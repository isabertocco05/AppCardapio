package com.example.app_cardapio.Models.Repository

import com.example.app_cardapio.Models.Item
import com.google.firebase.firestore.FirebaseFirestore

class CarrinhoRepository {

    private val carrinho = mutableListOf<Item>()

    fun adicionarItem(item: Item) {
        carrinho.add(item)
    }

    fun removerItem(item: Item) {
        carrinho.remove(item)
    }

    fun getItensCarrinho(): List<Item> {
        return carrinho
    }
}
