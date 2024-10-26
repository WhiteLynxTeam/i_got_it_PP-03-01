package site.pnpl.igotit.view.catalogue.individually.record

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.android.support.AndroidSupportInjection
import site.pnpl.igotit.databinding.FragmentIndividuallyRegisterBinding
import site.pnpl.igotit.view.catalogue.individually.IndividuallyViewModel

class IndividuallyRegisterFragment : Fragment() {

    private var _binding: FragmentIndividuallyRegisterBinding? = null
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
        _binding = FragmentIndividuallyRegisterBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tlIndividually.getTabAt(1)?.select()
    }

}