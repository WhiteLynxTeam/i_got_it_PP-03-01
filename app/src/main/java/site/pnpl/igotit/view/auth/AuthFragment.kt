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
import site.pnpl.igotit.view.OnHeaderChangeListener

class AuthFragment : Fragment() {
    private var _binding: FragmentAuthBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: AuthViewModel

    private var listener: OnHeaderChangeListener? = null

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)

        /***Сделать базовый класс фрагмента и перенести весь повторяющийся код для фрагментов*/
        /***И для активити на будущее тоже*/
        if (context is OnHeaderChangeListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnTextChangeListener")
        }
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

        /***Перенести в string AuthFragmentTitle*/
        listener?.onTitleTextChange("Вход")

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