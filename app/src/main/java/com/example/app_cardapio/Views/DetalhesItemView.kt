package com.example.app_cardapio.Views

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.app_cardapio.R
import com.example.app_cardapio.databinding.ActivityDetalhesItemBinding
import com.example.app_cardapio.viewModel.CarrinhoVM
import com.example.app_cardapio.viewModel.DetalhesItemVM
import com.example.app_cardapio.viewModel.ItensVM


class DetalhesItemView : AppCompatActivity() {

    private lateinit var binding: ActivityDetalhesItemBinding
    private val carrinhoViewModel: CarrinhoVM by viewModels()
    private val itemDetalhesVM: ItensVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetalhesItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()

        val categoria = intent.getStringExtra("categoria") ?: return
        val nomeItem = intent.getStringExtra("nomeItem") ?: return

        itemDetalhesVM.carregarDetalhes(categoria, nomeItem)

        itemDetalhesVM.itens.observe(this, Observer { itens ->
            val itemDetalhado = itens.firstOrNull()  // Como esperamos um único item, pegamos o primeiro
            if (itemDetalhado != null) {
                binding.nomeItem.text = itemDetalhado.nome
                binding.descItem.text = itemDetalhado.descricao
                binding.valorItem.text = "R$ %.2f".format(itemDetalhado.valor)

                Glide.with(binding.imgItem.context)
                    .load(itemDetalhado.img_url)
                    .centerCrop()
                    .into(binding.imgItem)

                binding.addItem.setOnClickListener {
                    carrinhoViewModel.adicionarItemCarrinho(itemDetalhado)
                    Toast.makeText(this, "${itemDetalhado.nome} adicionado ao carrinho", Toast.LENGTH_SHORT).show()
                }
            }

        })


    }
}




