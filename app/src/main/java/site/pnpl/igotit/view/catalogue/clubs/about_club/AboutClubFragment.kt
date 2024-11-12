package site.pnpl.igotit.view.catalogue.clubs.about_club

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import site.pnpl.igotit.R
import site.pnpl.igotit.databinding.FragmentAboutClubsBinding
import site.pnpl.igotit.view.base.BaseFragment
import javax.inject.Inject

class AboutClubFragment : BaseFragment() {
    private var _binding: FragmentAboutClubsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel : AboutClubViewModel

    @Inject
    lateinit var vmFactory: AboutClubViewModel.Factory

    private val id: Int? by lazy { arguments?.getInt("id") }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAboutClubsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, vmFactory)[AboutClubViewModel::class.java]

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.club.collect {

                with(binding) {
                    numberClasses.text = it.numberClasses
                    title.text = it.title
                    description.text = it.about
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
        fun newInstance(id: Int?): AboutClubFragment =
            AboutClubFragment().apply {
                arguments = Bundle().apply {
                    if (id != null) {
                        putInt("id", id)
                    }
                }
            }
    }

}