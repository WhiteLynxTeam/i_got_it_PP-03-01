package site.pnpl.igotit.view.courses.lessons

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import site.pnpl.igotit.domain.models.Clubs
import site.pnpl.igotit.domain.models.Lesson
import site.pnpl.igotit.domain.usecases.GetCourseClubByUuidDbUseCase
import site.pnpl.igotit.domain.usecases.GetLessonsByUuidCourseBDUseCase
import java.util.UUID

class LessonsViewModel(
    private val getCourseClubByUuidDbUseCase: GetCourseClubByUuidDbUseCase,
    private val getLessonsByUuidCourseBDUseCase: GetLessonsByUuidCourseBDUseCase,
) : ViewModel() {
    private var _lesson = MutableSharedFlow<Pair<Clubs, List<Lesson>>>()
    val lesson: SharedFlow<Pair<Clubs, List<Lesson>>>
        get() = _lesson.asSharedFlow()

    private var _club = MutableSharedFlow<Clubs>()
    val club: SharedFlow<Clubs>
        get() = _club.asSharedFlow()

    @RequiresApi(Build.VERSION_CODES.O)
    fun getLesson(uuidCourse: UUID) {
        viewModelScope.launch {
            val resultClub = getCourseClubByUuidDbUseCase(uuidCourse)
            _club.emit(resultClub)

            _lesson.emit(Pair(resultClub, getLessonsByUuidCourseBDUseCase(uuidCourse)))
        }
    }

    class Factory(
        private val getCourseClubByUuidDbUseCase: GetCourseClubByUuidDbUseCase,
        private val getLessonsByUuidCourseBDUseCase: GetLessonsByUuidCourseBDUseCase,
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(LessonsViewModel::class.java)) {
                return LessonsViewModel(
                    getCourseClubByUuidDbUseCase = getCourseClubByUuidDbUseCase,
                    getLessonsByUuidCourseBDUseCase = getLessonsByUuidCourseBDUseCase,
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}