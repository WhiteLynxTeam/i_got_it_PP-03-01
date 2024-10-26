package site.pnpl.igotit.view.catalogue.individually

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.AndroidSupportInjection
import site.pnpl.igotit.R
import site.pnpl.igotit.databinding.FragmentIndividuallyBinding


class IndividuallyFragment : Fragment() {

    private var _binding: FragmentIndividuallyBinding? = null
    private val binding get() = _binding!!

    private val viewModel : IndividuallyViewModel by viewModels()

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentIndividuallyBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRegister.setOnClickListener {
            //проверяем выбран ли вариант ответа
            if (binding.radioBtn1.isChecked || binding.radioBtn2.isChecked || binding.radioBtn3.isChecked) {
                findNavController().navigate(R.id.action_catalogueFragment_to_individuallyRegisterFragment)
            }
            //выводим сообщение если не выбран ни один из вариантов
            else Toast.makeText(requireContext(), "Выберите один из вариантов ответа", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        fun newInstance() = IndividuallyFragment()
    }
}