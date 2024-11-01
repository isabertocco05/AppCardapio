package com.example.app_cardapio.Repository

import com.example.app_cardapio.Models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class UserRepository{
    private val firebaseAuth: FirebaseAuth,
    private val database: FirebaseDatabase,
    private val autentica: FirebaseAuth = FirebaseAuth.getInstance()

    fun createUser(user: User, onComplete: (Boolean, String?) -> Unit) {
        firebaseAuth.createUserWithEmailAndPassword(user.email, user.senha)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val userId = firebaseAuth.currentUser?.uid
                    val dbRef = database.getReference("users").child(userId!!)
                    dbRef.setValue(user).addOnCompleteListener { dbTask ->
                        if (dbTask.isSuccessful) {
                            onComplete(true, null) // Usuário criado com sucesso
                        } else {
                            onComplete(false, dbTask.exception?.message) // Erro ao armazenar dados
                        }
                    }
                } else {
                    onComplete(false, task.exception?.message) // Erro ao criar usuário
                }
            }
    }

    fun autenticar (user: User, onResult: (String?) -> Unit){
        if (user.email.isEmpty() || user.senha.isEmpty() ){
            onResult("Todos os campos devem estar preenchidos")
            return
        }
    }
    // api do firebase
//    auth.signInWithEmailAndPassword(user.email, user.senha)
//        .addOnCompleteListener { task ->
//        if (task.isSuccessful) {
//            onResult(null) // Autenticação bem-sucedida
//        } else {
//            onResult("Email ou senha incorretos.")
//        }
//    }



}