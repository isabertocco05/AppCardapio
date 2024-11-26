package com.example.app_cardapio.Views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.app_cardapio.Models.Item
import com.example.app_cardapio.databinding.ItensViewBinding


class ItensAdapter (
    private val itens: List<Item>,
    private val onItemClick: (Item) -> Unit

    ) : RecyclerView.Adapter<ItensAdapter.ItemViewHolder>() {

        inner class ItemViewHolder(val binding: ItensViewBinding) : RecyclerView.ViewHolder(binding.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
            val binding = ItensViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ItemViewHolder(binding)
        }

        override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
            val item = itens[position]

            with(holder.binding) {
                nomeItem.text = item.nome
                descItem.text = item.descricao
                valorItem.text = "R$ %.2f".format(item.valor)

                Glide.with(imgItem.context)
                    .load(item.img_url)
                    .centerCrop()
//                    .placeholder(R.drawable.placeholder)
                    .into(imgItem)

                root.setOnClickListener {
                    onItemClick(item)
                }
            }
        }

        override fun getItemCount(): Int = itens.size
    }

