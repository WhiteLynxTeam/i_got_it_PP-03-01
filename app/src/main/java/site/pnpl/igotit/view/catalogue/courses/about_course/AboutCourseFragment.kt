package site.pnpl.igotit.view.catalogue.courses.about_course

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import site.pnpl.igotit.R
import site.pnpl.igotit.databinding.FragmentAboutCourseBinding
import site.pnpl.igotit.databinding.FragmentAuthBinding
import site.pnpl.igotit.view.courses.lessons.LessonsFragment

class AboutCourseFragment : Fragment() {
    private var _binding: FragmentAboutCourseBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about_course, container, false)
    }
    companion object {
        fun newInstance(title: String?):AboutCourseFragment = AboutCourseFragment().apply {
            arguments = Bundle().apply {
                putString("title", title)
            }
        }
    }
}