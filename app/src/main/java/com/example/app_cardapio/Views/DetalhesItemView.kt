package com.example.app_cardapio.Views

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
import com.example.app_cardapio.viewModel.DetalhesItemVM


class DetalhesItemView : AppCompatActivity() {

    private lateinit var binding: ActivityDetalhesItemBinding
    private lateinit var itemDetalhesVM: DetalhesItemVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetalhesItemBinding.inflate(layoutInflater)
        setContentView(binding.root)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        itemDetalhesVM = ViewModelProvider(this).get(DetalhesItemVM::class.java)

        val nomeItem = intent.getStringExtra("nomeItem")
        if (nomeItem != null) {
            // Recupera os detalhes do item com base no nome
            itemDetalhesVM.getItemDetalhes(nomeItem)
            itemDetalhesVM.itemDetalhes.observe(this, Observer { item ->
                binding.nomeItem.text = item.nome
                binding.descItem.text = item.descricao
                binding.valorItem.text = "R$ %.2f".format(item.valor)

                Glide.with(binding.imgItem.context)
                    .load(item.img_url)
                    .centerCrop()
                    .into(binding.imgItem)

                // Configura o botão de adicionar ao pedido
                binding.addItem.setOnClickListener {
                    // Lógica para adicionar o item ao pedido
                }
            })
        } else {
            Toast.makeText(this, "Item não encontrado!", Toast.LENGTH_SHORT).show()
        }
    }
}




