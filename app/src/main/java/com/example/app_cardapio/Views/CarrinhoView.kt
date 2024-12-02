package com.example.app_cardapio.Views

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.app_cardapio.Models.Item
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

        carrinhoAdapter = CarrinhoAdapter { item -> removerItemDoCarrinho(item) }
        binding.recyViewCarrinho.apply {
            adapter = carrinhoAdapter
            layoutManager = GridLayoutManager(this@CarrinhoView, 1)
        }

        carrinhoViewModel.itensCarrinho.observe(this, Observer { items ->
            carrinhoAdapter.setItensCarrinho(items)
        })

        carrinhoViewModel.carregarItensCarrinho()

        binding.finalizar.setOnClickListener {
            carrinhoViewModel.limparCarrinho()
            Toast.makeText(this, "Compra finalizada com sucesso!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun removerItemDoCarrinho(item: Item) {
        carrinhoViewModel.removerItemCarrinho(item)
        Toast.makeText(this, "${item.nome} removido do carrinho", Toast.LENGTH_SHORT).show()
    }
}
