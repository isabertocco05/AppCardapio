//package com.example.app_cardapio.Views

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.app_cardapio.Models.User
import com.example.app_cardapio.R
import android.widget.Toast
import com.example.app_cardapio.viewModel.CriarContaVM
import com.example.app_cardapio.databinding.ActivityCriarContaViewBinding
import com.example.app_cardapio.navigateTo


// Definindo a classe da Activity que representa a tela de criação de conta
// class CriarContaView : AppCompatActivity() {

//     // Declaração das variáveis para o binding e o ViewModel
//     private lateinit var binding: ActivityCriarContaViewBinding
//     private lateinit var viewModel: CriarContaVM

        binding = ActivityCriarContaViewBinding.inflate(layoutInflater)
        setContentView(binding.root) // Configuração correta do layout com ViewBinding

//         // Inicializa o binding, que conecta a Activity com o layout XML
//         binding = ActivityCriarContaViewBinding.inflate(layoutInflater)
//         setContentView(binding.root)

        enableEdgeToEdge()

        // Ajuste de padding para systemBars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewModel.statusCadastro.observe(this) { status ->
            Toast.makeText(this, status, Toast.LENGTH_SHORT).show()
            if (status == "Cadastro realizado com sucesso!") {
                navigateTo(this, LoginView::class.java)
            }
        }

        binding.CadastrarUsuario.setOnClickListener {
            val user = User(
                nomeUsuario = binding.nomeUsuario.text.toString(),
                nomeCompleto = binding.nomeCompleto.text.toString(),
                email = binding.seuEmail.text.toString(),
                senha = binding.senha.text.toString(),
                confirmaSenha = binding.confirmacaoSenha.text.toString()
            )
            if (viewModel.validarSenhas(user.senha, user.confirmaSenha)) {
                viewModel.cadastrarUsuario(user.nomeUsuario, user.nomeCompleto, user.email, user.senha)

            } else {
                Toast.makeText(this, "As senhas não coincidem", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
