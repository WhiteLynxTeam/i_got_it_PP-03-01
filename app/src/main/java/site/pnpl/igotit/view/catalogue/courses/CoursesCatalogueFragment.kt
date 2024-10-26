package site.pnpl.igotit.view.catalogue.courses

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import dagger.android.support.AndroidSupportInjection
import site.pnpl.igotit.R
import site.pnpl.igotit.databinding.FragmentCoursesCatalogueBinding
import site.pnpl.igotit.domain.models.CoursesCatalogue
import javax.inject.Inject


class CoursesCatalogueFragment : Fragment() {
    private var _binding: FragmentCoursesCatalogueBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CoursesCatalogueViewModel by viewModels()

    private val coursesCatalogue = CoursesCatalogueAdapter { title ->
        findNavController().navigate(R.id.action_catalogueFragment_to_detailsCoursesCatalogueFragment,
            Bundle().apply { putString("title", title) })
    }

    /*@Inject
    lateinit var vmFactory: CoursesCatalogueViewModel.Factory*/

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
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

        //viewModel = ViewModelProvider(this,vmFactory)[CoursesCatalogueViewModel::class.java]
        binding.rvCoursesCatalogue.adapter = coursesCatalogue

        initRV()
    }

    private fun initRV() {
        val list: List<CoursesCatalogue> = listOf(
            CoursesCatalogue(
                "Хочу заговорить",
                "A1 - A2",
                "2 занятия",
                "2 раза в неделю",
                "1,5 часа",
                "20 занятий",
                "Интенсивный разговорный английский для начальных уровней"
            ),
            CoursesCatalogue(
                "Хочу в айти",
                "B1+",
                "2 занятия",
                "2 раза в неделю",
                "1,5 часа",
                "24 занятий",
                "Айтишная лексика и бизнес-английский"
            ),
            CoursesCatalogue(
                "Качаю софт-скиллы",
                "B1+",
                "2 занятия",
                "2 раза в неделю",
                "1 часа",
                "30 занятий",
                "Разговорный рабочий английский"
            ),
        )
        coursesCatalogue.setData(list)
    }

    companion object {
        fun newInstance() = CoursesCatalogueFragment()
    }
}