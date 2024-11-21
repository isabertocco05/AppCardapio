package com.example.app_cardapio
import android.content.Context
import android.content.Intent
import android.util.Log

// Função para mudar de Activity
fun navigateTo(context: Context, destinationClass: Class<*>) {
    val intent = Intent(context, destinationClass)
    context.startActivity(intent)
}

