package site.pnpl.igotit.view.catalogue.clubs.record_club

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintSet
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch
import site.pnpl.igotit.R
import site.pnpl.igotit.databinding.FragmentRecordClubBinding
import site.pnpl.igotit.domain.models.WeekCalendar
import site.pnpl.igotit.utils.toDayOnly
import site.pnpl.igotit.utils.toGetFirstDayOfWeek
import site.pnpl.igotit.utils.uiextensions.hide
import site.pnpl.igotit.utils.uiextensions.show
import site.pnpl.igotit.view.base.BaseFragment
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.ArrayList
import java.util.UUID
import javax.inject.Inject

class RecordClubFragment : BaseFragment() {

    private var _binding: FragmentRecordClubBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: RecordClubViewModel

    @Inject
    lateinit var vmFactory: RecordClubViewModel.Factory

    private val id: Int? by lazy { arguments?.getInt("id") }
    private val uuid: UUID? by lazy { UUID.fromString(arguments?.getString("uuidString")) }

    private val listTVDay: List<Int> = listOf(
        R.id.day_1,
        R.id.day_2,
        R.id.day_3,
        R.id.day_4,
        R.id.day_5,
        R.id.day_6,
        R.id.day_7,
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        listener?.onTitleTextChange(R.string.record)

        _binding = FragmentRecordClubBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
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
                println(schedule)
                hideShowArrow()

                setNowInWeekCalenddar()
                WeekCalendar.entries.forEach { dayWeek ->
                    dayWeek.isAvailable =
                        schedule.any { dayScheduler -> dayScheduler.dayOfWeek == dayWeek.RuShort }
                    when (dayWeek) {
                        WeekCalendar.MONDAY -> {
                            with(binding.day1) {
                                isEnabled = dayWeek.isAvailable
                                text = viewModel.firstDayOfWeek.value.toDayOnly()
                            }
                        }

                        WeekCalendar.TUESDAY -> {
                            with(binding.day2) {
                                isEnabled = dayWeek.isAvailable
                                text = viewModel.firstDayOfWeek.value.plusDays(1).toDayOnly()
                            }
                        }

                        WeekCalendar.WEDNESDAY -> {
                            with(binding.day3) {
                                isEnabled = dayWeek.isAvailable
                                text = viewModel.firstDayOfWeek.value.plusDays(2).toDayOnly()
                            }
                        }

                        WeekCalendar.THURSDAY -> {
                            with(binding.day4) {
                                isEnabled = dayWeek.isAvailable
                                text = viewModel.firstDayOfWeek.value.plusDays(3).toDayOnly()
                            }
                        }

                        WeekCalendar.FRIDAY -> {
                            with(binding.day5) {
                                isEnabled = dayWeek.isAvailable
                                text = viewModel.firstDayOfWeek.value.plusDays(4).toDayOnly()
                            }
                        }

                        WeekCalendar.SATURDAY -> {
                            with(binding.day6) {
                                isEnabled = dayWeek.isAvailable
                                text = viewModel.firstDayOfWeek.value.plusDays(5).toDayOnly()
                            }
                        }

                        WeekCalendar.SUNDAY -> {
                            with(binding.day7) {
                                isEnabled = dayWeek.isAvailable
                                text = viewModel.firstDayOfWeek.value.plusDays(6).toDayOnly()
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
            viewModel.getFirstDayOfWeek(viewModel.firstDayOfWeek.value.minusDays(7))
            uuid?.let { uuid -> viewModel.getCoursesScheduler(uuid) }
        }

        binding.arrowRight.setOnClickListener {
            viewModel.getFirstDayOfWeek(viewModel.firstDayOfWeek.value.plusDays(7))
            uuid?.let { uuid -> viewModel.getCoursesScheduler(uuid) }
        }

        binding.btnRegister.setOnClickListener {
            id?.let { id -> viewModel.setMyCourse(id) }
        }

        hideShowArrow()
        uuid?.let { viewModel.getCoursesScheduler(it) }
    }

    @RequiresApi(Build.VERSION_CODES.O)
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

    @RequiresApi(Build.VERSION_CODES.O)
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
        val constraintSet = ConstraintSet()
        constraintSet.clone(binding.root)

        // Устанавливаем ограничения для вашего View
        constraintSet.connect(
            R.id.pointNow,
            ConstraintSet.START,
            listTVDay[day],
            ConstraintSet.START
        )
        constraintSet.connect(
            R.id.pointNow,
            ConstraintSet.END,
            listTVDay[day],
            ConstraintSet.END
        )
        constraintSet.connect(
            R.id.pointNow,
            ConstraintSet.TOP,
            listTVDay[day],
            ConstraintSet.BOTTOM
        )

        constraintSet.applyTo(binding.root)
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