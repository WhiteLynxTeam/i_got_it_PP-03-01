package site.pnpl.igotit.view.catalogue.courses.about_course

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import site.pnpl.igotit.R
import site.pnpl.igotit.databinding.FragmentAboutCourseBinding
import site.pnpl.igotit.view.base.BaseFragment
import javax.inject.Inject

class AboutCourseFragment : BaseFragment() {
    private var _binding: FragmentAboutCourseBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: AboutCourseViewModel

    @Inject
    lateinit var vmFactory: AboutCourseViewModel.Factory

    private val id: Int? by lazy { arguments?.getInt("id") }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAboutCourseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, vmFactory)[AboutCourseViewModel::class.java]

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.club.collect {

                with(binding) {
                    numberClasses.text = it.numberClasses
                    title.text = it.title
                    detailsDescription.text = it.about
                    level.text = it.level
                    totalQuantity.text = it.totalQuantity
                    duration.text = it.duration
                    limit.text = "пустое"
                    peerWeek.text = it.perWeek
                    if (it.isFavorite) {
                        detailsFabFavorites.setImageResource(R.drawable.ic_baseline_favorite_24)
                    } else {
                        detailsFabFavorites.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                    }

                }

            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.isFavorites.collect {
                with(binding) {
                    if (it) {
                        detailsFabFavorites.setImageResource(R.drawable.ic_baseline_favorite_24)
                    } else {
                        detailsFabFavorites.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                    }
                }
            }
        }

        binding.detailsFabFavorites.setOnClickListener {
            id?.let { id -> viewModel.setFavorites(id) }
        }

        id?.let { viewModel.setCourseById(it) }
    }

    companion object {
        fun newInstance(id: Int?): AboutCourseFragment =
            AboutCourseFragment().apply {
                arguments = Bundle().apply {
                    if (id != null) {
                        putInt("id", id)
                    }
                }
            }
    }
}
