package com.example.app_cardapio.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.app_cardapio.Models.Item
import com.example.app_cardapio.Models.Repository.CarrinhoRepository
import com.google.firebase.firestore.FirebaseFirestore

class CarrinhoVM : ViewModel() {

    private val carrinhoRepository = CarrinhoRepository()
    val itensCarrinho: LiveData<List<Item>> = carrinhoRepository.itensCarrinho
    val totalCarrinho: LiveData<Double> = carrinhoRepository.totalCarrinho

    init {
        carregarItensCarrinho()
    }

    fun carregarItensCarrinho() {
        carrinhoRepository.getItensCarrinho()
    }

    fun adicionarItemCarrinho(item: Item) {
        carrinhoRepository.adicionarItem(item)
    }

    fun removerItemCarrinho(item: Item) {
       carrinhoRepository.removerItem(item)
    }

    fun limparCarrinho() {
       carrinhoRepository.limparCarrinho()
    }

}
