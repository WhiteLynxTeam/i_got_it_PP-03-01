package site.pnpl.igotit.view.catalogue.clubs

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import dagger.android.support.AndroidSupportInjection
import site.pnpl.igotit.R
import site.pnpl.igotit.databinding.FragmentDetailsClubsBinding
import site.pnpl.igotit.view.base.BaseViewPagerAdapter
import site.pnpl.igotit.view.catalogue.clubs.about_club.AboutClubFragment
import site.pnpl.igotit.view.catalogue.clubs.record_club.RecordClubFragment

class DetailsClubsFragment : Fragment() {

    private var _binding: FragmentDetailsClubsBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsClubsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val title = arguments?.getString("title")
        addViewPager()
    }

    private fun addViewPager(){
        binding.vpDetailsClubs.adapter = BaseViewPagerAdapter(
            this, arrayOf(
                AboutClubFragment.newInstance(),
                RecordClubFragment.newInstance()
            )
        )

        binding.vpDetailsClubs.isUserInputEnabled = false
        TabLayoutMediator(binding.tlDetailsClubs,binding.vpDetailsClubs){tab , position ->
            when(position) {
                0 -> {
                    tab.text = getString(R.string.about_course)
                }
                1 -> {
                    tab.text=getString(R.string.record)
                }
            }
        }.attach()
    }

}