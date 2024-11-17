package site.pnpl.igotit.view.courses.remove

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import site.pnpl.igotit.domain.models.Clubs
import site.pnpl.igotit.domain.usecases.DelMyCourseClubDbUseCase
import site.pnpl.igotit.domain.usecases.GetCourseClubByUuidDbUseCase
import java.util.UUID

class RemoveCourseViewModel(
    private val getCourseClubByUuidDbUseCase: GetCourseClubByUuidDbUseCase,
    private val delMyCourseClubDbUseCase: DelMyCourseClubDbUseCase,
) : ViewModel() {
    private var _club = MutableSharedFlow<Clubs>()
    val club: SharedFlow<Clubs>
        get() = _club.asSharedFlow()

    fun getCourse(uuidCourse: UUID) {
        viewModelScope.launch {
            val resultClub = getCourseClubByUuidDbUseCase(uuidCourse)
            _club.emit(resultClub)
        }
    }

    fun delCourse(uuidCourse: UUID) {
        viewModelScope.launch {
            delMyCourseClubDbUseCase(uuidCourse)
//            _club.emit(resultClub)
        }
    }

    class Factory(
        private val getCourseClubByUuidDbUseCase: GetCourseClubByUuidDbUseCase,
        private val delMyCourseClubDbUseCase: DelMyCourseClubDbUseCase,
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(RemoveCourseViewModel::class.java)) {
                return RemoveCourseViewModel(
                    getCourseClubByUuidDbUseCase = getCourseClubByUuidDbUseCase,
                    delMyCourseClubDbUseCase = delMyCourseClubDbUseCase,
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}