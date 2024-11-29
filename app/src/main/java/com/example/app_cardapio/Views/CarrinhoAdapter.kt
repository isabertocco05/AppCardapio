package com.example.app_cardapio.Views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.app_cardapio.Models.Item
import com.example.app_cardapio.databinding.CarrinhoViewBinding

class CarrinhoAdapter(
    private val itens: List<Item>,
    private val onItemRemove: (Item) -> Unit
) : RecyclerView.Adapter<CarrinhoAdapter.CarrinhoViewHolder>() {

    class CarrinhoViewHolder(private val binding: CarrinhoViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item, onItemRemove: (Item) -> Unit) {
            // Atribui valores às views do layout
            binding.nameItem.text = item.nome
            binding.valor.text = "R$ ${item.valor}"
            binding.un.text = "un"

            Glide.with(itemView.context)
                .load(item.img_url)
                .centerCrop()
                .into(binding.image)

//            binding.deleteItem.setOnClickListener {
//                onItemRemove(item)
//            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarrinhoViewHolder {
        val binding = CarrinhoViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CarrinhoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarrinhoViewHolder, position: Int) {
        val item = itens[position]
        holder.bind(item, onItemRemove)
    }

    override fun getItemCount() = itens.size
}
