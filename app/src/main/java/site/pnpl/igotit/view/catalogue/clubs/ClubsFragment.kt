package site.pnpl.igotit.view.catalogue.clubs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import site.pnpl.igotit.R

class ClubsFragment : Fragment() {

    private val viewModel : ClubsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_clubs, container, false)
    }

    companion object {
        fun newInstance() = ClubsFragment()
    }
}