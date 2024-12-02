package com.example.app_cardapio.Views

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import android.widget.Button
import androidx.activity.ComponentActivity
import com.google.firebase.auth.FirebaseAuth
import com.example.app_cardapio.R

class RecuperaSenhaView : ComponentActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recupera_senha_view)

        auth = FirebaseAuth.getInstance()


        val recEmail = findViewById<EditText>(R.id.recEmail)


        val enviaLink = findViewById<Button>(R.id.alteraSenha)

        // Enviar link de redefinição de senha
        enviaLink.setOnClickListener {
            val email = recEmail.text.toString()

            if (email.isNotEmpty()) {
                // Enviar link para o e-mail informado
                auth.sendPasswordResetEmail(email)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Link de recuperação enviado para o e-mail.", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this, "Erro ao enviar link de recuperação.", Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                Toast.makeText(this, "Por favor, insira um e-mail válido.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
