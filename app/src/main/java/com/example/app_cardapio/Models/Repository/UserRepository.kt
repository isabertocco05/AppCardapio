package com.example.app_cardapio.Models.Repository

import com.example.app_cardapio.Models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class UserRepository {

    private val auth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()

    fun cadastrarUsuario(
        nomeUsuario: String,
        nomeCompleto: String,
        email: String,
        senha: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        // Cria o usuário no Firebase Authentication
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
                        // Salva os dados adicionais no Firestore
                        firestore.collection("usuarios").document(userId)
                            .set(usuarioData)
                            .addOnSuccessListener {
                                onSuccess()
                            }
                            .addOnFailureListener { e ->
                                onFailure(e.message ?: "Erro desconhecido ao salvar dados")
                            }
                    } else {
                        onFailure("Erro ao obter o UID do usuário")
                    }
                } else {
                    onFailure(task.exception?.message ?: "Erro desconhecido ao criar usuário")
                }
            }
    }
}
