package com.example.app_cardapio.Models.Repository

import android.util.Log
import com.example.app_cardapio.Models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class UserRepository {

    private val auth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()

    // Autenticação (Login)
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

    // Registro simples (somente email e senha)
    fun registerUser(email: String, senha: String, onResult: (Boolean, String?) -> Unit) {
        auth.createUserWithEmailAndPassword(email, senha)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("UserRepository", "Cadastro bem-sucedido!")
                    onResult(true, null)
                } else {
                    val errorMessage = task.exception?.message ?: "Erro desconhecido"
                    Log.e("UserRepository", "Erro ao cadastrar usuário: $errorMessage")
                    onResult(false, errorMessage)
                }
            }
    }

    // Registro com dados adicionais no Firestore
    fun cadastrarUsuario(
        nomeUsuario: String,
        nomeCompleto: String,
        email: String,
        senha: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, senha)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val userId = auth.currentUser?.uid
                    if (userId != null) {
                        val usuarioData = hashMapOf(
                            "nome_usuario" to nomeUsuario,
                            "nome_completo" to nomeCompleto,
                            "email" to email
                        )
                        firestore.collection("usuarios").document(userId)
                            .set(usuarioData)
                            .addOnSuccessListener {
                                Log.d("UserRepository", "Dados adicionais salvos com sucesso no Firestore!")
                                onSuccess()
                            }
                            .addOnFailureListener { e ->
                                val errorMessage = e.message ?: "Erro desconhecido ao salvar dados"
                                Log.e("UserRepository", errorMessage)
                                onFailure(errorMessage)
                            }
                    } else {
                        onFailure("Erro ao obter o UID do usuário")
                    }
                } else {
                    val errorMessage = task.exception?.message ?: "Erro desconhecido ao criar usuário"
                    Log.e("UserRepository", errorMessage)
                    onFailure(errorMessage)
                }
            }
    }

    // Logout
    fun logoutUser() {
        auth.signOut()
        Log.d("UserRepository", "Usuário deslogado com sucesso!")
    }
}
