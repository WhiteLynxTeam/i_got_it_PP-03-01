package site.pnpl.igotit.view.catalogue.courses.about_course

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import site.pnpl.igotit.domain.models.Clubs
import site.pnpl.igotit.domain.usecases.GetCourseByIdFromDbUseCase
import site.pnpl.igotit.domain.usecases.SetCoursesFavoriteUseCase

class AboutCourseViewModel(
    private val setCoursesFavoriteUseCase: SetCoursesFavoriteUseCase,
    private val getCourseByIdFromDbUseCase: GetCourseByIdFromDbUseCase,
) :
    ViewModel() {

    private var _isFavorites = MutableSharedFlow<Boolean>()
    val isFavorites: SharedFlow<Boolean>
        get() = _isFavorites.asSharedFlow()

    private var _club = MutableSharedFlow<Clubs>()
    val club: SharedFlow<Clubs>
        get() = _club.asSharedFlow()

    fun setFavorites(id: Int) {
        viewModelScope.launch {
            val result = setCoursesFavoriteUseCase(id)
            _isFavorites.emit(result)
        }
    }

    fun setCourseById(id: Int) {
        viewModelScope.launch {
            val result = getCourseByIdFromDbUseCase(id)
            _club.emit(result)
        }
    }

    class Factory(
        private val setCoursesFavoriteUseCase: SetCoursesFavoriteUseCase,
        private val getCourseByIdFromDbUseCase: GetCourseByIdFromDbUseCase,
    ) :
        ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AboutCourseViewModel::class.java)) {
                return AboutCourseViewModel(
                    setCoursesFavoriteUseCase = setCoursesFavoriteUseCase,
                    getCourseByIdFromDbUseCase = getCourseByIdFromDbUseCase,
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}