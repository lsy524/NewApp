package com.example.newsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.databinding.ItemTopNewsRowBinding
import com.example.newsapp.models.Articles
import com.example.newsapp.room.SavedEntity

class SavedAdapter(
    private val items: ArrayList<SavedEntity>): RecyclerView.Adapter<SavedAdapter.ViewHolder>() {

    private var onClickListener : OnClickListener? = null
    class ViewHolder(binding: ItemTopNewsRowBinding) : RecyclerView.ViewHolder(binding.root) {
        val savedAuthor = binding.tvAuthor
        val savedTitle = binding.tvTitle
        val savedTime = binding.tvTime
        val savedImage = binding.ivUrlImage
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemTopNewsRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val context = holder.itemView.context
        val model = items[position]

        holder.savedAuthor.text = model.author
        holder.savedTitle.text = model.title
        holder.savedTime.text = model.publishedAt
        Glide.with(context)
            .load(model.urlToImage)
            .into(holder.savedImage)

        holder.itemView.setOnClickListener {
            if(onClickListener != null) {
                onClickListener!!.onClick(position, model)
            }
        }

    }

    override fun getItemCount() = items.size

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }


    interface OnClickListener {
        fun onClick(position: Int, model: SavedEntity)
    }
}