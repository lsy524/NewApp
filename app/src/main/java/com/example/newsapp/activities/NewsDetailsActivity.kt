package com.example.newsapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.databinding.ActivityNewsDetailsBinding
import com.example.newsapp.fragment.NewsFragment
import com.example.newsapp.models.Articles
import com.example.newsapp.room.SavedApp
import com.example.newsapp.room.SavedDao
import com.example.newsapp.room.SavedEntity
import kotlinx.coroutines.launch


class NewsDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewsDetailsBinding

    private lateinit var newsDetailModel : Articles
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val savedDao = (application as SavedApp).db.savedDao()

        setSupportActionBar(binding.detailsToolbar.toolbar)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        binding.detailsToolbar.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }


        if (intent.hasExtra(NewsFragment.EXTRA_NEWS_DETAILS)) {
            newsDetailModel =
                intent.getSerializableExtra(NewsFragment.EXTRA_NEWS_DETAILS) as Articles
        } else if(intent.hasExtra(CategoryNewsActivity.EXTRA_CATEGORY_NEWS_DETAILS)) {
            newsDetailModel =
                intent.getSerializableExtra(CategoryNewsActivity.EXTRA_CATEGORY_NEWS_DETAILS) as Articles
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

        binding.ivStar.setOnClickListener {
            binding.ivStar.setImageResource(R.drawable.ic_start_color)
            addNews(savedDao)

        }


    }

    private fun addNews(savedDao: SavedDao) {
        lifecycleScope.launch{
            savedDao.insert(SavedEntity(saved = true, author = newsDetailModel.author, title = newsDetailModel.title, description = newsDetailModel.description, url = newsDetailModel.url, urlToImage = newsDetailModel.urlToImage, publishedAt = newsDetailModel.publishedAt, content = newsDetailModel.content))
            Toast.makeText(applicationContext, "SAVED OK", Toast.LENGTH_SHORT).show()
        }
    }
}