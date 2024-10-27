package site.pnpl.igotit.view.catalogue.courses.record

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import site.pnpl.igotit.R
import site.pnpl.igotit.databinding.FragmentRecordBinding
import site.pnpl.igotit.view.base.BaseFragment

class RecordFragment : BaseFragment() {
    private var _binding: FragmentRecordBinding? = null
    private val binding get() = _binding!!

    private val viewModel: RecordViewModel by viewModels()

//    override fun onAttach(context: Context) {
//        AndroidSupportInjection.inject(this)
//        super.onAttach(context)
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        listener?.onTitleTextChange(R.string.record)

        _binding = FragmentRecordBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRegister.setOnClickListener {
            findNavController().navigate(R.id.action_detailsCoursesCatalogueFragment_to_homeTrainingFragment)
        }
    }
    companion object {
        fun newInstance() = RecordFragment()
    }
}