package site.pnpl.igotit.view.catalogue.courses

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.launch
import site.pnpl.igotit.R
import site.pnpl.igotit.databinding.FragmentCoursesCatalogueBinding
import site.pnpl.igotit.domain.models.Clubs
import site.pnpl.igotit.domain.models.Courses
import site.pnpl.igotit.view.base.BaseFragment
import site.pnpl.igotit.view.catalogue.CatalogueViewModel
import site.pnpl.igotit.view.catalogue.courses.about_course.AboutCourseFragment
import javax.inject.Inject


class CoursesCatalogueFragment : BaseFragment() {
    private var _binding: FragmentCoursesCatalogueBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: CoursesCatalogueViewModel

    @Inject
    lateinit var vmFactory: CoursesCatalogueViewModel.Factory

    private val coursesCatalogue = CoursesCatalogueAdapter { title , id ->
        findNavController().navigate(R.id.action_catalogueFragment_to_detailsCoursesCatalogueFragment,
            Bundle().apply {
                putString("title", title)
                putInt("id", id)
            })
    }

//    override fun onAttach(context: Context) {
//        AndroidSupportInjection.inject(this)
//        super.onAttach(context)
//    }

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

                println("List<Clubs> $it")

                coursesCatalogue.setData(it)

            }
        }

        binding.rvCoursesCatalogue.adapter = coursesCatalogue

        viewModel.getCoursesFromDb()
//        initRV()
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
        fun newInstance(listClubs: List<Clubs>?): CoursesCatalogueFragment = CoursesCatalogueFragment().apply {
//            arguments = Bundle().apply {
//                putString("title", title)
//            }
        }
    }
}