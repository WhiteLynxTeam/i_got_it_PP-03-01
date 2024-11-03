package site.pnpl.igotit.view.catalogue.clubs.record_club

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import site.pnpl.igotit.domain.usecases.SetCourseAsMyUseCase

class RecordClubViewModel(private val setCourseAsMyUseCase: SetCourseAsMyUseCase): ViewModel() {
    private var _isMyCourse = MutableSharedFlow<Boolean>()
    val isMyCourse: SharedFlow<Boolean>
        get() = _isMyCourse.asSharedFlow()

    fun setMyCourse(id: Int) {
        viewModelScope.launch {
            _isMyCourse.emit(setCourseAsMyUseCase(id))
        }
    }

    class Factory(
        private val setCourseAsMyUseCase: SetCourseAsMyUseCase,
    ) :
        ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(RecordClubViewModel::class.java)) {
                return RecordClubViewModel(
                    setCourseAsMyUseCase = setCourseAsMyUseCase,
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}