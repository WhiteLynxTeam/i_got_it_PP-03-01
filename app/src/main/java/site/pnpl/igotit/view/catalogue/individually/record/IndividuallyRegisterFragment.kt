package site.pnpl.igotit.view.catalogue.individually.record

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import site.pnpl.igotit.R
import site.pnpl.igotit.databinding.FragmentIndividuallyRegisterBinding
import site.pnpl.igotit.view.base.BaseFragment
import site.pnpl.igotit.view.catalogue.individually.IndividuallyViewModel

class IndividuallyRegisterFragment : BaseFragment() {

    private var _binding: FragmentIndividuallyRegisterBinding? = null
    private val binding get() = _binding!!

    private val viewModel : IndividuallyViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        listener?.onTitleTextChange(R.string.record)

        _binding = FragmentIndividuallyRegisterBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //устанавливаем индикатор tablayout на раздел "Запись"
        binding.tlIndividually.getTabAt(1)?.select()
        //переходим в раздел мои курсы
        binding.btnRegister.setOnClickListener {
            findNavController().navigate(R.id.action_individuallyRegisterFragment_to_homeTrainingFragment)
        }
    }

}