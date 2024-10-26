package site.pnpl.igotit.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import site.pnpl.igotit.R
import site.pnpl.igotit.databinding.FragmentHomeBinding
import site.pnpl.igotit.view.base.BaseFragment

class HomeFragment : BaseFragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: HomeViewModel

//    private var listener: OnHeaderChangeListener? = null

//    override fun onAttach(context: Context) {
//        AndroidSupportInjection.inject(this)
//        super.onAttach(context)
//
//        if (context is OnHeaderChangeListener) {
//            listener = context
//        } else {
//            throw RuntimeException("$context must implement OnTextChangeListener")
//        }
//    }

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

        //переход во фрагмент с "выбрать обучение"
        binding.chooseTrainingButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_catalogueFragment)
        }

        //переход во фрагмент с "избранными курсами"
        binding.favoritesCoursesButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_homeFavoritesCoursesFragment)
        }
    }

}