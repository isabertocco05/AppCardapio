package com.example.app_cardapio.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.app_cardapio.Models.Categoria
import com.google.firebase.firestore.FirebaseFirestore

class CategoriasVM : ViewModel() {

    private val _categorias = MutableLiveData<List<Categoria>>()
    val categorias: LiveData<List<Categoria>> get() = _categorias

    fun showCategorias() {
        val db = FirebaseFirestore.getInstance()
        db.collection("cardapio")
            .get()
            .addOnSuccessListener { documents ->
                val categoriaList = mutableListOf<Categoria>()
                for (document in documents) {
                    val nome = document.id
                    val imagem = document.getString("img_categoria")
                    categoriaList.add(Categoria(nome, imagem))
                }
                _categorias.value = categoriaList
            }
            .addOnFailureListener { exception ->
                Log.e("FirestoreError", "Erro ao carregar categorias", exception)
            }
    }
}