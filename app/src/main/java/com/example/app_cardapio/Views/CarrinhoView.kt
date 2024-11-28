package com.example.app_cardapio.Views

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.app_cardapio.R
import com.example.app_cardapio.databinding.ActivityCarrinhoViewBinding
import com.example.app_cardapio.viewModel.CarrinhoVM


class CarrinhoView : AppCompatActivity() {
    private lateinit var binding: ActivityCarrinhoViewBinding
    private lateinit var carrinhoViewModel: CarrinhoVM
    private lateinit var adapter: CarrinhoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCarrinhoViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()

        Log.d("CarrinhoView", "Carrinho entrou")

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        carrinhoViewModel = ViewModelProvider(this).get(CarrinhoVM::class.java)
        Log.d("CarrinhoView", "viewholder aqui")

        // Inicializando o adapter com a lista de itens e a função de remoção
        carrinhoViewModel.itensCarrinho.observe(this) { itens ->
            adapter = CarrinhoAdapter(itens) { item ->
                // Lógica para remover o item do carrinho
                carrinhoViewModel.removerItem(item)
            }
            binding.recyViewCarrinho.adapter = adapter
        }

        // Configuração do botão de limpar carrinho (caso necessário)
        /*binding.btnLimparCarrinho.setOnClickListener {
            carrinhoViewModel.limparCarrinho()
            Toast.makeText(this, "Carrinho limpo!", Toast.LENGTH_SHORT).show()
        }*/
    }
}
