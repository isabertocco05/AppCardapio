package com.example.app_cardapio.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.app_cardapio.Models.User
//import com.google.firebase.auth.FirebaseAuth

class CriarContaVM(private val context: Context) : ViewModel() {
//    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    fun inputValido(
        nomeUsuario: String,
        nomeCompleto: String,
        email: String,
        senha: String,
        confirmaSenha: String,
        onResult: (String?) -> Unit
    ){
        if (nomeUsuario.isEmpty() || nomeCompleto.isEmpty() || email.isEmpty() || senha.isEmpty() || confirmaSenha.isEmpty()) {
            onResult("Todos os campos devem ser preenchidos.")
            return
        }

        val user = User(nomeUsuario, nomeCompleto, email, senha, confirmaSenha)

        if (!user.verificaNome(nomeUsuario)) {
            onResult("O nome de usuário deve conter letras diferentes e não pode ser apenas números.")
            return
        }
        if (!user.verificaNome(nomeCompleto)) {
            onResult("O nome completo deve conter letras diferentes e não pode ser números.")
            return
        }
        if (!user.emailValido()) {
            onResult("Email inválido.")
            return
        }
        if (!user.senhaValida()) {
            onResult("A senha deve ter pelo menos 8 dígitos e conter, pelo menos, uma letra, um número e um caractere especial.")
            return
        }
        if (!user.verificaConfirmacao(senha, confirmaSenha)) {
            onResult("As senhas não são iguais.")
            return
        }

//        // Criar o usuário no Firebase
//        user.createUser(firebaseAuth) { success, errorMessage ->
//            if (success) {
//                onResult(null) // Usuário criado com sucesso
//            } else {
//                onResult(errorMessage) // Mensagem de erro
//            }
//        }

    }

}