package site.pnpl.igotit.view.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import site.pnpl.igotit.domain.usecases.FilDbWithSampleDataUseCase

class AuthViewModel(private val filDbWithSampleDataUseCase: FilDbWithSampleDataUseCase) :
    ViewModel() {

    fun fillDb() {
        viewModelScope.launch {
            filDbWithSampleDataUseCase()
        }
    }


    class Factory(private val filDbWithSampleDataUseCase: FilDbWithSampleDataUseCase) :
        ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
                return AuthViewModel(filDbWithSampleDataUseCase = filDbWithSampleDataUseCase
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}