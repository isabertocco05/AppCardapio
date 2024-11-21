package com.example.app_cardapio.Views

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.app_cardapio.Models.User
import com.example.app_cardapio.R
import android.widget.EditText
import com.example.app_cardapio.viewModel.CriarContaVM
import com.example.app_cardapio.databinding.ActivityCriarContaViewBinding

class CriarContaView : AppCompatActivity() {
    private lateinit var binding: ActivityCriarContaViewBinding
    private lateinit var viewModel: CriarContaVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCriarContaViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(CriarContaVM::class.java)

        enableEdgeToEdge()
        setContentView(R.layout.activity_criar_conta_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        binding.CadastrarUsuario.setOnClickListener {
            val user = User(
                nomeUsuario = binding.nomeUsuario.toString(),
                nomeCompleto = binding.nomeCompleto.toString(),
                email = binding.seuEmail.toString(),
                senha = binding.senha.toString(),
                confirmaSenha = binding.confirmacaoSenha.toString()
            )
            viewModel.criarConta(user)
        }
    }
}