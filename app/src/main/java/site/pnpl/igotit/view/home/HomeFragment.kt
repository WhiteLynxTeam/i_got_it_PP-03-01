package site.pnpl.igotit.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import site.pnpl.igotit.adapters.CoursesListRecyclerAdapter
import site.pnpl.igotit.databinding.FragmentHomeBinding
import site.pnpl.igotit.items.Courses

class HomeFragment : Fragment() {
    private val viewModel: HomeViewModel by viewModels()

    private lateinit var binding: FragmentHomeBinding
    private lateinit var coursesAdapter:CoursesListRecyclerAdapter

    val coursesDataBase = listOf(
        Courses("Выбрать обучение"),
        Courses("Избранные курсы"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val courses_recycler = binding.coursesRecycler
        //находим наш RV
        courses_recycler.apply {
            coursesAdapter= CoursesListRecyclerAdapter(object :CoursesListRecyclerAdapter.OnItemClickListener{
                override fun click(courses: Courses) {
                    Toast.makeText(context,"Тут будет что-то", Toast.LENGTH_SHORT).show()
                }
            })
            //Присваиваем адаптер
            adapter = coursesAdapter
            //Присвои layoutmanager
            layoutManager = LinearLayoutManager(requireContext())
        }
        //Кладем нашу БД в RV
        coursesAdapter.addItems(coursesDataBase)

    }
}