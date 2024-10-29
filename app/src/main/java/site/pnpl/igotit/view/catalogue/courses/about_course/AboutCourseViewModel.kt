package site.pnpl.igotit.view.catalogue.courses.about_course

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import site.pnpl.igotit.domain.usecases.SetCoursesFavoriteUseCase

class AboutCourseViewModel(private val setCoursesFavoriteUseCase: SetCoursesFavoriteUseCase) :
    ViewModel() {

    private var _isFavorites = MutableSharedFlow<Boolean>()
    val isFavorites: SharedFlow<Boolean>
        get() = _isFavorites.asSharedFlow()

    fun setFavorites(id : Int) {
        viewModelScope.launch {
            val result = setCoursesFavoriteUseCase(id)
            _isFavorites.emit(result)
        }
    }

    class Factory(private val setCoursesFavoriteUseCase: SetCoursesFavoriteUseCase) :
        ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AboutCourseViewModel::class.java)) {
                return AboutCourseViewModel(setCoursesFavoriteUseCase = setCoursesFavoriteUseCase) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}