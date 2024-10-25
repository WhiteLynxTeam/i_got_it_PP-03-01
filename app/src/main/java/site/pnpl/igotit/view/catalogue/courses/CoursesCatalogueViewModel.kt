package site.pnpl.igotit.view.catalogue.courses

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CoursesCatalogueViewModel : ViewModel() {

    class Factory():ViewModelProvider.Factory{

        override fun <T:ViewModel> create (modelClass: Class<T>):T{
            if (modelClass.isAssignableFrom(CoursesCatalogueViewModel::class.java)){
                return CoursesCatalogueViewModel() as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}