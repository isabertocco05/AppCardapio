package com.example.app_cardapio.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.app_cardapio.Models.Item
import com.google.firebase.firestore.FirebaseFirestore

class ItensVM : ViewModel() {
    private val _itens = MutableLiveData<List<Item>>()
    val itens: LiveData<List<Item>> get() = _itens

    fun carregarItens(categoria: String) {
        val db = FirebaseFirestore.getInstance()
        db.collection("cardapio")
            .document(categoria)
            .collection("itens")
            .get()
            .addOnSuccessListener { documents ->
                val itensList = documents.map { it.toObject(Item::class.java) }
                _itens.value = itensList
            }
            .addOnFailureListener { exception ->
                Log.e("FirestoreError", "Erro ao carregar itens", exception)
            }
    }
}
