package site.pnpl.igotit.view.catalogue

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import dagger.android.support.AndroidSupportInjection
import site.pnpl.igotit.R
import site.pnpl.igotit.databinding.FragmentCatalogueBinding
import site.pnpl.igotit.view.base.BaseViewPagerAdapter
import site.pnpl.igotit.view.catalogue.clubs.ClubsFragment
import site.pnpl.igotit.view.catalogue.courses.CoursesCatalogueFragment
import site.pnpl.igotit.view.catalogue.individually.IndividuallyFragment

class CatalogueFragment : Fragment() {
    private var _binding: FragmentCatalogueBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CatalogueViewModel by viewModels()

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCatalogueBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addViewPager()
    }

    private fun addViewPager(){
        binding.vpCatalog.adapter = BaseViewPagerAdapter(
            this, arrayOf(
                CoursesCatalogueFragment.newInstance(),
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