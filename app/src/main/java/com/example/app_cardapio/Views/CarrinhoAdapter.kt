package com.example.app_cardapio.Views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.app_cardapio.Models.Item
import com.example.app_cardapio.databinding.CarrinhoViewBinding

class CarrinhoAdapter(
    private val onItemRemove: (Item) -> Unit
) : RecyclerView.Adapter<CarrinhoAdapter.CarrinhoViewHolder>() {

    private val itensCarrinho = mutableListOf<Item>()

    fun setItensCarrinho(newItems: List<Item>) {
        itensCarrinho.clear()
        itensCarrinho.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarrinhoViewHolder {
        val binding = CarrinhoViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CarrinhoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarrinhoViewHolder, position: Int) {
        val item = itensCarrinho[position]
        holder.bind(item)
//        holder.binding.btnRemove.setOnClickListener { onItemRemove(item) }
    }

    override fun getItemCount(): Int = itensCarrinho.size

    class CarrinhoViewHolder(val binding: CarrinhoViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Item) {
            binding.nameItem.text = item.nome
            binding.valor.text = "R$ %.2f".format(item.valor)
            Glide.with(binding.image.context).load(item.img_url).into(binding.image)
        }
    }
}
