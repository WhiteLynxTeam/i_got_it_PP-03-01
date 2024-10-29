package site.pnpl.igotit.view.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.redmadrobot.inputmask.MaskedTextChangedListener
import site.pnpl.igotit.R
import site.pnpl.igotit.databinding.FragmentAuthBinding
import site.pnpl.igotit.view.base.BaseFragment
import javax.inject.Inject

class AuthFragment : BaseFragment() {
    private var _binding: FragmentAuthBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: AuthViewModel

    @Inject
    lateinit var vmFactory: AuthViewModel.Factory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        listener?.onTitleTextChange(R.string.authFragmentTitle)

        _binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel =
            ViewModelProvider(this, vmFactory)[AuthViewModel::class.java]

        binding.btnEnter.setOnClickListener {
            findNavController().navigate(R.id.action_authFragment_to_homeFragment)
        }

        binding.tvReg.setOnClickListener {
            findNavController().navigate(R.id.action_authFragment_to_regFragment)
        }

        binding.tvForget.setOnClickListener {
            findNavController().navigate(R.id.action_authFragment_to_restoreFragment)
        }
        binding.tvSampleDb.setOnClickListener {
            viewModel.fillDb()
        }

        val listener = MaskedTextChangedListener( "[0] ([000]) [000]-[00]-[00]", binding.etPhone)
        binding.etPhone.addTextChangedListener(listener)
        binding.etPhone.onFocusChangeListener = listener

    }
}