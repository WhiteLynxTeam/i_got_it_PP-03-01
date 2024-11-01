package site.pnpl.igotit.view.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import site.pnpl.igotit.domain.models.Clubs
import site.pnpl.igotit.domain.usecases.GetMyCoursesFromDbUseCase

class HomeViewModel(private val getMyCoursesFromDbUseCase: GetMyCoursesFromDbUseCase) :
    ViewModel() {
    private var _myCourses = MutableSharedFlow<List<Clubs>>()
    val myCourses: SharedFlow<List<Clubs>>
        get() = _myCourses.asSharedFlow()

    init {
        getMyCourses()
    }

    fun getMyCourses() {
        viewModelScope.launch {
            _myCourses.emit(getMyCoursesFromDbUseCase())
        }
    }

    class Factory(
        private val getMyCoursesFromDbUseCase: GetMyCoursesFromDbUseCase,
    ) :
        ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
                return HomeViewModel(
                    getMyCoursesFromDbUseCase = getMyCoursesFromDbUseCase,
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}