package com.example.app_cardapio.Models.Repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class UserRepository {

    private val auth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()

    fun authenticateUser(email: String, senha: String, onResult: (Boolean, String?) -> Unit) {
        auth.signInWithEmailAndPassword(email, senha)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onResult(true, null)
                } else {
                    val errorMessage = task.exception?.message ?: "Erro desconhecido"
                    onResult(false, errorMessage)
                }
            }
    }

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
                                onSuccess()
                            }
                            .addOnFailureListener { e ->
                                val errorMessage = e.message ?: "Erro desconhecido ao salvar dados"
                                onFailure(errorMessage)
                            }
                    } else {
                        onFailure("Erro ao obter o UID do usuário")
                    }
                } else {
                    val errorMessage = task.exception?.message ?: "Erro desconhecido ao criar usuário"
                    onFailure(errorMessage)
                }
            }
    }

    fun verificarEmailCadastrado(email: String, onResult: (Boolean, String?) -> Unit) {
        auth.fetchSignInMethodsForEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val isEmailRegistered = task.result?.signInMethods?.isNotEmpty() ?: false
                    if (isEmailRegistered) {
                        onResult(true, null)
                    } else {
                        onResult(false, "Este e-mail não está cadastrado.")
                    }
                } else {
                    val errorMessage = task.exception?.message ?: "Erro ao verificar o e-mail"
                    onResult(false, errorMessage)
                }
            }
    }

    fun alterarSenha(email: String, novaSenha: String, onResult: (Boolean, String?) -> Unit) {
        auth.signInWithEmailAndPassword(email, novaSenha)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    user?.updatePassword(novaSenha)
                        ?.addOnCompleteListener { updateTask ->
                            if (updateTask.isSuccessful) {
                                onResult(true, "Senha alterada com sucesso!")
                            } else {
                                val errorMessage = updateTask.exception?.message ?: "Erro ao alterar a senha"
                                onResult(false, errorMessage)
                            }
                        }
                } else {
                    val errorMessage = task.exception?.message ?: "Erro ao autenticar o usuário"
                    onResult(false, errorMessage)
                }
            }
    }

    fun logoutUser() {
        auth.signOut()
        Log.d("UserRepository", "Usuário deslogado com sucesso!")
    }
}
