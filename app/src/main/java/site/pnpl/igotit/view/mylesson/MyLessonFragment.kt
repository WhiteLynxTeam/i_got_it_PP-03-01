package site.pnpl.igotit.view.mylesson

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import site.pnpl.igotit.databinding.FragmentMyLessonBinding
import site.pnpl.igotit.view.base.BaseFragment
import site.pnpl.igotit.view.courses.lessons.LessonsViewModel
import javax.inject.Inject

class MyLessonFragment : BaseFragment() {

    private var _binding: FragmentMyLessonBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MyLessonViewModel

    private val myLessonAdapter by lazy {
        MyLessonAdapter(requireContext()) { id ->
            println("LessonsFragment id_lesson = $id")
        }
    }

    @Inject
    lateinit var vmFactory: MyLessonViewModel.Factory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        listener?.onTitleTextChange("+7 (999) 999-99-99")

        _binding = FragmentMyLessonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel =
            ViewModelProvider(this, vmFactory)[MyLessonViewModel::class.java]

        binding.rvLessons.adapter = myLessonAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.lesson.collect {
                myLessonAdapter.setData(it)
            }
        }

        viewModel.getLessons()
    }
}