package com.example.app_cardapio.Views

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app_cardapio.R
import com.example.app_cardapio.databinding.ActivityCategoriasViewBinding
import com.example.app_cardapio.navigateTo
import com.example.app_cardapio.viewModel.CarrinhoVM
import com.example.app_cardapio.viewModel.CategoriasVM

class CategoriasView : AppCompatActivity() {

    private lateinit var binding: ActivityCategoriasViewBinding
    private lateinit var categoriaAdapter: CategoriaAdapter
    private val categoriaViewModel: CategoriasVM by viewModels()
    private val carrinhoViewModel: CarrinhoVM by viewModels()

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
            binding.recyViewCategoria.layoutManager = GridLayoutManager(this@CategoriasView, 2)
        }

        categoriaViewModel.categorias.observe(this, Observer { categorias ->
            categoriaAdapter = CategoriaAdapter(categorias) { nomeCategoria ->
                val intent = Intent(this, ItensView::class.java)
                intent.putExtra("categoria", nomeCategoria)
                startActivity(intent)
            }
            binding.recyViewCategoria.adapter = categoriaAdapter

            binding.carrinho.setOnClickListener{
                navigateTo(this, CarrinhoView::class.java)
            }

            binding.logout.setOnClickListener{
                carrinhoViewModel.limparCarrinho()
                navigateTo(this, LoginView::class.java)
            }
        })


        categoriaViewModel.showCategorias()
    }
}