package site.pnpl.igotit.view.reg

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import site.pnpl.igotit.R
import site.pnpl.igotit.databinding.FragmentRegBinding
import site.pnpl.igotit.view.base.BaseFragment

class RegFragment : BaseFragment() {
    private var _binding: FragmentRegBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: RegViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        listener?.onTitleTextChange(R.string.regFragmentTitle)

        _binding = FragmentRegBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnReg.setOnClickListener {
        }
    }
}