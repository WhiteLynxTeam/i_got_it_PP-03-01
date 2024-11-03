package site.pnpl.igotit.view.catalogue.clubs.record_club

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch
import site.pnpl.igotit.R
import site.pnpl.igotit.databinding.FragmentRecordClubBinding
import site.pnpl.igotit.view.base.BaseFragment
import site.pnpl.igotit.view.catalogue.courses.record.RecordFragment
import javax.inject.Inject

class RecordClubFragment : BaseFragment() {

    private var _binding: FragmentRecordClubBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: RecordClubViewModel

    @Inject
    lateinit var vmFactory: RecordClubViewModel.Factory

    private val id: Int? by lazy { arguments?.getInt("id") }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        listener?.onTitleTextChange(R.string.record)

        _binding = FragmentRecordClubBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, vmFactory)[RecordClubViewModel::class.java]

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.isMyCourse.collect {
                if (it) findNavController().navigate(R.id.action_detailsClubsFragment_to_homeFragment)
            }
        }

        binding.btnRegister.setOnClickListener {
            id?.let { id -> viewModel.setMyCourse(id) }
        }
    }

    companion object {
        fun newInstance(id: Int?): RecordClubFragment =
            RecordClubFragment().apply {
                arguments = Bundle().apply {
                    if (id != null) {
                        putInt("id", id)
                    }
                }
            }
    }

}