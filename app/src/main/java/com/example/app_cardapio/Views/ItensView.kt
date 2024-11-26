package com.example.app_cardapio.Views

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
import com.example.app_cardapio.viewModel.ItensVM

class ItensView : AppCompatActivity() {

    private lateinit var binding: ActivityItensViewBinding
    private lateinit var itemAdapter: ItensAdapter
    private val itemViewModel: ItensVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = ActivityItensViewBinding.inflate(layoutInflater)
    setContentView(binding.root)

    val categoria = intent.getStringExtra("categoria") ?: return

    itemAdapter = ItensAdapter(emptyList()) { item ->
        Toast.makeText(this, "Clicou no item: ${item.nome}", Toast.LENGTH_SHORT).show()
    }

    binding.recyViewItens.apply {
        adapter = itemAdapter
        binding.recyViewItens.layoutManager = GridLayoutManager(this@ItensView, 2)
    }

    itemViewModel.itens.observe(this, Observer { itens ->
        itemAdapter = ItensAdapter(itens) { item ->
         Toast.makeText(this, "Clicou no item: ${item.nome}", Toast.LENGTH_SHORT).show()
        }
        binding.recyViewItens.adapter = itemAdapter
    })

    itemViewModel.carregarItens(categoria)
    }
}




