package com.example.app_cardapio.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.app_cardapio.Models.Repository.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class CriarContaVM() : ViewModel() {
    private val _statusCadastro = MutableLiveData<String>()
    val statusCadastro: LiveData<String> = _statusCadastro
    private val userRepository = UserRepository()

    val usuario = FirebaseAuth.getInstance().currentUser
    val uid = usuario?.uid ?: ""


    fun validarSenhas(senha: String, confirmaSenha: String): Boolean {
        return senha == confirmaSenha
    }
    fun cadastrarUsuario(nomeUsuario: String, nomeCompleto: String, email: String, senha: String) {

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = hashMapOf(
                        "nome_usuario" to nomeUsuario,
                        "nome_completo" to nomeCompleto,
                        "email" to email
                    )

                    val db = FirebaseFirestore.getInstance()

                    db.collection("usuarios")
                        .document("cadastro_usuarios")
                        .collection("cadastros")
                        .document(nomeCompleto)
                        .set(user)
                        .addOnSuccessListener { documentReference ->
                            _statusCadastro.postValue("Cadastro realizado com sucesso!")
                        }
                        .addOnFailureListener { e ->
                            _statusCadastro.postValue("Erro ao cadastrar: ${e.message}")
                        }
                    _statusCadastro.postValue("Usuário cadastrado com sucesso!")
                } else {
                    _statusCadastro.postValue("Erro ao criar usuário")
                }
            }
    }


}
