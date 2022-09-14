package com.example.newsapp.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.databinding.ItemTopNewsRowBinding
import com.example.newsapp.models.Articles

class NewsAdapter(private val context: Context, private val items : List<Articles>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var onClickListener : OnClickListener? = null

    class ViewHolder(binding: ItemTopNewsRowBinding) : RecyclerView.ViewHolder(binding.root) {
        val author = binding.tvAuthor
        val title = binding.tvTitle
        val time = binding.tvTime
        val image = binding.ivUrlImage
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(ItemTopNewsRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = items[position]

        if(holder is ViewHolder) {
            holder.author.text = model.author
            holder.title.text = model.title
            holder.time.text = model.publishedAt
            Glide.with(context)
                .load(model.urlToImage)
                .into(holder.image)


            holder.itemView.setOnClickListener {
                if(onClickListener != null) {
                    onClickListener!!.onClick(position, model)
                }
            }
        }


    }

    override fun getItemCount() = items.size

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }


    interface OnClickListener {
        fun onClick(position: Int, model: Articles)
    }

}
