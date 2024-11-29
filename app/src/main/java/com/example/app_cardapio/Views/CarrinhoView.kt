package com.example.app_cardapio.Views

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app_cardapio.databinding.ActivityCarrinhoViewBinding
import com.example.app_cardapio.viewModel.CarrinhoVM

import androidx.activity.viewModels

class CarrinhoView : AppCompatActivity() {

    private lateinit var binding: ActivityCarrinhoViewBinding
    private lateinit var carrinhoAdapter: CarrinhoAdapter

    // Inicializa o ViewModel automaticamente
    private val carrinhoViewModel: CarrinhoVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCarrinhoViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configura o RecyclerView
//        carrinhoAdapter = CarrinhoAdapter(emptyList()) { item ->
//            // Remover item do carrinho
//            carrinhoViewModel.removerItemCarrinho(item)
//        }

        binding.recyViewCarrinho.apply {
            layoutManager = LinearLayoutManager(this@CarrinhoView)
            adapter = carrinhoAdapter
        }

        // Observa os itens do carrinho
        carrinhoViewModel.itensCarrinho.observe(this) { itens ->
            carrinhoAdapter = CarrinhoAdapter(itens) { item ->
                carrinhoViewModel.removerItemCarrinho(item)
            }
            binding.recyViewCarrinho.adapter = carrinhoAdapter
        }

        // Carrega os itens do carrinho
        carrinhoViewModel.carregarItensCarrinho()
    }
}
