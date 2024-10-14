package site.pnpl.igotit.view.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class HomeTrainingViewModel: ViewModel(){

    class Factory(): ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(HomeTrainingViewModel::class.java)) {
                return HomeTrainingViewModel(
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}