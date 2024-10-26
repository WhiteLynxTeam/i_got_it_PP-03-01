package site.pnpl.igotit.view.catalogue.clubs.record_club

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.android.support.AndroidSupportInjection
import site.pnpl.igotit.databinding.FragmentRecordClubBinding

class RecordClubFragment : Fragment() {

    private var _binding: FragmentRecordClubBinding? = null
    private val binding get() = _binding!!

    private val viewModel : RecordClubViewModel by viewModels()

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecordClubBinding.inflate(inflater,container,false)
        return binding.root
    }

    companion object {
        fun newInstance() = RecordClubFragment()
    }

}