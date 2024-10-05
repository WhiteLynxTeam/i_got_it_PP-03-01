package site.pnpl.igotit.view.courses.remove

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import site.pnpl.igotit.R

class RemoveCourseFragment : Fragment() {


    private val viewModel: RemoveCourseViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_remove_course, container, false)
    }

    companion object {
        fun newInstance() = RemoveCourseFragment()
    }
}