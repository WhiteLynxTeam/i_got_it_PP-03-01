package site.pnpl.igotit.view.home

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
import site.pnpl.igotit.domain.usecases.GetFirstNextLessonUseCase
import site.pnpl.igotit.domain.usecases.GetMyCoursesFromDbUseCase

@RequiresApi(Build.VERSION_CODES.O)
class HomeViewModel(
    private val getMyCoursesFromDbUseCase: GetMyCoursesFromDbUseCase,
    private val getFirstNextLessonUseCase: GetFirstNextLessonUseCase,
    ) :
    ViewModel() {
    private var _myCourses = MutableSharedFlow<List<Clubs>>()
    val myCourses: SharedFlow<List<Clubs>>
        get() = _myCourses.asSharedFlow()

    private var _lesson = MutableSharedFlow<Lesson>()
    val lesson: SharedFlow<Lesson>
        get() = _lesson.asSharedFlow()

    init {
        getMyCourses()
        getFirstNextLesson()
    }

    fun getMyCourses() {
        viewModelScope.launch {
            _myCourses.emit(getMyCoursesFromDbUseCase())
        }
    }

    fun getFirstNextLesson() {
        viewModelScope.launch {
            val lessonFirst = getFirstNextLessonUseCase()
            if (lessonFirst != null) {
                _lesson.emit(lessonFirst)
            }
        }
    }

    class Factory(
        private val getMyCoursesFromDbUseCase: GetMyCoursesFromDbUseCase,
        private val getFirstNextLessonUseCase: GetFirstNextLessonUseCase,
    ) :
        ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
                return HomeViewModel(
                    getMyCoursesFromDbUseCase = getMyCoursesFromDbUseCase,
                    getFirstNextLessonUseCase = getFirstNextLessonUseCase,
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}