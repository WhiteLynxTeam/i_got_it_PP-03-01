package site.pnpl.igotit.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import site.pnpl.igotit.R
import site.pnpl.igotit.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private val viewModel: HomeViewModel by viewModels()

    private lateinit var binding: FragmentHomeBinding

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

        //переход во фрагмент с "выбрать обучение"
        binding.chooseTrainingButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_coursesFragment)
        }

        //переход во фрагмент с "избранными курсами"
        binding.favoritesCoursesButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_homeFavoritesCoursesFragment)
        }
    }

}