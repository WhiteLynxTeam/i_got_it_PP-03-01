package site.pnpl.igotit.view.catalogue

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import site.pnpl.igotit.domain.models.Clubs
import site.pnpl.igotit.domain.models.Courses
import site.pnpl.igotit.domain.usecases.DownloadCoursesUseCase

class CatalogueViewModel(private val downloadCoursesUseCase: DownloadCoursesUseCase) : ViewModel() {
    private var _courses = MutableSharedFlow<List<Clubs>>()
    val courses: SharedFlow<List<Clubs>>
        get() = _courses.asSharedFlow()

    fun getCourses() {
        viewModelScope.launch {
            val result = downloadCoursesUseCase()
            if (result.isSuccess) {
                result.getOrNull()?.let { _courses.emit(it) }
            }
        }
    }




    class Factory(private val downloadCoursesUseCase: DownloadCoursesUseCase
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CatalogueViewModel::class.java)) {
                return CatalogueViewModel(downloadCoursesUseCase = downloadCoursesUseCase
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}