package com.example.app_cardapio.Views

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app_cardapio.databinding.ActivityCarrinhoViewBinding
import com.example.app_cardapio.viewModel.CarrinhoVM
import com.google.firebase.auth.FirebaseAuth

class CarrinhoView : AppCompatActivity() {

    private lateinit var binding: ActivityCarrinhoViewBinding
    private lateinit var carrinhoAdapter: CarrinhoAdapter

    // Inicializa o ViewModel automaticamente
    private val carrinhoViewModel: CarrinhoVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityCarrinhoViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        carrinhoViewModel.carregarItensCarrinho()
        // Configura o RecyclerView
        binding.recyViewCarrinho.layoutManager = LinearLayoutManager(this)

        // Observa os itens do carrinho
        carrinhoViewModel.itensCarrinho.observe(this, Observer { itens ->
//            carrinhoAdapter = CarrinhoAdapter(itens) { item ->
//                // Lógica para remover o item
//                carrinhoViewModel.removerItemCarrinho(item)
//                Toast.makeText(this, "${item.nome} removido do carrinho", Toast.LENGTH_SHORT).show()
//            }
            binding.recyViewCarrinho.adapter = carrinhoAdapter
        })


    }
}
