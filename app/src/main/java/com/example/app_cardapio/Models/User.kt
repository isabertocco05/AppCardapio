package com.example.app_cardapio.Models

data class User(
    val nomeUsuario: String,
    val nomeCompleto: String,
    val email: String,
    val senha: String,
    val confirmaSenha: String
){
    fun emailValido(): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() && email.length in 3..254
    }

    fun senhaValida(): Boolean {
        val hasLetter = senha.any { it.isLetter() }
        val hasDigit = senha.any { it.isDigit() }
        val hasSpecialChar = senha.any { !it.isLetterOrDigit() }
        return hasLetter && hasDigit && hasSpecialChar && (senha.length >= 8)
    }

    fun verificaNome(nome: String): Boolean {
        val hasLetters = nome.any { it.isLetter() }
        val hasUniqueCharacters = nome.toSet().size > 1
        val hasOnlyLettersAndSpaces = nome.all { it.isLetter() || it.isWhitespace() }
        return hasLetters && hasUniqueCharacters && hasOnlyLettersAndSpaces && nome.isNotBlank()
    }

    fun validarSenhas(senha: String, confirmaSenha: String): Boolean {
        return senha == confirmaSenha
    }



}