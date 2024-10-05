package site.pnpl.igotit.view.profile.about

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import site.pnpl.igotit.R
import site.pnpl.igotit.view.courses.calendar.CalendarViewModel
import site.pnpl.igotit.view.profile.data.UserDataFragment

class AboutFragment : Fragment() {

    private val viewModel: AboutViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about, container, false)
    }
    companion object{
        fun newInstance() = AboutFragment()
    }
}