package com.example.newsapp.fragment


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.newsapp.R
import com.example.newsapp.activities.CategoryActivity
import com.example.newsapp.databinding.FragmentCategoryBinding
import com.example.newsapp.models.NewsResponse
import com.example.newsapp.network.TopNewsService
import com.example.newsapp.util.Constants
import com.example.newsapp.util.FragmentDrawable
import com.google.gson.Gson
import retrofit.*


class CategoryFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentCategoryBinding
    private lateinit var mSharedPreferences: SharedPreferences
    private val TAG = CategoryFragment::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCategoryBinding.inflate(layoutInflater, container, false)

        mSharedPreferences = this.requireActivity()
            .getSharedPreferences(Constants.PREFERENCE_NAME, Context.MODE_PRIVATE)

        FragmentDrawable.getDrawable(binding.newsFragment.ivNews, R.drawable.ic_grey_news)
        FragmentDrawable.getDrawable(binding.newsFragment.ivCategory, R.drawable.ic_white_category)


        binding.newsFragment.ivNews.setOnClickListener(this@CategoryFragment)
        binding.newsFragment.ivSave.setOnClickListener(this@CategoryFragment)
        binding.ivBusiness.setOnClickListener(this@CategoryFragment)
        binding.ivEntertainment.setOnClickListener(this@CategoryFragment)
        binding.ivGeneral.setOnClickListener(this@CategoryFragment)
        binding.ivHealth.setOnClickListener(this@CategoryFragment)
        binding.ivScience.setOnClickListener(this@CategoryFragment)
        binding.ivSports.setOnClickListener(this@CategoryFragment)
        binding.ivTechnology.setOnClickListener(this@CategoryFragment)

        return binding.root
    }

    override fun onClick(v: View?) {
        when(v!!.id) {
            R.id.iv_news -> {findNavController().navigate(R.id.action_categoryFragment_to_newsFragment)}
            R.id.iv_save -> {findNavController().navigate(R.id.action_categoryFragment_to_savedFragment)}
            R.id.iv_business -> {
                val intent = Intent(context, CategoryActivity::class.java)
                intent.putExtra(Constants.CATEGORY, Constants.BUSINESS)
                startActivity(intent)

            }
            R.id.iv_entertainment -> {
                val intent = Intent(context, CategoryActivity::class.java)
                intent.putExtra(Constants.CATEGORY, Constants.ENTERTAINMENT)
                startActivity(intent)
            }
            R.id.iv_general -> {
                val intent = Intent(context, CategoryActivity::class.java)
                intent.putExtra(Constants.CATEGORY, Constants.GENERAL)
                startActivity(intent)
            }
            R.id.iv_health -> {
                val intent = Intent(context, CategoryActivity::class.java)
                intent.putExtra(Constants.CATEGORY, Constants.HEALTH)
                startActivity(intent)
            }
            R.id.iv_science -> {
                val intent = Intent(context, CategoryActivity::class.java)
                intent.putExtra(Constants.CATEGORY, Constants.SCIENCE)
                startActivity(intent)
            }
            R.id.iv_sports -> {
                val intent = Intent(context, CategoryActivity::class.java)
                intent.putExtra(Constants.CATEGORY, Constants.SPORTS)
                startActivity(intent)
            }
            R.id.iv_technology -> {
                val intent = Intent(context, CategoryActivity::class.java)
                intent.putExtra(Constants.CATEGORY, Constants.TECHNOLOGY)
                startActivity(intent)
            }

        }
    }

    private fun getBusinessNews(category: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service : TopNewsService = retrofit.create(TopNewsService::class.java)

        val listCall: Call<NewsResponse> = service.getCategoryNews("us",Constants.APP_KEY,category)
        listCall.enqueue(object : Callback<NewsResponse>{
            override fun onResponse(response: Response<NewsResponse>?, retrofit: Retrofit?) {
                if(response!!.isSuccess) {
                    val businessNewsList: NewsResponse = response.body()
                    val businessNewsResponseJsonString = Gson().toJson(businessNewsList)
                    val editor = mSharedPreferences.edit()
                    editor.putString(
                        Constants.NEWS_RESPONSE_DATA,
                        businessNewsResponseJsonString
                    )

                    editor.apply()
                    Log.d(TAG, businessNewsList.toString())
                }

            }

            override fun onFailure(t: Throwable?) {
                Log.e(TAG, t!!.message.toString())
            }

        })
    }



}