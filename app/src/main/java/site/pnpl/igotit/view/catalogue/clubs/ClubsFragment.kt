package site.pnpl.igotit.view.catalogue.clubs

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.android.support.AndroidSupportInjection
import site.pnpl.igotit.R
import site.pnpl.igotit.databinding.FragmentClubsBinding
import site.pnpl.igotit.domain.models.Clubs

class ClubsFragment : Fragment() {

    private var _binding: FragmentClubsBinding? = null
    private val binding get() = _binding!!
    private val viewModel : ClubsViewModel by viewModels()

    private val clubsAdapter = ClubsAdapter() { title ->
        val bundle = Bundle()
        bundle.putString("title",title)
        findNavController().navigate(R.id.action_catalogueFragment_to_detailsClubsFragment, bundle)
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentClubsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rv.adapter = clubsAdapter
        initClubsRV()
    }

    companion object {
        fun newInstance() = ClubsFragment()
    }

    private fun initClubsRV() {
        val list: List<Clubs> = listOf(
            Clubs("Разговорный клуб Extra", "А1 — A2", "2 занятие", "1 раз в неделю","1 час", "10 занятий", "Прокачиваем разговорную речь, обсуждая сериал"),
            Clubs("Разговорный клуб по книге Бритни", "B1+", "2 занятие", "1 раз в неделю","1 час", "7 занятий", "Обсуждаем нашумевшую автобиографию Бритни Спирс"),
            Clubs("Разговорный клуб по новелле «История Кицунэ»", "B1+", "2 занятие", "1 раз в неделю","1 час", "10 занятий", "Окунемся в атмосферу средневековой Японии с помощью визуальной новеллы"),
            Clubs("Разговорный клуб Extra", "А1 — A2", "2 занятие", "1 раз в неделю","1 час", "10 занятий", "Прокачиваем разговорную речь, обсуждая сериал"),
            Clubs("Разговорный клуб по книге Бритни", "B1+", "2 занятие", "1 раз в неделю","1 час", "7 занятий", "Обсуждаем нашумевшую автобиографию Бритни Спирс"),
            Clubs("Разговорный клуб по новелле «История Кицунэ»", "B1+", "2 занятие", "1 раз в неделю","1 час", "10 занятий", "Окунемся в атмосферу средневековой Японии с помощью визуальной новеллы"),
        )
        clubsAdapter.setData(list)//изменить setData на логичное название
    }
}