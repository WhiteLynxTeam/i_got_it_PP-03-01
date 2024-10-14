package site.pnpl.igotit.view.courses.lessons

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.AndroidSupportInjection
import site.pnpl.igotit.databinding.FragmentLessonsBinding
import site.pnpl.igotit.domain.models.Lesson
import javax.inject.Inject

class LessonsFragment : Fragment() {

    private var _binding: FragmentLessonsBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: LessonsViewModel

    private val lessonAdapter = LessonAdapter() { id ->
        println("LessonsFragment id_lesson = $id")
    }

    @Inject
    lateinit var vmFactory: LessonsViewModel.Factory

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLessonsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("onViewCreated PhotosFragment")

        viewModel =
            ViewModelProvider(this, vmFactory)[LessonsViewModel::class.java]

        binding.rvLessons.adapter = lessonAdapter

        initRV()
    }

    private fun initRV() {
        val list: List<Lesson> = listOf(
            Lesson(1L,System.currentTimeMillis(),"Урок1"),
            Lesson(2L,System.currentTimeMillis(),"Урок2"),
            Lesson(3L,System.currentTimeMillis(),"Урок3"),
            Lesson(4L,System.currentTimeMillis(),"Урок4"),
            Lesson(5L,System.currentTimeMillis(),"Урок5"),
            Lesson(6L,System.currentTimeMillis(),"Урок6"),
            Lesson(7L,System.currentTimeMillis(),"Урок7"),
            Lesson(8L,System.currentTimeMillis(),"Урок8"),
            )
        lessonAdapter.setData(list)
    }


    companion object {
        fun newInstance() = LessonsFragment()
    }
}