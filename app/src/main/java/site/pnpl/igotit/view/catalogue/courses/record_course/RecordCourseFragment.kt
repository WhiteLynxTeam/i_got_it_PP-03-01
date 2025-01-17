package site.pnpl.igotit.view.catalogue.courses.record_course

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintSet
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch
import site.pnpl.igotit.R
import site.pnpl.igotit.databinding.FragmentRecordCourseBinding
import site.pnpl.igotit.databinding.ItemTimeScedulerBinding
import site.pnpl.igotit.domain.models.CoursesSchedule
import site.pnpl.igotit.domain.models.EnumWeekCalendar
import site.pnpl.igotit.utils.toDayOnly
import site.pnpl.igotit.utils.toGetFirstDayOfWeek
import site.pnpl.igotit.utils.uiextensions.hide
import site.pnpl.igotit.utils.uiextensions.show
import site.pnpl.igotit.view.base.BaseFragment
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.UUID
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
class RecordCourseFragment : BaseFragment() {
    private var _binding: FragmentRecordCourseBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: RecordCourseViewModel

    @Inject
    lateinit var vmFactory: RecordCourseViewModel.Factory

    private val id: Int? by lazy { arguments?.getInt("id") }
    private val uuid: UUID? by lazy { UUID.fromString(arguments?.getString("uuidString")) }

    private val listViewScheduleTime = mutableListOf<View>()

    private val onDayClickListener = View.OnClickListener { view ->
        setSelected(view)
        viewModel.selectGroupe(EnumWeekCalendar.getRuShortByTextViewId(view.id))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        listener?.onTitleTextChange(R.string.record)

        _binding = FragmentRecordCourseBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, vmFactory)[RecordCourseViewModel::class.java]

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.isMyCourse.collect {
                if (it) findNavController().navigate(R.id.action_detailsCoursesCatalogueFragment_to_homeFragment)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.schedule.collect { schedule ->
                println("schedule - $schedule")
                if (schedule.second.isNotEmpty()) {
                    hideShowArrow()

                    setNowInWeekCalenddar()
                    EnumWeekCalendar.entries.forEach { dayWeek ->
                        dayWeek.isAvailable =
                            schedule.second.any { dayScheduler -> dayScheduler.dayOfWeek == dayWeek.RuShort }
                        when (dayWeek) {
                            EnumWeekCalendar.MONDAY -> {
                                with(binding.day1) {
                                    isEnabled = dayWeek.isAvailable
                                    text = schedule.first.toDayOnly()
//                                    text = viewModel.firstDayOfWeek.value.toDayOnly()
                                }
                            }

                            EnumWeekCalendar.TUESDAY -> {
                                with(binding.day2) {
                                    isEnabled = dayWeek.isAvailable
                                    text = schedule.first.plusDays(1).toDayOnly()
                                }
                            }

                            EnumWeekCalendar.WEDNESDAY -> {
                                with(binding.day3) {
                                    isEnabled = dayWeek.isAvailable
                                    text = schedule.first.plusDays(2).toDayOnly()
                                }
                            }

                            EnumWeekCalendar.THURSDAY -> {
                                with(binding.day4) {
                                    isEnabled = dayWeek.isAvailable
                                    text = schedule.first.plusDays(3).toDayOnly()
                                }
                            }

                            EnumWeekCalendar.FRIDAY -> {
                                with(binding.day5) {
                                    isEnabled = dayWeek.isAvailable
                                    text = schedule.first.plusDays(4).toDayOnly()
                                }
                            }

                            EnumWeekCalendar.SATURDAY -> {
                                with(binding.day6) {
                                    isEnabled = dayWeek.isAvailable
                                    text = schedule.first.plusDays(5).toDayOnly()
                                }
                            }

                            EnumWeekCalendar.SUNDAY -> {
                                with(binding.day7) {
                                    isEnabled = dayWeek.isAvailable
                                    text = schedule.first.plusDays(6).toDayOnly()
                                }
                            }
                        }
                    }

                }

            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.timeSchedule.collect { schedule ->
                println("schedule - $schedule")
                if (schedule.isNotEmpty()) {
                    if (listViewScheduleTime.isNotEmpty()) {
                        listViewScheduleTime.forEach {
                            binding.flowTimeLayout.time.referencedIds = intArrayOf()
                            binding.root.removeView(it)
                        }
                        listViewScheduleTime.clear()
                    }

                    schedule.forEach {
                        val viewTime = createItemView(it)
                        listViewScheduleTime.add(viewTime)
                    }

                    listViewScheduleTime.forEach {
                        binding.flowTimeLayout.time.referencedIds += it.id
                        binding.root.addView(it)
                    }
                }
            }
        }

        binding.arrowLeft.setOnClickListener {
            println("binding.arrowLeft.setOnClickListener - ${viewModel.firstDayOfWeek.value}")
            viewModel.getFirstDayOfWeek(viewModel.firstDayOfWeek.value.minusDays(7))
            uuid?.let { uuid -> viewModel.getCoursesScheduler(uuid) }
        }

        binding.arrowRight.setOnClickListener {
            println("binding.arrowRight.setOnClickListener - ${viewModel.firstDayOfWeek.value}")
            viewModel.getFirstDayOfWeek(viewModel.firstDayOfWeek.value.plusDays(7))
            uuid?.let { uuid -> viewModel.getCoursesScheduler(uuid) }
        }

        binding.btnRegister.setOnClickListener {
//            id?.let { id -> viewModel.setMyCourse(id) }
            viewModel.setMyCourse()
        }

        initDayListener()
        hideShowArrow()
        uuid?.let { viewModel.getCoursesScheduler(it) }
    }

