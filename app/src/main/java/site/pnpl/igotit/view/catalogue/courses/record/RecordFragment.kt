package site.pnpl.igotit.view.catalogue.courses.record

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.android.support.AndroidSupportInjection
import site.pnpl.igotit.R
import site.pnpl.igotit.databinding.FragmentRecordBinding

class RecordFragment : Fragment() {
    private var _binding: FragmentRecordBinding? = null
    private val binding get() = _binding!!

    private val viewModel: RecordViewModel by viewModels()

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecordBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRegister.setOnClickListener {
            findNavController().navigate(R.id.action_recordClubFragment2_to_homeTrainingFragment)
        }
    }
    companion object {
        fun newInstance() = RecordFragment()
    }
}