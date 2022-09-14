package com.example.newsapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.databinding.ActivityNewsDetailsBinding
import com.example.newsapp.fragment.NewsFragment
import com.example.newsapp.models.Articles
import com.example.newsapp.util.ImageLoader
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewsDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewsDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.detailsToolbar.toolbar)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        binding.detailsToolbar.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }



        var newsDetailModel : Articles? = null

        if (intent.hasExtra(NewsFragment.EXTRA_NEWS_DETAILS)) {
            newsDetailModel =
                intent.getSerializableExtra(NewsFragment.EXTRA_NEWS_DETAILS) as Articles
        }


        if(newsDetailModel != null) {
            supportActionBar!!.title = newsDetailModel.title
            binding.tvTitle.text = newsDetailModel.title
            binding.tvAuthor.text = newsDetailModel.author
            binding.tvTime.text = newsDetailModel.publishedAt
            binding.tvDescription.text = newsDetailModel.content

            Glide.with(applicationContext)
                .load(newsDetailModel.urlToImage)
                .into(binding.ivUrlToImage)

        }



    }
}