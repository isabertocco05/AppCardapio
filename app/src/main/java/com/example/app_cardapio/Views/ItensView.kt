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
import com.example.app_cardapio.databinding.ActivityItensViewBinding
import com.example.app_cardapio.navigateTo
import com.example.app_cardapio.viewModel.CarrinhoVM
import com.example.app_cardapio.viewModel.ItensVM

class ItensView : AppCompatActivity() {

    private lateinit var binding: ActivityItensViewBinding
    private lateinit var itemAdapter: ItensAdapter
    private val itemViewModel: ItensVM by viewModels()
    private val carrinhoViewModel: CarrinhoVM by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityItensViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val categoria = intent.getStringExtra("categoria") ?: return
        binding.titulo.text = categoria

        itemAdapter = ItensAdapter(emptyList()) { item ->
            val intent = Intent(this, DetalhesItemView::class.java)
            intent.putExtra("categoria", categoria)
            intent.putExtra("nomeItem", item.nome)
            startActivity(intent)
        }

        binding.recyViewItens.apply {
            adapter = itemAdapter
            layoutManager = GridLayoutManager(this@ItensView, 2)
        }

        binding.carrinho.setOnClickListener{
            navigateTo(this, CarrinhoView::class.java)
        }

        binding.logout.setOnClickListener{
            carrinhoViewModel.limparCarrinho()
            navigateTo(this, LoginView::class.java)
        }

        itemViewModel.itens.observe(this, Observer { itens ->
            itemAdapter = ItensAdapter(itens) { item ->
                val intent = Intent(this, DetalhesItemView::class.java)
                intent.putExtra("categoria", categoria)
                intent.putExtra("nomeItem", item.nome)
                startActivity(intent)
            }
            binding.recyViewItens.adapter = itemAdapter
        })

        itemViewModel.carregarItens(categoria)
    }
}





