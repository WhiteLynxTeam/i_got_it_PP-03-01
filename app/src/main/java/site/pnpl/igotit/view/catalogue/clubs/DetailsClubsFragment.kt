package site.pnpl.igotit.view.catalogue.clubs

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.google.android.material.tabs.TabLayoutMediator
import site.pnpl.igotit.R
import site.pnpl.igotit.databinding.FragmentDetailsClubsBinding
import site.pnpl.igotit.view.base.BaseFragment
import site.pnpl.igotit.view.base.BaseViewPagerAdapter
import site.pnpl.igotit.view.catalogue.clubs.about_club.AboutClubFragment
import site.pnpl.igotit.view.catalogue.clubs.record_club.RecordClubFragment

@RequiresApi(Build.VERSION_CODES.O)
class DetailsClubsFragment : BaseFragment() {

    private var _binding: FragmentDetailsClubsBinding? = null
    private val binding get() = _binding!!

    private val uuidString: String? by lazy { arguments?.getString("uuidString") }
    private val id: Int? by lazy { arguments?.getInt("id") }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsClubsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addViewPager()
    }

    private fun addViewPager(){
        binding.vpDetailsClubs.adapter = BaseViewPagerAdapter(
            this, arrayOf(
                AboutClubFragment.newInstance(id),
                RecordClubFragment.newInstance(id, uuidString)
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