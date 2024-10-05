package site.pnpl.igotit.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import site.pnpl.igotit.databinding.FragmentHomeFavoritesCoursesBinding

class HomeFavoritesCoursesFragment : Fragment() {
    private lateinit var binding: FragmentHomeFavoritesCoursesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeFavoritesCoursesBinding.inflate(layoutInflater)
        return binding.root

    }
}