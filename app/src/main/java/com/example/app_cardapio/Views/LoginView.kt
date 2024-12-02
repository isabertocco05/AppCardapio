package com.example.app_cardapio.Views

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import com.example.app_cardapio.R
import com.example.app_cardapio.viewModel.LoginVM
import com.example.app_cardapio.databinding.ActivityLoginViewBinding
import com.example.app_cardapio.navigateTo

class LoginView : AppCompatActivity() {

    private lateinit var binding: ActivityLoginViewBinding
    private val viewModel: LoginVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        viewModel.isAuthenticated.observe(this, Observer { isAuthenticated ->
            if (isAuthenticated) {
                Toast.makeText(this, "Login bem-sucedido!", Toast.LENGTH_SHORT).show()
                navigateTo(this, CategoriasView::class.java)
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
            navigateTo(this, CriarContaView::class.java)
        }

        binding.recSenha.setOnClickListener {
            navigateTo(this, RecuperaSenhaView::class.java)
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
