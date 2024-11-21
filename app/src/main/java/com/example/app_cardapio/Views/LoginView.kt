package com.example.app_cardapio.Views

import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.app_cardapio.R
import com.example.app_cardapio.viewModel.LoginVM
import com.example.app_cardapio.databinding.ActivityLoginViewBinding

class LoginView : AppCompatActivity() {

    private lateinit var binding: ActivityLoginViewBinding
    private val viewModel: LoginVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.isAuthenticated.observe(this, Observer { isAuthenticated ->
            if (isAuthenticated) {
                Toast.makeText(this, "Login bem-sucedido!", Toast.LENGTH_SHORT).show()
                // Navegue para a tela principal, por exemplo:
            } else {
                Toast.makeText(this, "Login inválido! Verifique suas credenciais.", Toast.LENGTH_SHORT).show()
            }
        })


        binding.Login.setOnClickListener {
            val email = binding.email.text.toString()
            val senha = binding.senha.text.toString()

            if (validateInput(email, senha)) {
                viewModel.authenticateUser(email, senha)
            }
        }

        binding.cadastrar.setOnClickListener {
            val email = binding.email.text.toString()
            val senha = binding.senha.text.toString()

            if (validateInput(email, senha)) {
                viewModel.registerUser(email, senha)
            }
        }

        binding.recSenha.setOnClickListener {
            // Implementar recuperação de senha
        }
    }

    private fun validateInput(email: String, senha: String): Boolean {
        if (email.isEmpty() || senha.isEmpty()) {
            showError("Por favor, preencha todos os campos.")
            return false
        }


        return true
    }

    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
