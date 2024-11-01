package site.pnpl.igotit.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch
import site.pnpl.igotit.R
import site.pnpl.igotit.databinding.FragmentHomeBinding
import site.pnpl.igotit.domain.models.FavoritesCourses
import site.pnpl.igotit.domain.models.MyCourses
import site.pnpl.igotit.utils.uiextensions.hide
import site.pnpl.igotit.utils.uiextensions.show
import site.pnpl.igotit.view.base.BaseFragment
import site.pnpl.igotit.view.home.favorites_courses.FavoritesCoursesAdapter
import site.pnpl.igotit.view.home.my_courses.MyCoursesAdapter
import javax.inject.Inject

class HomeFragment : BaseFragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: HomeViewModel

    @Inject
    lateinit var vmFactory: HomeViewModel.Factory

    private val myCoursesAdapter =
        MyCoursesAdapter() { id -> println("MyCoursesFragment id_courses = $id") }

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
                if (it.isEmpty()) {
                    binding.homeLayout.root.hide()
                    binding.emptyHomeLayout.root.show()
                } else {
                    binding.homeLayout.root.show()
                    binding.emptyHomeLayout.root.hide()
                    myCoursesAdapter.setData(it)
                    favoritesCoursesAdapter.setData(it)
                }
            }
        }
    }

    private fun initCoursesRV() {
//        val list: List<MyCourses> = listOf(
//            MyCourses("Например - Клуб по новелле «История Кицунэ»"),
//            MyCourses("Например - Клуб по новелле «История Кицунэ»"),
//            MyCourses("Например - Клуб по новелле «История Кицунэ»"),
//            MyCourses("Например - Клуб по новелле «История Кицунэ»"),
//            MyCourses("Например - Клуб по новелле «История Кицунэ»"),
//            MyCourses("Например - Клуб по новелле «История Кицунэ»"),
//            MyCourses("Например - Клуб по новелле «История Кицунэ»"),
//            MyCourses("Например - Клуб по новелле «История Кицунэ»"),
//            MyCourses("Например - Клуб по новелле «История Кицунэ»"),
//            MyCourses("Например - Клуб по новелле «История Кицунэ»"),
//            MyCourses("Например - Клуб по новелле «История Кицунэ»"),
//            MyCourses("Например - Клуб по новелле «История Кицунэ»"),
//            MyCourses("Например - Клуб по новелле «История Кицунэ»"),
//            MyCourses("Например - Клуб по новелле «История Кицунэ»"),
//            MyCourses("Например - Клуб по новелле «История Кицунэ»"),
//            MyCourses("Например - Клуб по новелле «История Кицунэ»"),
//            MyCourses("Например - Клуб по новелле «История Кицунэ»"),
//            MyCourses("Например - Клуб по новелле «История Кицунэ»"),
//            MyCourses("Например - Клуб по новелле «История Кицунэ»"),
//            MyCourses("Например - Клуб по новелле «История Кицунэ»"),
//            MyCourses("Например - Клуб по новелле «История Кицунэ»"),
//            MyCourses("Например - Клуб по новелле «История Кицунэ»"),
//            MyCourses("Например - Клуб по новелле «История Кицунэ»"),
//            MyCourses("Например - Клуб по новелле «История Кицунэ»"),
//            MyCourses("Например - Клуб по новелле «История Кицунэ»"),
//            MyCourses("Например - Клуб по новелле «История Кицунэ»"),
//            MyCourses("Например - Клуб по новелле «История Кицунэ»"),
//        )
//        myCoursesAdapter.setData(list)//изменить setData на логичное название
    }

    private fun initFavorRV() {
//        val list: List<FavoritesCourses> = listOf(
//            FavoritesCourses(
//                "Например - Клуб по новелле «История Кицунэ»",
//                "Интенсивный разговорный английский для начальных уровней"
//            ),
//            FavoritesCourses(
//                "Например - Клуб по новелле «История Кицунэ»",
//                "Интенсивный разговорный английский для начальных уровней"
//            ),
//            FavoritesCourses(
//                "Например - Клуб по новелле «История Кицунэ»",
//                "Интенсивный разговорный английский для начальных уровней"
//            ),
//            FavoritesCourses(
//                "Например - Клуб по новелле «История Кицунэ»",
//                "Интенсивный разговорный английский для начальных уровней"
//            ),
//            FavoritesCourses(
//                "Например - Клуб по новелле «История Кицунэ»",
//                "Интенсивный разговорный английский для начальных уровней"
//            ),
//            FavoritesCourses(
//                "Например - Клуб по новелле «История Кицунэ»",
//                "Интенсивный разговорный английский для начальных уровней"
//            ),
//            FavoritesCourses(
//                "Например - Клуб по новелле «История Кицунэ»",
//                "Интенсивный разговорный английский для начальных уровней"
//            ),
//            FavoritesCourses(
//                "Например - Клуб по новелле «История Кицунэ»",
//                "Интенсивный разговорный английский для начальных уровней"
//            ),
//            FavoritesCourses(
//                "Например - Клуб по новелле «История Кицунэ»",
//                "Интенсивный разговорный английский для начальных уровней"
//            ),
//            FavoritesCourses(
//                "Например - Клуб по новелле «История Кицунэ»",
//                "Интенсивный разговорный английский для начальных уровней"
//            ),
//            FavoritesCourses(
//                "Например - Клуб по новелле «История Кицунэ»",
//                "Интенсивный разговорный английский для начальных уровней"
//            ),
//        )
//        favoritesCoursesAdapter.setData(list)//изменить setData на логичное название
    }
}