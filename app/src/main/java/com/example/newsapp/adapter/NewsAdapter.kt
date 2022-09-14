package com.example.newsapp.adapter


import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.databinding.ItemTopNewsRowBinding
import com.example.newsapp.models.Articles
import com.example.newsapp.util.ImageLoader
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.w3c.dom.Text

class NewsAdapter(private val context: Context, private val items : List<Articles>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var onClickListener : OnClickListener? = null

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_top_news_row, parent, false))
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = items[position]

        val author = holder.itemView.findViewById<TextView>(R.id.tv_author)
        val title = holder.itemView.findViewById<TextView>(R.id.tv_title)
        val time = holder.itemView.findViewById<TextView>(R.id.tv_time)
        val image = holder.itemView.findViewById<ImageView>(R.id.iv_urlImage)

        if(holder is ViewHolder) {
            author.text = model.author
            title.text = model.title
            time.text = model.publishedAt
            Glide.with(context)
                .load(model.urlToImage)
                .into(image)


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
