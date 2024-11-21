package com.example.app_cardapio.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.app_cardapio.Models.User
import com.example.app_cardapio.Models.Repository.UserRepository

class CriarContaVM : ViewModel() {

    private val userRepository = UserRepository()

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _success = MutableLiveData<Boolean>()
    val success: LiveData<Boolean> get() = _success

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> get() = _errorMessage

    fun validarSenhas(senha: String, confirmaSenha: String): Boolean {
        return senha == confirmaSenha
    }

    fun criarConta(user: User) {
        _isLoading.value = true
        _errorMessage.value = null
        if (validarSenhas(user.senha, user.confirmaSenha)) {
            userRepository.registerUser(user) { isSuccessful, message ->
                _isLoading.value = false
                if (isSuccessful) {
                    _success.value = true
                } else {
                    _errorMessage.value = message
                    _success.value = false
                }

            }
        }
    }
}
