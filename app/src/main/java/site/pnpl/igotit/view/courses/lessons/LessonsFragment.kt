package site.pnpl.igotit.view.courses.lessons

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import site.pnpl.igotit.databinding.FragmentLessonsBinding
import site.pnpl.igotit.domain.models.Lesson
import site.pnpl.igotit.view.base.BaseFragment
import java.util.UUID
import javax.inject.Inject

class LessonsFragment : BaseFragment() {

    private var _binding: FragmentLessonsBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: LessonsViewModel

    private val uuidCourse: UUID? by lazy { UUID.fromString(arguments?.getString("uuidString")) }

    private val lessonAdapter by lazy {
        LessonAdapter(requireContext()) { id ->
            println("LessonsFragment id_lesson = $id")
        }
    }

    @Inject
    lateinit var vmFactory: LessonsViewModel.Factory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        listener?.onTitleTextChange("+7 (999) 999-99-99")

        _binding = FragmentLessonsBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel =
            ViewModelProvider(this, vmFactory)[LessonsViewModel::class.java]

        binding.rvLessons.adapter = lessonAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.lesson.collect {
                binding.tvTitleLesson.text = it.first.title

                lessonAdapter.setData(it.second, it.first.title)
            }
        }

        uuidCourse?.let { viewModel.getLesson(it) }
    }

    companion object {
        fun newInstance(uuidString: String?): LessonsFragment =
            LessonsFragment().apply {
                arguments = Bundle().apply {
                    putString("uuidString", uuidString)
                }
            }
    }
}