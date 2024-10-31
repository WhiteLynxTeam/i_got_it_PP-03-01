package site.pnpl.igotit.view.catalogue.clubs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import site.pnpl.igotit.domain.models.Clubs
import site.pnpl.igotit.domain.usecases.GetClubsFromDbUseCase


class ClubsViewModel(private val getClubsFromDbUseCase: GetClubsFromDbUseCase)  : ViewModel(){

    private var _clubs = MutableSharedFlow<List<Clubs>>()
    val clubs: SharedFlow<List<Clubs>>
        get() = _clubs.asSharedFlow()

    fun getCoursesFromDb() {
        viewModelScope.launch {
            val result = getClubsFromDbUseCase()
            _clubs.emit(result)

//            if (result.isSuccess) {
//                result.getOrNull()?.let { _courses.emit(it) }
//            }
        }
    }

    class Factory(private val getClubsFromDbUseCase: GetClubsFromDbUseCase): ViewModelProvider.Factory{

        override fun <T:ViewModel> create (modelClass: Class<T>):T{
            if (modelClass.isAssignableFrom(ClubsViewModel::class.java)){
                return ClubsViewModel(getClubsFromDbUseCase = getClubsFromDbUseCase) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}