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

    fun verificaConfirmacao(senha: String, senhaConfirm: String): Boolean {
        return senha == senhaConfirm
    }


//    // Método para criar o usuário no Firebase
//    fun createUser(firebaseAuth: FirebaseAuth, onComplete: (Boolean, String?) -> Unit) {
//        firebaseAuth.createUserWithEmailAndPassword(email, senha)
//            .addOnCompleteListener { task ->
//                if (task.isSuccessful) {
//                    // Armazenar dados adicionais no Firebase Realtime Database
//                    val userId = firebaseAuth.currentUser?.uid
//                    val database = FirebaseDatabase.getInstance().getReference("users").child(userId!!)
//                    database.setValue(this).addOnCompleteListener { dbTask ->
//                        if (dbTask.isSuccessful) {
//                            onComplete(true, null) // Usuário criado com sucesso
//                        } else {
//                            onComplete(false, dbTask.exception?.message) // Erro ao armazenar dados
//                        }
//                    }
//                } else {
//                    onComplete(false, task.exception?.message) // Erro ao criar usuário
//                }
//            }
//    }
}