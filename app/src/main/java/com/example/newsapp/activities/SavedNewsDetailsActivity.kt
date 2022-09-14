package com.example.newsapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.databinding.ActivitySavedNewsDetailsBinding
import com.example.newsapp.fragment.SavedFragment
import com.example.newsapp.room.SavedApp
import com.example.newsapp.room.SavedDao
import com.example.newsapp.room.SavedEntity
import kotlinx.coroutines.launch

class SavedNewsDetailsActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySavedNewsDetailsBinding

    private lateinit var savedNewsDetailModel : SavedEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySavedNewsDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val savedDao = (application as SavedApp).db.savedDao()

        setSupportActionBar(binding.savedToolbar.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        binding.savedToolbar.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        if(intent.hasExtra(SavedFragment.EXTRA_SAVED_NEWS_DETAILS)) {
            savedNewsDetailModel = intent.getSerializableExtra(SavedFragment.EXTRA_SAVED_NEWS_DETAILS) as SavedEntity
        }

        supportActionBar!!.title = savedNewsDetailModel.title
        binding.tvTitle.text = savedNewsDetailModel.title
        binding.tvAuthor.text = savedNewsDetailModel.author
        binding.tvTime.text = savedNewsDetailModel.publishedAt
        binding.tvDescription.text =savedNewsDetailModel.description
        Glide.with(applicationContext)
            .load(savedNewsDetailModel.urlToImage)
            .into(binding.ivUrlToImage)
        
        if(savedNewsDetailModel.saved) {
            binding.ivStar.setImageResource(R.drawable.ic_start_color)
        }

        binding.ivStar.setOnClickListener {
            delete(savedNewsDetailModel.id, savedDao)
            finish()
        }
    }

    private fun delete(id: Int, savedDao: SavedDao) {
        lifecycleScope.launch {
            savedDao.delete(SavedEntity(id=id))
        }
    }
}