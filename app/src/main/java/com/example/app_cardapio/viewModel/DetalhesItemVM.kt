package com.example.app_cardapio.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.app_cardapio.Models.Item
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DetalhesItemVM : ViewModel() {

    private val _itemDetalhes = MutableLiveData<Item>()
    val itemDetalhes: LiveData<Item> get() = _itemDetalhes

    private val database: DatabaseReference = FirebaseDatabase.getInstance().reference


    fun getItemDetalhes(nomeItem: String) {

        database.child("itens").orderByChild("nome").equalTo(nomeItem)
            .get().addOnSuccessListener { dataSnapshot ->
                if (dataSnapshot.exists()) {

                    val item = dataSnapshot.children.first().getValue(Item::class.java)
                    if (item != null) {
                        _itemDetalhes.postValue(item)
                    }
                }
            }
            .addOnFailureListener {

            }
    }
}