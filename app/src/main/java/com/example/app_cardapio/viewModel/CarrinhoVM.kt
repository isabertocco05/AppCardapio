package com.example.app_cardapio.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.app_cardapio.Models.Item
import com.google.firebase.firestore.FirebaseFirestore

class CarrinhoVM : ViewModel() {

    private val _itensCarrinho = MutableLiveData<List<Item>>()
    val itensCarrinho: LiveData<List<Item>> = _itensCarrinho

    private val db = FirebaseFirestore.getInstance()

    fun carregarItensCarrinho() {
        db.collection("carrinho")
            .get()
            .addOnSuccessListener { result ->
                val itens = result.map { doc ->
                    Item(
                        nome = doc.getString("nome") ?: "",
                        valor = doc.getDouble("valor") ?: 0.0,
                        img_url = doc.getString("img_url") ?: ""
                    )
                }
                _itensCarrinho.value = itens
            }
            .addOnFailureListener {
                _itensCarrinho.value = emptyList()
            }
    }

    fun adicionarItemCarrinho(item: Item) {
        db.collection("carrinho").add(item)
            .addOnSuccessListener {
                carregarItensCarrinho()
            }
    }

    fun removerItemCarrinho(item: Item) {
        db.collection("carrinho")
            .whereEqualTo("nome", item.nome)
            .get()
            .addOnSuccessListener { result ->
                for (doc in result) {
                    db.collection("carrinho").document(doc.id).delete()
                }
                carregarItensCarrinho()
            }
    }

    fun limparCarrinho() {
        db.collection("carrinho").get()
            .addOnSuccessListener { result ->
                for (doc in result) {
                    db.collection("carrinho").document(doc.id).delete()
                }
                carregarItensCarrinho()
            }
    }
}
