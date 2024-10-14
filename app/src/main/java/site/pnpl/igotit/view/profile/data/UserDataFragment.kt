package site.pnpl.igotit.view.profile.data

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.redmadrobot.inputmask.MaskedTextChangedListener
import okhttp3.internal.format
import site.pnpl.igotit.R
import site.pnpl.igotit.databinding.FragmentUserDataBinding


class UserDataFragment : Fragment() {

    private val viewModel: UserDataViewModel by viewModels()
    private var _binding:FragmentUserDataBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserDataBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listener = MaskedTextChangedListener( "[0] ([000]) [000]-[00]-[00]", binding.numberEdit)
        binding.numberEdit.addTextChangedListener(listener)
        binding.numberEdit.onFocusChangeListener = listener
    }
    companion object {
        fun newInstance() = UserDataFragment()
    }

}