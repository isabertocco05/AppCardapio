package com.example.app_cardapio.Views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.app_cardapio.Models.Categoria
import com.example.app_cardapio.databinding.CategoriaViewBinding

class CategoriaAdapter(
    private val categorias: List<Categoria>,
    private val onCategoriaClick: (String) -> Unit
) : RecyclerView.Adapter<CategoriaAdapter.CategoriaViewHolder>() {

    class CategoriaViewHolder(val binding: CategoriaViewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriaViewHolder {
        val binding = CategoriaViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoriaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoriaViewHolder, position: Int) {
        val categoria = categorias[position]

        holder.binding.nomeCategoria.text = categoria.nome

        Glide.with(holder.itemView.context)
            .load(categoria.imagem)
//            .placeholder(R.drawable.placeholder)
//            .error(R.drawable.error)
            .into(holder.binding.imgCategoria)

        holder.binding.root.setOnClickListener {
            onCategoriaClick(categoria.nome ?: "")
        }
    }

    override fun getItemCount(): Int = categorias.size
}
