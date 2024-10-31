package site.pnpl.igotit.view.catalogue.clubs.about_club

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import site.pnpl.igotit.domain.models.Clubs
import site.pnpl.igotit.domain.usecases.GetClubByIdFromDbUseCase
import site.pnpl.igotit.domain.usecases.SetClubsFavoriteUseCase

class AboutClubViewModel(
    private val setClubsFavoriteUseCase: SetClubsFavoriteUseCase,
    private val getClubByIdFromDbUseCase: GetClubByIdFromDbUseCase
) : ViewModel() {

    private var _isFavorites = MutableSharedFlow<Boolean>()
    val isFavorites: SharedFlow<Boolean>
        get() = _isFavorites.asSharedFlow()

    private var _club = MutableSharedFlow<Clubs>()
    val club: SharedFlow<Clubs>
        get() = _club.asSharedFlow()

    fun setFavorites(id: Int) {
        viewModelScope.launch {
            val result = setClubsFavoriteUseCase(id)
            if (result.isSuccess) {
                result.getOrNull()?.let { _isFavorites.emit(it) }
            }
        }
    }

    fun setCourseById(id: Int) {
        viewModelScope.launch {
            val result = getClubByIdFromDbUseCase(id)
            _club.emit(result)
        }
    }

    class Factory(
        private val setClubsFavoriteUseCase: SetClubsFavoriteUseCase,
        private val getClubByIdFromDbUseCase: GetClubByIdFromDbUseCase,
    ) :
        ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AboutClubViewModel::class.java)) {
                return AboutClubViewModel(
                    setClubsFavoriteUseCase = setClubsFavoriteUseCase,
                    getClubByIdFromDbUseCase = getClubByIdFromDbUseCase,
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}