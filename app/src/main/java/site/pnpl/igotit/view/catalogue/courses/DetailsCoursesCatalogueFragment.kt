package site.pnpl.igotit.view.catalogue.courses

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.android.support.AndroidSupportInjection
import site.pnpl.igotit.R
import site.pnpl.igotit.databinding.FragmentDetailsCoursesCatalogueBinding
import site.pnpl.igotit.view.base.BaseViewPagerAdapter
import site.pnpl.igotit.view.catalogue.courses.about_course.AboutCourseFragment
import site.pnpl.igotit.view.catalogue.courses.record.RecordFragment

class DetailsCoursesCatalogueFragment : Fragment() {
    private var _binding: FragmentDetailsCoursesCatalogueBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsCoursesCatalogueBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val title = arguments?.getString("title")
        addViewPager()
    }
    private fun addViewPager(){
        binding.vpDetailsCourses.adapter = BaseViewPagerAdapter(
            this, arrayOf(
                AboutCourseFragment.newInstance(),
                RecordFragment.newInstance()
            )
        )

        binding.vpDetailsCourses.isUserInputEnabled = false
        TabLayoutMediator(binding.tabsDetailsCourses,binding.vpDetailsCourses){tab , position ->
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