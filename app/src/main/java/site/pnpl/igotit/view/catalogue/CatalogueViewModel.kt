package site.pnpl.igotit.view.catalogue

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import site.pnpl.igotit.domain.models.Courses
import site.pnpl.igotit.domain.usecases.DownloadCoursesUseCase

class CatalogueViewModel(private val downloadCoursesUseCase: DownloadCoursesUseCase) : ViewModel() {
    private var _courses = MutableSharedFlow<List<Courses>>()
    val courses: SharedFlow<List<Courses>>
        get() = _courses.asSharedFlow()

    fun getCourses() {
        viewModelScope.launch {
            downloadCoursesUseCase()
//            downloadCoursesUseCase().let { _courses.emit(it) }
//            if (downloadImagesUseCase()) _images.emit(getImagesFromDbUseCase())
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