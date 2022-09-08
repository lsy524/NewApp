package com.example.newsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.databinding.ItemTopNewsRowBinding
import com.example.newsapp.models.NewsResponse
import com.example.newsapp.util.ImageLoader
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewsAdapter(private val items: List<NewsResponse>): RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemTopNewsRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindItem(item : NewsResponse) {
            for(i in item.articles.indices) {
                binding.tvAuthor.text = item.articles[i].author
                binding.tvTime.text = item.articles[i].publishedAt
                binding.tvTitle.text = item.articles[i].title

                CoroutineScope(Dispatchers.Main).launch {
                    val bitmap = withContext(Dispatchers.IO) {
                        ImageLoader.loadImage(item.articles[i].urlToImage)
                    }
                    binding.ivUrlImage.setImageBitmap(bitmap)
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemTopNewsRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = items[position]
        holder.bindItem(model)

    }

    override fun getItemCount() = items.size
}
