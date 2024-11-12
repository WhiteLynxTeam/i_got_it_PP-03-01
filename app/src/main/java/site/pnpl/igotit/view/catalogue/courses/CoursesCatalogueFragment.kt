package site.pnpl.igotit.view.catalogue.courses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch
import site.pnpl.igotit.R
import site.pnpl.igotit.databinding.FragmentCoursesCatalogueBinding
import site.pnpl.igotit.domain.models.Clubs
import site.pnpl.igotit.domain.models.Courses
import site.pnpl.igotit.view.base.BaseFragment
import javax.inject.Inject


class CoursesCatalogueFragment : BaseFragment() {
    private var _binding: FragmentCoursesCatalogueBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: CoursesCatalogueViewModel

    @Inject
    lateinit var vmFactory: CoursesCatalogueViewModel.Factory

    private val coursesCatalogueAdapter = CoursesCatalogueAdapter { id, uuid ->
        findNavController().navigate(R.id.action_catalogueFragment_to_detailsCoursesCatalogueFragment,
            Bundle().apply {
                putInt("id", id)
                putString("uuidString", uuid.toString())
            })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCoursesCatalogueBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this,vmFactory)[CoursesCatalogueViewModel::class.java]

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.courses.collect {
                coursesCatalogueAdapter.setData(it)
            }
        }

        binding.rvCoursesCatalogue.adapter = coursesCatalogueAdapter

        viewModel.getCoursesFromDb()
    }

    private fun initRV() {
        val list: List<Courses> = listOf(
            Courses(
                "Хочу заговорить",
                "A1 - A2",
                "2 занятия",
                "2 раза в неделю",
                "1,5 часа",
                "20 занятий",
                "Интенсивный разговорный английский для начальных уровней"
            ),
            Courses(
                "Хочу в айти",
                "B1+",
                "2 занятия",
                "2 раза в неделю",
                "1,5 часа",
                "24 занятий",
                "Айтишная лексика и бизнес-английский"
            ),
            Courses(
                "Качаю софт-скиллы",
                "B1+",
                "2 занятия",
                "2 раза в неделю",
                "1 часа",
                "30 занятий",
                "Разговорный рабочий английский"
            ),
        )
//        coursesCatalogue.setData(list)
    }

    companion object {
        fun newInstance() = CoursesCatalogueFragment()
    }
}