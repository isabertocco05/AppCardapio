package com.example.app_cardapio.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.app_cardapio.Models.Repository.UserRepository


class LoginVM : ViewModel() {
    private val userRepository = UserRepository()

    fun authenticateUser(email: String, senha: String, onResult: (String?) -> Unit) {
        userRepository.autenticar(email, senha, onResult)
    }


}