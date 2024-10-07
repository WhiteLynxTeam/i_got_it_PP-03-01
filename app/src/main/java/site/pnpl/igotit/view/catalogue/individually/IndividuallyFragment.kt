package site.pnpl.igotit.view.catalogue.individually

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import site.pnpl.igotit.R


class IndividuallyFragment : Fragment() {

    private val viewModel : IndividuallyViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_individually, container, false)
    }

    companion object {
        fun newInstance() = IndividuallyFragment()
    }
}