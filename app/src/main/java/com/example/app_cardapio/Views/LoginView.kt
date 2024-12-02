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
import com.bumptech.glide.Glide
import com.example.app_cardapio.R
import com.example.app_cardapio.viewModel.LoginVM
import com.example.app_cardapio.databinding.ActivityLoginViewBinding
import com.example.app_cardapio.navigateTo
import com.google.firebase.firestore.FirebaseFirestore

class LoginView : AppCompatActivity() {

    private lateinit var binding: ActivityLoginViewBinding
    private val viewModel: LoginVM by viewModels()
    val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()

        viewModel.isAuthenticated.observe(this, Observer { isAuthenticated ->
            if (isAuthenticated) {
                Toast.makeText(this, "Login bem-sucedido!", Toast.LENGTH_SHORT).show()
                navigateTo(this, CategoriasView::class.java)
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

        db.collection("app")
            .document("img")
            .get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val logoUrl = document.getString("logo")
                    if (!logoUrl.isNullOrEmpty()) {
                        Glide.with(this)
                            .load(logoUrl)
                            .into(binding.imgLogo)
                    }
                }
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
