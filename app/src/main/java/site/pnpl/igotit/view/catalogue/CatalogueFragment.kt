package site.pnpl.igotit.view.catalogue

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.launch
import site.pnpl.igotit.R
import site.pnpl.igotit.databinding.FragmentCatalogueBinding
import site.pnpl.igotit.domain.models.Clubs
import site.pnpl.igotit.view.base.BaseFragment
import site.pnpl.igotit.view.base.BaseViewPagerAdapter
import site.pnpl.igotit.view.catalogue.clubs.ClubsFragment
import site.pnpl.igotit.view.catalogue.courses.CoursesCatalogueFragment
import site.pnpl.igotit.view.catalogue.individually.IndividuallyFragment
import javax.inject.Inject

class CatalogueFragment : BaseFragment() {
    private var _binding: FragmentCatalogueBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: CatalogueViewModel

    @Inject
    lateinit var vmFactory: CatalogueViewModel.Factory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        listener?.onTitleTextChange(R.string.catalogue)

        _binding = FragmentCatalogueBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel =
            ViewModelProvider(this, vmFactory)[CatalogueViewModel::class.java]

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.courses.collect {

                println("List<Clubs> $it")

                addViewPager(it)
            }
        }

        viewModel.getCourses()

        addViewPager(null)
    }

    private fun addViewPager(listClubs: List<Clubs>?){
        binding.vpCatalog.adapter = BaseViewPagerAdapter(
            this, arrayOf(
                CoursesCatalogueFragment.newInstance(listClubs),
                ClubsFragment.newInstance(),
                IndividuallyFragment.newInstance()
            )
        )

        binding.vpCatalog.isUserInputEnabled = false
        TabLayoutMediator(binding.tabsCatalog,binding.vpCatalog){tab,position ->
            when(position){
                0 -> {
                    tab.text = getString(R.string.courses)
                }
                1 -> {
                    tab.text = getString(R.string.clubs)
                }
                2 -> {
                    tab.text = getString(R.string.individually)
                }
            }
        }.attach()
    }
}