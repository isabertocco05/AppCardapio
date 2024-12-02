package com.example.app_cardapio.Models.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.app_cardapio.Models.Item
import com.google.firebase.firestore.FirebaseFirestore

class CarrinhoRepository {
    private val _itensCarrinho = MutableLiveData<List<Item>>()
    val itensCarrinho: LiveData<List<Item>> = _itensCarrinho
    private val _totalCarrinho = MutableLiveData<Double>()
    val totalCarrinho: LiveData<Double> = _totalCarrinho
    private val db = FirebaseFirestore.getInstance()

    fun adicionarItem(item: Item) {
        db.collection("carrinho").add(item)
            .addOnSuccessListener {
                getItensCarrinho()
            }
    }

    fun removerItem(item: Item) {
        db.collection("carrinho")
            .whereEqualTo("nome", item.nome)
            .get()
            .addOnSuccessListener { result ->
                for (doc in result) {
                    db.collection("carrinho").document(doc.id).delete()
                }
                getItensCarrinho()
            }
    }

    fun getItensCarrinho(){
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
                somarTotal(itens)
            }
            .addOnFailureListener {
                _itensCarrinho.value = emptyList()
            }
    }

    fun limparCarrinho(){
        db.collection("carrinho").get()
            .addOnSuccessListener { result ->
                for (doc in result) {
                    db.collection("carrinho").document(doc.id).delete()
                }
                getItensCarrinho()
            }
    }

    private fun somarTotal(itens: List<Item>) {
        val total = itens.sumOf { it.valor }
        _totalCarrinho.value = total
    }
}
