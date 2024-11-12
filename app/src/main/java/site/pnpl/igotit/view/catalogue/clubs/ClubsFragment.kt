package site.pnpl.igotit.view.catalogue.clubs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch
import site.pnpl.igotit.R
import site.pnpl.igotit.databinding.FragmentClubsBinding
import site.pnpl.igotit.view.base.BaseFragment
import javax.inject.Inject

class ClubsFragment : BaseFragment() {

    private var _binding: FragmentClubsBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ClubsViewModel

    @Inject
    lateinit var vmFactory: ClubsViewModel.Factory

    private val clubsAdapter = ClubsAdapter() { id, uuid ->

        findNavController().navigate(
            R.id.action_catalogueFragment_to_detailsClubsFragment,
            Bundle().apply {
                putInt("id", id)
                putString("uuidString", uuid.toString())
            })
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

        viewModel = ViewModelProvider(this,vmFactory)[ClubsViewModel::class.java]

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.clubs.collect {
                clubsAdapter.setData(it)
            }
        }

        binding.rv.adapter = clubsAdapter

        viewModel.getCoursesFromDb()
    }

    companion object {
        fun newInstance() = ClubsFragment()
    }

    private fun initClubsRV() {
//        val list: List<Clubs> = listOf(
//            Clubs("Разговорный клуб Extra", "А1 — A2", "2 занятие", "1 раз в неделю","1 час", "10 занятий", "Прокачиваем разговорную речь, обсуждая сериал"),
//            Clubs("Разговорный клуб по книге Бритни", "B1+", "2 занятие", "1 раз в неделю","1 час", "7 занятий", "Обсуждаем нашумевшую автобиографию Бритни Спирс"),
//            Clubs("Разговорный клуб по новелле «История Кицунэ»", "B1+", "2 занятие", "1 раз в неделю","1 час", "10 занятий", "Окунемся в атмосферу средневековой Японии с помощью визуальной новеллы"),
//            Clubs("Разговорный клуб Extra", "А1 — A2", "2 занятие", "1 раз в неделю","1 час", "10 занятий", "Прокачиваем разговорную речь, обсуждая сериал"),
//            Clubs("Разговорный клуб по книге Бритни", "B1+", "2 занятие", "1 раз в неделю","1 час", "7 занятий", "Обсуждаем нашумевшую автобиографию Бритни Спирс"),
//            Clubs("Разговорный клуб по новелле «История Кицунэ»", "B1+", "2 занятие", "1 раз в неделю","1 час", "10 занятий", "Окунемся в атмосферу средневековой Японии с помощью визуальной новеллы"),
//        )
//        clubsAdapter.setData(list)//изменить setData на логичное название
    }
}