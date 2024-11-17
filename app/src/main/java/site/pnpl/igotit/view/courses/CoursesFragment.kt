package site.pnpl.igotit.view.courses

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.android.support.AndroidSupportInjection
import site.pnpl.igotit.R
import site.pnpl.igotit.databinding.FragmentCoursesBinding
import site.pnpl.igotit.view.base.BaseViewPagerAdapter
import site.pnpl.igotit.view.courses.calendar.CalendarFragment
import site.pnpl.igotit.view.courses.lessons.LessonsFragment
import site.pnpl.igotit.view.courses.remove.RemoveCourseFragment

class CoursesFragment : Fragment() {
    private var _binding: FragmentCoursesBinding? = null
    private val binding get() = _binding!!

    private val uuidString: String? by lazy { arguments?.getString("uuidString") }

    private lateinit var viewModel: CoursesViewModel

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoursesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addViewPager()
    }

    private fun addViewPager() {
        binding.vpCourses.adapter = BaseViewPagerAdapter(
            this, arrayOf(
                LessonsFragment.newInstance(uuidString),
                CalendarFragment.newInstance(),
                RemoveCourseFragment.newInstance(uuidString)
            )
        )

        binding.vpCourses.isUserInputEnabled = false
        TabLayoutMediator(binding.tabsCourses, binding.vpCourses) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = getString(R.string.lessons)
                }

                1 -> {
                    tab.text = getString(R.string.calendar)
                }

                2 -> {
                    tab.text = getString(R.string.remove)
                }
            }
        }.attach()
    }
}