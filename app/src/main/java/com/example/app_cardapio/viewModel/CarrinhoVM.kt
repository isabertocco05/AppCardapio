package com.example.app_cardapio.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.app_cardapio.Models.Item
import com.example.app_cardapio.Models.Repository.CarrinhoRepository
import com.google.firebase.firestore.FirebaseFirestore

class CarrinhoVM : ViewModel() {

    private val carrinhoRepository = CarrinhoRepository()  // Instanciando o repositório
    private val _itensCarrinho = MutableLiveData<List<Item>>()
    val itensCarrinho: LiveData<List<Item>> get() = _itensCarrinho

    init {
        // Inicializando o carrinho com os dados do repositório
        atualizarCarrinho()
    }

    // Função para adicionar um item ao carrinho
    fun adicionarItemCarrinho(item: Item)
    {

        Log.d("CarrinhoVM", "Adicionando item ao carrinho: ${item.nome}")
        val itensAtuais = _itensCarrinho.value?.toMutableList() ?: mutableListOf()
        itensAtuais.add(item)
        _itensCarrinho.value = itensAtuais
        Log.d("CarrinhoVM", "Itens no carrinho após adição: ${_itensCarrinho.value}")
        carrinhoRepository.adicionarItem(item)
        atualizarCarrinho()  // Atualiza a lista após adicionar o item
    }

    // Função privada para atualizar a lista de itens do carrinho
    private fun atualizarCarrinho() {
        _itensCarrinho.value = carrinhoRepository.getItensCarrinho()
    }

    // Função para remover um item do carrinho
    fun removerItemCarrinho(item: Item) {
        carrinhoRepository.removerItem(item)
        atualizarCarrinho()  // Atualiza a lista após remover o item
    }

    fun carregarItensCarrinho() {
        // Chama o repositório para obter os itens
        val itens = carrinhoRepository.getItensCarrinho()

        // Atualiza o LiveData com a lista de itens
        _itensCarrinho.value = itens
    }

}
