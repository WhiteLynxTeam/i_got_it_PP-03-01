package site.pnpl.igotit.view.home

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
import site.pnpl.igotit.databinding.FragmentHomeBinding
import site.pnpl.igotit.utils.toDayOnly
import site.pnpl.igotit.utils.uiextensions.hide
import site.pnpl.igotit.utils.uiextensions.show
import site.pnpl.igotit.view.base.BaseFragment
import site.pnpl.igotit.view.home.favorites_courses.FavoritesCoursesAdapter
import site.pnpl.igotit.view.home.my_courses.MyCoursesAdapter
import java.time.Instant
import java.time.ZoneId
import javax.inject.Inject

class HomeFragment : BaseFragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: HomeViewModel

    @Inject
    lateinit var vmFactory: HomeViewModel.Factory

    private val myCoursesAdapter =
        MyCoursesAdapter() {
            uuidCourse -> println("MyCoursesFragment id_courses = $uuidCourse")

            findNavController().navigate(R.id.action_homeFragment_to_coursesFragment,
                Bundle().apply {
                    putString("uuidString", uuidCourse.toString())
                })
        }

    private val favoritesCoursesAdapter =
        FavoritesCoursesAdapter() { id -> println("FavoritesCoursesFragment id_courses = $id") }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        listener?.onTitleTextChange("+7 (999) 999-99-99")

        _binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, vmFactory)[HomeViewModel::class.java]

        binding.homeLayout.btnMyCourses.isSelected = true
        binding.homeLayout.btnFavoritesCourses.isSelected = false.also {
            binding.homeLayout.btnFavoritesCourses.setTextColor(resources.getColor(R.color.white))
        }

        binding.homeLayout.rv.adapter = myCoursesAdapter
//        initCoursesRV()

        binding.homeLayout.btnMyCourses.setOnClickListener {
            binding.homeLayout.btnMyCourses.isSelected = true.also {
                binding.homeLayout.btnMyCourses.setTextColor(resources.getColor(R.color.black))
            }
            binding.homeLayout.btnFavoritesCourses.isSelected = false.also {
                binding.homeLayout.btnFavoritesCourses.setTextColor(resources.getColor(R.color.white))
            }
            binding.homeLayout.rv.adapter = myCoursesAdapter
//            initCoursesRV()

        }

        binding.homeLayout.btnFavoritesCourses.setOnClickListener {
            binding.homeLayout.btnFavoritesCourses.isSelected = true.also {
                binding.homeLayout.btnFavoritesCourses.setTextColor(resources.getColor(R.color.black))
            }
            binding.homeLayout.btnMyCourses.isSelected = false.also {
                binding.homeLayout.btnMyCourses.setTextColor(resources.getColor(R.color.white))
            }
            binding.homeLayout.rv.adapter = favoritesCoursesAdapter
//            initFavorRV()
        }

        //переход во фрагмент с "выбрать обучение"
        binding.emptyHomeLayout.chooseTrainingButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_catalogueFragment)
        }

        //переход во фрагмент с "избранными курсами"
        binding.emptyHomeLayout.favoritesCoursesButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_homeFavoritesCoursesFragment)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.myCourses.collect {

                println("List<Clubs> $it")
                if (it.first.isEmpty() && it.second.isEmpty()) {
                    binding.homeLayout.root.hide()
                    binding.emptyHomeLayout.root.show()
                } else {
                    binding.homeLayout.root.show()
                    binding.emptyHomeLayout.root.hide()
                    myCoursesAdapter.setData(it.first)
                    favoritesCoursesAdapter.setData(it.second)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.lesson.collect {

                println("Lesson $it")

                if (it != null) {
                    binding.homeLayout.dateLesson.text = dateMilisToDateString(it.dateMilis)
                    binding.homeLayout.titleLesson.text = it.title
                } else {

                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun dateMilisToDateString(dateMilis: Long): String {
        val instant = Instant.ofEpochMilli(dateMilis)
        val localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate()
        val day = localDate.toDayOnly()
        val month = String.format("%02d", localDate.monthValue)
        val year = localDate.year.toString()
        val localDateTime = instant.atZone(ZoneId.systemDefault()).toLocalDateTime()
        val hour = String.format("%02d", localDateTime.hour)
        val minute = String.format("%02d", localDateTime.minute)
        return "$day.$month.$year - $hour:$minute"
    }
}