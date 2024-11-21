package com.example.app_cardapio.Models.Repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth

class UserRepository {

    private val auth = FirebaseAuth.getInstance()

    //  autenticação (login)
    fun authenticateUser(email: String, senha: String, onResult: (Boolean, String?) -> Unit) {
        auth.signInWithEmailAndPassword(email, senha)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("UserRepository", "Autenticação bem-sucedida!")
                    onResult(true, null)
                } else {
                    val errorMessage = task.exception?.message ?: "Erro desconhecido"
                    Log.e("UserRepository", "Erro ao autenticar usuário: $errorMessage")
                    onResult(false, errorMessage)
                }
            }
    }

    // Método de registro (cadastro)
    fun registerUser(email: String, senha: String, onResult: (Boolean, String?) -> Unit) {
        auth.createUserWithEmailAndPassword(email, senha)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("UserRepository", "Cadastro bem-sucedido!")
                    onResult(true, null) // Cadastro bem-sucedido
                } else {
                    val errorMessage = task.exception?.message ?: "Erro desconhecido"
                    Log.e("UserRepository", "Erro ao cadastrar usuário: $errorMessage")
                    onResult(false, errorMessage) // Falha no cadastro
                }
            }
    }

    fun logoutUser() {
        auth.signOut()
        Log.d("UserRepository", "Usuário deslogado com sucesso!")
    }
}

