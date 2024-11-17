package site.pnpl.igotit.view.courses.remove

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import site.pnpl.igotit.databinding.FragmentRemoveCourseBinding
import site.pnpl.igotit.view.base.BaseFragment
import site.pnpl.igotit.view.courses.lessons.LessonsFragment
import java.util.UUID
import javax.inject.Inject

class RemoveCourseFragment : BaseFragment() {
    private var _binding: FragmentRemoveCourseBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: RemoveCourseViewModel

    private val uuidCourse: UUID? by lazy { UUID.fromString(arguments?.getString("uuidString")) }

    @Inject
    lateinit var vmFactory: RemoveCourseViewModel.Factory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        listener?.onTitleTextChange("+7 (999) 999-99-99")

        _binding = FragmentRemoveCourseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel =
            ViewModelProvider(this, vmFactory)[RemoveCourseViewModel::class.java]

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.club.collect {
                binding.tvTitleLesson.text = it.title
            }
        }

        binding.deleteCourseButton.setOnClickListener {
            uuidCourse?.let { uuidCourse -> viewModel.delCourse(uuidCourse) }
        }

        uuidCourse?.let { viewModel.getCourse(it) }
    }

    companion object {
        fun newInstance(uuidString: String?): RemoveCourseFragment =
            RemoveCourseFragment().apply {
                arguments = Bundle().apply {
                    putString("uuidString", uuidString)
                }
            }
    }
}