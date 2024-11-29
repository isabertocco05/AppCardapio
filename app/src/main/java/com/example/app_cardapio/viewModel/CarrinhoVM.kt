package com.example.app_cardapio.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.example.app_cardapio.Models.Item

class CarrinhoVM : ViewModel() {

    private val firestore = FirebaseFirestore.getInstance()

    private val _itensCarrinho = MutableLiveData<List<Item>>()
    val itensCarrinho: LiveData<List<Item>> = _itensCarrinho

    // Função para adicionar um item ao Firestore
    fun adicionarItemCarrinho(item: Item) {
        val itemMap = hashMapOf(
            "nome" to item.nome,
            "descricao" to item.descricao,
            "valor" to item.valor,
            "img_url" to item.img_url
        )

        firestore.collection("carrinho").add(itemMap)
            .addOnSuccessListener { documentReference ->
                Log.d("CarrinhoVM", "Item adicionado com sucesso: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w("CarrinhoVM", "Erro ao adicionar item", e)
            }
    }

    // Função para carregar os itens do carrinho
    fun carregarItensCarrinho() {
        val carrinhoRef = firestore.collection("carrinho")

        carrinhoRef.get()
            .addOnSuccessListener { documents ->
                val itens = mutableListOf<Item>()
                for (document in documents) {
                    val item = document.toObject(Item::class.java)
                    itens.add(item)
                }
                _itensCarrinho.value = itens  // Atualiza a lista observada
            }
            .addOnFailureListener { e ->
                Log.w("CarrinhoVM", "Erro ao carregar itens do carrinho", e)
            }
    }
}
