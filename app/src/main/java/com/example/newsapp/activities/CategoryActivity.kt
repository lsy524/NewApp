package com.example.newsapp.activities

import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.newsapp.R
import com.example.newsapp.databinding.ActivityCategoryBinding
import com.example.newsapp.models.Articles
import com.example.newsapp.models.NewsResponse
import com.example.newsapp.network.TopNewsService
import com.example.newsapp.util.Constants
import com.google.gson.Gson
import retrofit.*
import java.util.*

class CategoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCategoryBinding
    private val TAG = CategoryActivity::class.java.simpleName
    private lateinit var categoryType : String

    private lateinit var mSharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        categoryType = intent.getStringExtra(Constants.CATEGORY).toString()

        setSupportActionBar(binding.categoryToolbar.toolbar)
        supportActionBar!!.title = "Category - ${categoryType.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(
                Locale.getDefault()
            ) else it.toString()
        }}"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        binding.categoryToolbar.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        mSharedPreferences = getSharedPreferences(Constants.PREFERENCE_NAME, MODE_PRIVATE)

        getCategoryNews(categoryType)
    }

    private fun getCategoryNews(categoryType: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service : TopNewsService = retrofit.create(TopNewsService::class.java)

        val listCall: Call<NewsResponse> = service.getCategoryNews("us", Constants.APP_KEY, categoryType)
        listCall.enqueue(object : Callback<NewsResponse>{
            override fun onResponse(response: Response<NewsResponse>?, retrofit: Retrofit?) {
                if(response!!.isSuccess) {
                    val newsList: NewsResponse = response.body()
                    val newsResponseJsonString = Gson().toJson(newsList)
                    val editor = mSharedPreferences.edit()
                    editor.putString(
                        Constants.NEWS_RESPONSE_DATA,
                        newsResponseJsonString
                    )

                    editor.apply()
                    
                    val items = mutableListOf<Articles>()
                    response.body()?.let{
                        
                        for(i in it.articles.indices) {
                            val author = it.articles[i].author ?: ""
                            val title = it.articles[i].title ?: ""
                            val description = it.articles[i].description ?: ""
                            val url = it.articles[i].url ?: ""
                            val urlToImage = it.articles[i].urlToImage ?: ""
                            val publishedAt = it.articles[i].publishedAt ?: ""
                            val content = it.articles[i].content ?: ""
                            
                            items.add(Articles(author, title, description, url, urlToImage, publishedAt, content))
                        }
                    }
                } else {
                    when(response.code()) {
                        400 -> Log.e("Error 400", "Bad Request")
                        404 -> Log.e("Error 404", "Not Found")
                        else -> Log.e("Error", "Generic Error")

                    }
                }
            }

            override fun onFailure(t: Throwable?) {
                Log.d(TAG, t!!.message.toString())
            }

        })
    }
}