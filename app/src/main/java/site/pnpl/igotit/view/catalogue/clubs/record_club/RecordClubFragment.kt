package site.pnpl.igotit.view.catalogue.clubs.record_club

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch
import site.pnpl.igotit.R
import site.pnpl.igotit.databinding.FragmentRecordClubBinding
import site.pnpl.igotit.utils.toDayOnly
import site.pnpl.igotit.utils.toGetFirstDayOfWeek
import site.pnpl.igotit.utils.uiextensions.hide
import site.pnpl.igotit.utils.uiextensions.show
import site.pnpl.igotit.view.base.BaseFragment
import java.time.LocalDate
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
            viewModel.schedule.collect {
                println(it)
                hideShowArrow()

                with(binding) {
                    day1.text = viewModel.firstDayOfWeek.value.toDayOnly()
                    day2.text = viewModel.firstDayOfWeek.value.plusDays(1).toDayOnly()
                    day3.text = viewModel.firstDayOfWeek.value.plusDays(2).toDayOnly()
                    day4.text = viewModel.firstDayOfWeek.value.plusDays(3).toDayOnly()
                    day5.text = viewModel.firstDayOfWeek.value.plusDays(4).toDayOnly()
                    day6.text = viewModel.firstDayOfWeek.value.plusDays(5).toDayOnly()
                    day7.text = viewModel.firstDayOfWeek.value.plusDays(6).toDayOnly()
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
    fun hideShowArrow() {
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