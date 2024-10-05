package site.pnpl.igotit.view.courses.lessons

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class LessonsViewModel : ViewModel() {


    class Factory(
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(LessonsViewModel::class.java)) {
                return LessonsViewModel(
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}