package com.example.app_cardapio.Models.Repository

import com.example.app_cardapio.Models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class UserRepository {
    private val auth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()

    fun registerUser(user: User, onResult: (Boolean, String?) -> Unit) {

        auth.createUserWithEmailAndPassword(user.email, user.senha)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val userId = auth.currentUser?.uid ?: ""
                    val userData = mapOf(
                        "nomeUsuario" to user.nomeUsuario,
                        "nomeCompleto" to user.nomeCompleto,
                        "email" to user.email
                    )
                    firestore.collection("usuarios").document(userId).set(userData)
                        .addOnSuccessListener {
                            onResult(true, null)
                        }
                        .addOnFailureListener { e ->
                            onResult(false, "Erro ao salvar dados: ${e.message}")
                        }
                } else {
                    onResult(false, task.exception?.message)
                }
            }
    }
}
