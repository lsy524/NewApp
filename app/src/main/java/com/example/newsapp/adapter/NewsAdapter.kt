package com.example.newsapp.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.databinding.ItemTopNewsRowBinding
import com.example.newsapp.models.NewsResponse

class NewsAdapter(
    private val items: List<NewsResponse>
) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemTopNewsRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindItem(item: NewsResponse) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            ItemTopNewsRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = items[position]
        holder.bindItem(model)

    }

    override fun getItemCount() = items.size

}
