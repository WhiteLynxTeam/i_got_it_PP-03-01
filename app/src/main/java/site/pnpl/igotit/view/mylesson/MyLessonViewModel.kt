package site.pnpl.igotit.view.mylesson

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import site.pnpl.igotit.domain.models.Lesson
import site.pnpl.igotit.domain.usecases.GetAllLessonsBdUseCase

class MyLessonViewModel(
    private val getAllLessonsBdUseCase: GetAllLessonsBdUseCase,
) : ViewModel() {
    private var _lesson = MutableSharedFlow<List<Lesson>>()
    val lesson: SharedFlow<List<Lesson>>
        get() = _lesson.asSharedFlow()

    fun getLessons() {
        viewModelScope.launch {
            _lesson.emit(getAllLessonsBdUseCase())
        }
    }

    class Factory(
        private val getAllLessonsBdUseCase: GetAllLessonsBdUseCase,
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MyLessonViewModel::class.java)) {
                return MyLessonViewModel(
                    getAllLessonsBdUseCase = getAllLessonsBdUseCase,
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}