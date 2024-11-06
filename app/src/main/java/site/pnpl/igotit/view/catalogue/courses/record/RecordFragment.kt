package site.pnpl.igotit.view.catalogue.courses.record

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch
import site.pnpl.igotit.R
import site.pnpl.igotit.databinding.FragmentRecordBinding
import site.pnpl.igotit.view.base.BaseFragment
import javax.inject.Inject

class RecordFragment : BaseFragment() {
    private var _binding: FragmentRecordBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: RecordViewModel

    @Inject
    lateinit var vmFactory: RecordViewModel.Factory

    private val id: Int? by lazy { arguments?.getInt("id") }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        listener?.onTitleTextChange(R.string.record)

        _binding = FragmentRecordBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, vmFactory)[RecordViewModel::class.java]

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.isMyCourse.collect {
                if (it) findNavController().navigate(R.id.action_detailsCoursesCatalogueFragment_to_homeFragment)
            }
        }

        binding.btnRegister.setOnClickListener {
            id?.let { id ->
                viewModel.setMyCourse(id)
            }
        }
    }

    companion object {
        fun newInstance(id: Int?): RecordFragment =
            RecordFragment().apply {
                arguments = Bundle().apply {
                    if (id != null) {
                        putInt("id", id)
                    }
                }
            }
    }
}
