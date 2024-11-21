package com.example.app_cardapio.Views

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.app_cardapio.R
import com.example.app_cardapio.viewModel.LoginVM
import com.example.app_cardapio.databinding.ActivityLoginViewBinding
import androidx.databinding.DataBindingUtil
import com.example.app_cardapio.navigateTo


class LoginView : AppCompatActivity() {
    private lateinit var binding: ActivityLoginViewBinding
    private lateinit var viewModel: LoginVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login_view)
        viewModel = ViewModelProvider(this).get(LoginVM::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        binding.cadastrar.setOnClickListener {
//            navigateTo(this, CriarContaView::class.java)
            val intent = Intent(this, CriarContaView::class.java)
            startActivity(intent)
        }
    }

}
