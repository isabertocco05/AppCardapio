package com.example.app_cardapio.ViewModels

import android.content.Context
import com.example.app_cardapio.Repository.UserRepository
import com.example.app_cardapio.Models.User
import com.google.firebase.auth.FirebaseAuth

class LoginVM (private val context: Context) {
    private val userRepository = UserRepository()

    fun authenticateUser(user: User, onResult: (String?) -> Unit) {
        userRepository.autenticar(user, onResult)
    }
}