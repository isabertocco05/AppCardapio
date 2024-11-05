package com.example.app_cardapio.Views

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.app_cardapio.R
import com.example.app_cardapio.ViewModels.LoginVM
import com.example.app_cardapio.databinding.ActivityLoginViewBinding

class LoginView : AppCompatActivity() {
    private lateinit var binding: ActivityLoginViewBinding
    private lateinit var viewModel: LoginVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityLoginViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        binding.Login.setOnClickListener{
            val email = binding.email.text.toString().trim()
            val senha = binding.senha.text.toString().trim()

            viewModel.authenticateUser(email, senha)
        }
    }
}