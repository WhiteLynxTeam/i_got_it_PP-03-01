package site.pnpl.igotit.view.catalogue.courses

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import site.pnpl.igotit.domain.models.Clubs
import site.pnpl.igotit.domain.usecases.DownloadCoursesUseCase
import site.pnpl.igotit.domain.usecases.GetCoursesFromDbUseCase

class CoursesCatalogueViewModel(private val getCoursesFromDbUseCase: GetCoursesFromDbUseCase) : ViewModel() {
    private var _courses = MutableSharedFlow<List<Clubs>>()
    val courses: SharedFlow<List<Clubs>>
        get() = _courses.asSharedFlow()

    fun getCoursesFromDb() {
        viewModelScope.launch {
            val result = getCoursesFromDbUseCase()
            _courses.emit(result)

//            if (result.isSuccess) {
//                result.getOrNull()?.let { _courses.emit(it) }
//            }
        }
    }

    class Factory(private val getCoursesFromDbUseCase: GetCoursesFromDbUseCase):ViewModelProvider.Factory{

        override fun <T:ViewModel> create (modelClass: Class<T>):T{
            if (modelClass.isAssignableFrom(CoursesCatalogueViewModel::class.java)){
                return CoursesCatalogueViewModel(getCoursesFromDbUseCase = getCoursesFromDbUseCase) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}