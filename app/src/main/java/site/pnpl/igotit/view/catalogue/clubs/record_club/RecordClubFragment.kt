package site.pnpl.igotit.view.catalogue.clubs.record_club

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
import site.pnpl.igotit.databinding.FragmentRecordClubBinding
import site.pnpl.igotit.databinding.ItemTimeScedulerBinding
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
class RecordClubFragment : BaseFragment() {

    private var _binding: FragmentRecordClubBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: RecordClubViewModel

    @Inject
    lateinit var vmFactory: RecordClubViewModel.Factory

    private val id: Int? by lazy { arguments?.getInt("id") }
    private val uuid: UUID? by lazy { UUID.fromString(arguments?.getString("uuidString")) }

    private val onDayClickListener = View.OnClickListener { view ->
//        val view = createItemView()
//        binding.time.setReferencedIds(intArrayOf(view.id))
//        binding.time.addView(view)
        println("onDayClickListener - ${view.id}")
        viewModel.selectGroupe(EnumWeekCalendar.getRuShortByTextViewId(view.id))
    }

    private val createItemView: () -> View = {
        val inflater = LayoutInflater.from(context)
        val view = ItemTimeScedulerBinding.inflate(inflater, this.binding.getRoot(), false).root
        view.id = View.generateViewId()
        view
//        val itemView = inflater.inflate(R.layout.item_time_sceduler, null, false)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        listener?.onTitleTextChange(R.string.record)

        _binding = FragmentRecordClubBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, vmFactory)[RecordClubViewModel::class.java]

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.isMyCourse.collect {
                if (it) findNavController().navigate(R.id.action_detailsClubsFragment_to_homeFragment)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.schedule.collect { schedule ->
                println("schedule - $schedule")
                if(schedule.second.isNotEmpty()) {
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
            viewModel.firstDayOfWeek.collect {
//                hideShowArrow()
//
//                with(binding){
//                    day1.text = it.toDayOnly()
//                    day2.text = it.plusDays(1).toDayOnly()
//                    day3.text = it.plusDays(2).toDayOnly()
//                    day4.text = it.plusDays(3).toDayOnly()
//                    day5.text = it.plusDays(4).toDayOnly()
//                    day6.text = it.plusDays(5).toDayOnly()
//                    day7.text = it.plusDays(6).toDayOnly()
//                }
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
            id?.let { id -> viewModel.setMyCourse(id) }
        }

        initDayListener()
        hideShowArrow()
        uuid?.let { viewModel.getCoursesScheduler(it) }
    }

    private fun initDayListener() {
        with(binding){
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


    companion object {
        fun newInstance(id: Int?, uuidString: String?): RecordClubFragment =
            RecordClubFragment().apply {
                arguments = Bundle().apply {
                    if (id != null) {
                        putInt("id", id)
                    }
                    putString("uuidString", uuidString)
                }
            }
    }

}