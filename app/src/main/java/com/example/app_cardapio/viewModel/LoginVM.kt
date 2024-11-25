package com.example.app_cardapio.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.app_cardapio.Models.Repository.UserRepository

class LoginVM : ViewModel() {

    private val userRepository = UserRepository()

    private val _isAuthenticated = MutableLiveData<Boolean>(false)
    val isAuthenticated: LiveData<Boolean> get() = _isAuthenticated

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun authenticateUser(email: String, senha: String) {
        userRepository.authenticateUser(email, senha) { success, message ->
            _isAuthenticated.value = success
            if (!success) {
                _errorMessage.value = message
            }
        }
    }

    fun registerUser(email: String, senha: String) {
        userRepository.registerUser(email, senha) { success, message ->
            if (success) {
                _isAuthenticated.value = true
            } else {
                _errorMessage.value = message
            }
        }
    }

    fun logoutUser() {
        userRepository.logoutUser()
        _isAuthenticated.value = false
    }
}
