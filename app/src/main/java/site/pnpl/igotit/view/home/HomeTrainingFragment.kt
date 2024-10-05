package site.pnpl.igotit.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import site.pnpl.igotit.databinding.FragmentHomeTrainingBinding

class HomeTrainingFragment : Fragment() {
    private lateinit var binding: FragmentHomeTrainingBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeTrainingBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.favoritesCoursesButton.setOnClickListener {
//            findNavController().navigate(R.id.action_homeTrainingFragment_to_homeFavoritesCoursesFragment)
        }
    }
}