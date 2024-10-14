package site.pnpl.igotit.view.auth

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.redmadrobot.inputmask.MaskedTextChangedListener
import dagger.android.support.AndroidSupportInjection
import site.pnpl.igotit.R
import site.pnpl.igotit.databinding.FragmentAuthBinding

class AuthFragment : Fragment() {
    private var _binding: FragmentAuthBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: AuthViewModel

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnEnter.setOnClickListener {
            findNavController().navigate(R.id.action_authFragment_to_homeFragment)
        }

        binding.tvReg.setOnClickListener {
            findNavController().navigate(R.id.action_authFragment_to_regFragment)
        }

        binding.tvForget.setOnClickListener {
            findNavController().navigate(R.id.action_authFragment_to_restoreFragment)
        }

        val listener = MaskedTextChangedListener( "[0] ([000]) [000]-[00]-[00]", binding.etPhone)
        binding.etPhone.addTextChangedListener(listener)
        binding.etPhone.onFocusChangeListener = listener

    }
}