package site.pnpl.igotit.view.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import site.pnpl.igotit.R
import site.pnpl.igotit.databinding.FragmentProfileBinding
import site.pnpl.igotit.view.base.BaseFragment
import site.pnpl.igotit.view.base.BaseViewPagerAdapter
import site.pnpl.igotit.view.profile.about.AboutFragment
import site.pnpl.igotit.view.profile.data.UserDataFragment
import site.pnpl.igotit.view.profile.setting.SettingsFragment

class ProfileFragment : BaseFragment() {
    private var _binding:FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ProfileViewModel

//    override fun onAttach(context: Context) {
//        AndroidSupportInjection.inject(this)
//        super.onAttach(context)
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        listener?.onTitleTextChange(R.string.profile)

        _binding = FragmentProfileBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addViewPager()
    }

    private fun addViewPager() {
        binding.vpProfile.adapter = BaseViewPagerAdapter(
            this, arrayOf(
                UserDataFragment.newInstance(),
                SettingsFragment.newInstance(),
                AboutFragment.newInstance()
            )
        )

        binding.vpProfile.isUserInputEnabled= false
        TabLayoutMediator(binding.tabsProfile,binding.vpProfile){tab,position ->
            when(position) {
                0 -> {
                    tab.text = getString(R.string.user_data)
                }
                1 -> {
                    tab.text = getString(R.string.settings)
                }
                2 -> {
                    tab.text = getString(R.string.about)
                }
            }
        }.attach()
    }
}