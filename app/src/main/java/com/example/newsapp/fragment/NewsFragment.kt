package com.example.newsapp.fragment

import android.content.Context
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
import com.example.newsapp.adapter.NewsAdapter
import com.example.newsapp.databinding.FragmentNewsBinding
import com.example.newsapp.models.Articles
import com.example.newsapp.models.NewsResponse
import com.example.newsapp.network.TopNewsService
import com.example.newsapp.util.Constants
import com.google.gson.Gson
import retrofit.*


class NewsFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentNewsBinding

    private lateinit var mSharedPreferences: SharedPreferences

    private val TAG = NewsFragment::class.java.simpleName

    private lateinit var newsAdapter: NewsAdapter


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


                    val items  =  mutableListOf<NewsResponse>()

                    items.add(NewsResponse(newsList.status, newsList.totalResult, newsList.articles))

                    Log.d(TAG, "${newsList}")
                    setUpNewsRecyclerView(items)


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



    private fun setUpNewsRecyclerView(newsList : List<NewsResponse>) {
        newsAdapter = NewsAdapter(newsList)

        binding.rvNews.adapter = newsAdapter

        binding.rvNews.layoutManager = LinearLayoutManager(context)


    }


}