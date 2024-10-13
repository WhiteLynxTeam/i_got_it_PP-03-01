package site.pnpl.igotit.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import site.pnpl.igotit.R
import site.pnpl.igotit.databinding.FragmentHomeTrainingBinding
import site.pnpl.igotit.domain.models.FavoritesCourses
import site.pnpl.igotit.domain.models.MyCourses
import site.pnpl.igotit.view.home.favorites_courses.FavoritesCoursesAdapter
import site.pnpl.igotit.view.home.my_courses.MyCoursesAdapter

class HomeTrainingFragment : Fragment() {

    private var _binding: FragmentHomeTrainingBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: HomeTrainingViewModel

    private val myCoursesAdapter =
        MyCoursesAdapter() { id -> println("MyCoursesFragment id_courses = $id") }

    private val favoritesCoursesAdapter =
        FavoritesCoursesAdapter() { id -> println("FavoritesCoursesFragment id_courses = $id") }

    /*@Inject
    lateinit var vmFactory : HomeTrainingViewModel.Factory*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeTrainingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnMyCourses.isSelected = true
        binding.btnFavoritesCourses.isSelected = false.also {
            binding.btnFavoritesCourses.setTextColor(resources.getColor(R.color.white))
        }

        /*viewModel =
            ViewModelProvider(this,vmFactory)[HomeTrainingViewModel::class.java]*/


        binding.btnMyCourses.setOnClickListener {
            binding.btnMyCourses.isSelected = true.also {
                binding.btnMyCourses.setTextColor(resources.getColor(R.color.black))
            }
            binding.btnFavoritesCourses.isSelected = false.also {
                binding.btnFavoritesCourses.setTextColor(resources.getColor(R.color.white))
            }
            binding.rv.adapter = myCoursesAdapter

            initCoursesRV()
        }

        binding.btnFavoritesCourses.setOnClickListener {
            binding.btnFavoritesCourses.isSelected = true.also {
                binding.btnFavoritesCourses.setTextColor(resources.getColor(R.color.black))
            }
            binding.btnMyCourses.isSelected = false.also {
                binding.btnMyCourses.setTextColor(resources.getColor(R.color.white))
            }
            binding.rv.adapter = favoritesCoursesAdapter
            initFavorRV()
        }
    }

    private fun initCoursesRV() {
        val list: List<MyCourses> = listOf(
            MyCourses("Например - Клуб по новелле «История Кицунэ»"),
            MyCourses("Например - Клуб по новелле «История Кицунэ»"),
            MyCourses("Например - Клуб по новелле «История Кицунэ»"),
            MyCourses("Например - Клуб по новелле «История Кицунэ»"),
            MyCourses("Например - Клуб по новелле «История Кицунэ»"),
            MyCourses("Например - Клуб по новелле «История Кицунэ»"),
            MyCourses("Например - Клуб по новелле «История Кицунэ»"),
        )
        myCoursesAdapter.setData(list)//изменить setData на логичное название
    }

    private fun initFavorRV() {
        val list: List<FavoritesCourses> = listOf(
            FavoritesCourses("Например - Клуб по новелле «История Кицунэ»","Интенсивный разговорный английский для начальных уровней"),
            FavoritesCourses("Например - Клуб по новелле «История Кицунэ»","Интенсивный разговорный английский для начальных уровней"),
            FavoritesCourses("Например - Клуб по новелле «История Кицунэ»","Интенсивный разговорный английский для начальных уровней"),
            FavoritesCourses("Например - Клуб по новелле «История Кицунэ»","Интенсивный разговорный английский для начальных уровней"),
            FavoritesCourses("Например - Клуб по новелле «История Кицунэ»","Интенсивный разговорный английский для начальных уровней"),
            FavoritesCourses("Например - Клуб по новелле «История Кицунэ»","Интенсивный разговорный английский для начальных уровней"),
        )
        favoritesCoursesAdapter.setData(list)//изменить setData на логичное название
    }
}