    private fun initDayListener() {
        with(binding) {
            day1.setOnClickListener(onDayClickListener)
            day2.setOnClickListener(onDayClickListener)
            day3.setOnClickListener(onDayClickListener)
            day4.setOnClickListener(onDayClickListener)
            day5.setOnClickListener(onDayClickListener)
            day6.setOnClickListener(onDayClickListener)
            day7.setOnClickListener(onDayClickListener)
        }
    }

    private fun hideShowArrow() {
        if (viewModel.firstDayOfWeek.value.toGetFirstDayOfWeek() <= LocalDate.now()) {
//            binding.arrowLeft.isEnabled = false
            binding.arrowLeft.hide()
        } else {
//            binding.arrowLeft.isEnabled = true
            binding.arrowLeft.show()
        }
        if (viewModel.firstDayOfWeek.value.plusDays(7)
                .toGetFirstDayOfWeek().month != LocalDate.now().month
        ) {
//            binding.arrowRight.isEnabled = false
            binding.arrowRight.hide()
        } else {
//            binding.arrowRight.isEnabled = true
            binding.arrowRight.show()
        }
    }

    private fun setNowInWeekCalenddar() {
        val now = LocalDate.now()
        if (now.isAfter(viewModel.firstDayOfWeek.value) &&
            now.isBefore(viewModel.firstDayOfWeek.value.plusDays(6))
        ) {
            val day = ChronoUnit.DAYS.between(viewModel.firstDayOfWeek.value, now).toInt()
//            WeekCalendar.entries[day].isNow = true
            setPointForNow(day)
            binding.pointNow.show()
        } else {
            binding.pointNow.hide()
//            WeekCalendar.entries.forEach { it.isNow = false}
        }
    }

    private fun setPointForNow(day: Int) {
        /*** Увеличиваем на 1, потому что в неделе позицию считаем от 1, а получаем разницу количества дней между
         * понедельником и сегодня, т.е. если разница 0 - то это понедельник*/
        val viewDay = EnumWeekCalendar.getTextViewIdByPos(day + 1)
        if (viewDay != null) {
            val constraintSet = ConstraintSet()
            constraintSet.clone(binding.root)

            constraintSet.connect(
                R.id.pointNow,
                ConstraintSet.START,
                viewDay,
                ConstraintSet.START
            )
            constraintSet.connect(
                R.id.pointNow,
                ConstraintSet.END,
                viewDay,
                ConstraintSet.END
            )
            constraintSet.connect(
                R.id.pointNow,
                ConstraintSet.TOP,
                viewDay,
                ConstraintSet.BOTTOM
            )

            constraintSet.applyTo(binding.root)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun createItemView(itemCoursesSchedule: CoursesSchedule): View {
//         LayoutInflater.from(context).inflate(R.layout.item_time_sceduler, binding.root, false)
//            .apply {
//                id = View.generateViewId()
//            }

        val itemView =
            ItemTimeScedulerBinding.inflate(LayoutInflater.from(context), binding.root, false)
                .apply {
                    tvType.text = itemCoursesSchedule.shortType
                    tvTime.text =
                        "${itemCoursesSchedule.startHour} ${itemCoursesSchedule.startMinute}"
                    root.apply {
                        id = View.generateViewId()
                    }
                }.root

        itemView.setOnClickListener {
            onTimeSchedulerClick(it, itemCoursesSchedule.uuid)
        }
        return itemView
    }

    private fun setSelected(view: View?) {
        if (view != null) {
            with(binding) {
                day1.isSelected = false
                day2.isSelected = false
                day3.isSelected = false
                day4.isSelected = false
                day5.isSelected = false
                day6.isSelected = false
                day7.isSelected = false
                view.isSelected = true
            }
        }
    }

    private fun onTimeSchedulerClick(timeSchedulerView: View, uuidTimeSchedule: UUID) {
        val isSelected = timeSchedulerView.isSelected
        listViewScheduleTime.forEach {
            it.isSelected = false
        }

        if (!isSelected) {
            binding.btnRegister.isEnabled = true
            timeSchedulerView.isSelected = true
            viewModel.uuidTimeSchedule = uuidTimeSchedule
        } else {
            binding.btnRegister.isEnabled = false
        }
    }

    companion object {
        fun newInstance(id: Int?, uuidString: String?): RecordCourseFragment =
            RecordCourseFragment().apply {
                arguments = Bundle().apply {
                    if (id != null) {
                        putInt("id", id)
                    }
                    putString("uuidString", uuidString)
                }
            }
    }
}
