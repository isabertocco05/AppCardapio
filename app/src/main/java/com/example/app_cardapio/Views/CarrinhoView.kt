package com.example.app_cardapio.Views

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app_cardapio.Models.Item
import com.example.app_cardapio.R
import com.example.app_cardapio.databinding.ActivityCarrinhoViewBinding
import com.example.app_cardapio.viewModel.CarrinhoVM

class CarrinhoView : AppCompatActivity() {

    private lateinit var binding: ActivityCarrinhoViewBinding
    private lateinit var carrinhoAdapter: CarrinhoAdapter
    private val carrinhoViewModel: CarrinhoVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCarrinhoViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializando o adapter com uma lista vazia
        carrinhoAdapter = CarrinhoAdapter(emptyList()) { item ->
            // Lógica para remover item do carrinho
            carrinhoViewModel.removerItemCarrinho(item)
        }

        // Configurando o RecyclerView
        binding.recyViewCarrinho.apply {
            layoutManager = LinearLayoutManager(this@CarrinhoView)
            adapter = carrinhoAdapter
        }

        // Observador para observar mudanças nos itens do carrinho
        carrinhoViewModel.itensCarrinho.observe(this, Observer { itens ->
            // Atualiza a lista de itens do carrinho
            carrinhoAdapter = CarrinhoAdapter(itens) { item ->
                // Lógica para remover o item
                carrinhoViewModel.removerItemCarrinho(item)
            }
            binding.recyViewCarrinho.adapter = carrinhoAdapter
        })

        // Carregar os itens do carrinho
        carrinhoViewModel.carregarItensCarrinho()
    }
}
