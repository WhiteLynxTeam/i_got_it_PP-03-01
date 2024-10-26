package site.pnpl.igotit.view.catalogue.clubs.about_club

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.android.support.AndroidSupportInjection
import site.pnpl.igotit.databinding.FragmentAboutClubsBinding

class AboutClubFragment : Fragment() {

    private var _binding: FragmentAboutClubsBinding? = null
    private val binding get() = _binding!!
    private val viewModel : AboutClubViewModel by viewModels()

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAboutClubsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    companion object {
        fun newInstance() = AboutClubFragment()
    }

}