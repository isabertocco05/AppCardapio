package com.example.app_cardapio.Views

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app_cardapio.R
import com.example.app_cardapio.databinding.ActivityCategoriasViewBinding
import com.example.app_cardapio.viewModel.CategoriasVM

class CategoriasView : AppCompatActivity() {

    private lateinit var binding: ActivityCategoriasViewBinding
    private lateinit var categoriaAdapter: CategoriaAdapter
    private val categoriaViewModel: CategoriasVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCategoriasViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        categoriaAdapter = CategoriaAdapter(emptyList()) { nomeCategoria ->
            Toast.makeText(this, "Clicou em: $nomeCategoria", Toast.LENGTH_SHORT).show()
        }

        binding.recyViewCategoria.apply {
            adapter = categoriaAdapter
            layoutManager = LinearLayoutManager(this@CategoriasView)
        }

        categoriaViewModel.categorias.observe(this, Observer { categorias ->
            categoriaAdapter = CategoriaAdapter(categorias) { nomeCategoria ->
                Toast.makeText(this, "Clicou em: $nomeCategoria", Toast.LENGTH_SHORT).show()
            }
            binding.recyViewCategoria.adapter = categoriaAdapter
        })

        categoriaViewModel.showCategorias()
    }
}