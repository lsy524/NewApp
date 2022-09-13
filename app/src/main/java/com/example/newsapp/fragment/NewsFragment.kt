package com.example.newsapp.fragment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.activities.NewsDetailsActivity
import com.example.newsapp.adapter.NewsAdapter
import com.example.newsapp.databinding.FragmentNewsBinding

import com.example.newsapp.models.Articles
import com.example.newsapp.models.NewsResponse
import com.example.newsapp.network.TopNewsService
import com.example.newsapp.util.Constants
import com.google.gson.Gson
import org.json.JSONObject
import org.koin.androidx.viewmodel.ext.android.viewModel
import retrofit.*


class NewsFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentNewsBinding

    private lateinit var mSharedPreferences: SharedPreferences

    private val TAG = NewsFragment::class.java.simpleName

    private lateinit var newsAdapter: NewsAdapter

    companion object {
        var EXTRA_NEWS_DETAILS = "extra_news_details"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsBinding.inflate(layoutInflater, container, false)

        mSharedPreferences = this.requireActivity()
            .getSharedPreferences(Constants.PREFERENCE_NAME, Context.MODE_PRIVATE)

        binding.newsFragment.ivCategory.setOnClickListener(this@NewsFragment)
        binding.newsFragment.ivSave.setOnClickListener(this@NewsFragment)


        getHeadlineNews()


        return binding.root
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.iv_category -> {
                findNavController().navigate(R.id.action_newsFragment_to_categoryFragment)
            }
            R.id.iv_save -> {
                findNavController().navigate(R.id.action_newsFragment_to_savedFragment)
            }
        }
    }

    private fun getHeadlineNews() {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service: TopNewsService =
            retrofit.create(TopNewsService::class.java)

        val listCall: Call<NewsResponse> = service.getHeadLineNews("us", Constants.APP_KEY)

        listCall.enqueue(object : Callback<NewsResponse> {

            override fun onResponse(response: Response<NewsResponse>?, retrofit: Retrofit?) {
                if (response!!.isSuccess) {
                    val newsList: NewsResponse = response.body()
                    val newsResponseJsonString = Gson().toJson(newsList)
                    val editor = mSharedPreferences.edit()
                    editor.putString(
                        Constants.NEWS_RESPONSE_DATA,
                        newsResponseJsonString
                    )


                    editor.apply()

                    val items = mutableListOf<Articles>()
                    response.body()?.let {
                        var author : String
                        var title : String
                        var description : String
                        var url : String
                        var urlToImage : String
                        var publishedAt: String
                        var content : String
                        for (i in it.articles.indices) {
                            author = if(it.articles[i].author != null) {
                                it.articles[i].author
                            } else {
                                ""
                            }
                            title = if(it.articles[i].title != null) {
                                it.articles[i].title
                            } else {
                                ""
                            }
                            description = if(it.articles[i].description != null) {
                                it.articles[i].description
                            } else {
                                ""
                            }
                            url = if(it.articles[i].url != null ){
                                it.articles[i].url
                            } else {
                                ""
                            }

                            urlToImage = if(it.articles[i].urlToImage != null) {
                                it.articles[i].urlToImage
                            } else {
                                ""
                            }
                            publishedAt = if(it.articles[i].publishedAt != null) {
                                it.articles[i].publishedAt
                            } else {
                                ""
                            }
                            content = if(it.articles[i].content != null ) {
                                it.articles[i].content
                            } else {
                                ""
                            }
                            items.add(Articles(author, title, description, url, urlToImage, publishedAt, content))

                        }
                    }

                    newsAdapter = NewsAdapter(items)

                    binding.rvNews.adapter = newsAdapter

                    binding.rvNews.layoutManager = LinearLayoutManager(context)


                    newsAdapter.setOnClickListener(object : NewsAdapter.OnClickListener{
                        override fun onClick(position: Int, model: Articles) {
                            val intent = Intent(requireContext(), NewsDetailsActivity::class.java)
                            intent.putExtra(EXTRA_NEWS_DETAILS, items.toString())
                            startActivity(intent)


                        }

                    })


                } else {
                    when (response.code()) {
                        400 -> {
                            Log.e("Error 400", "Bad Request")
                        }
                        404 -> {
                            Log.e("Error 404", "Not Found")
                        }
                        else -> {
                            Log.e("Error", "Generic Error")
                        }
                    }
                }
            }

            override fun onFailure(t: Throwable?) {
                Log.e(TAG, t!!.message.toString())
            }
        })

    }



}