package com.example.newsapp.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.activities.NewsDetailsActivity
import com.example.newsapp.activities.SavedNewsDetailsActivity
import com.example.newsapp.adapter.SavedAdapter
import com.example.newsapp.databinding.FragmentCategoryBinding
import com.example.newsapp.databinding.FragmentSavedBinding
import com.example.newsapp.room.SavedApp
import com.example.newsapp.room.SavedDao
import com.example.newsapp.room.SavedEntity
import com.example.newsapp.util.FragmentDrawable
import kotlinx.coroutines.launch


class SavedFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentSavedBinding

    companion object {
        var EXTRA_SAVED_NEWS_DETAILS = "extra_saved_news_details"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSavedBinding.inflate(layoutInflater, container, false)

        val savedDao = (requireActivity().application as SavedApp).db.savedDao()

        FragmentDrawable.getDrawable(binding.newsFragment.ivNews, R.drawable.ic_grey_news)
        FragmentDrawable.getDrawable(binding.newsFragment.ivSave, R.drawable.ic_white_saved)



        binding.newsFragment.ivNews.setOnClickListener(this@SavedFragment)
        binding.newsFragment.ivCategory.setOnClickListener(this@SavedFragment)

        lifecycleScope.launch{
            savedDao.fetchAllSaved().collect{
                val list = ArrayList(it)
                setupListOfDataIntoRecyclerView(list, savedDao)
            }

        }
        return binding.root
    }


    override fun onClick(v: View?) {
        when(v!!.id) {
            R.id.iv_news -> {findNavController().navigate(R.id.action_savedFragment_to_newsFragment)}
            R.id.iv_category -> {findNavController().navigate(R.id.action_savedFragment_to_categoryFragment)}
        }
    }

    private fun setupListOfDataIntoRecyclerView(
        savedList : ArrayList<SavedEntity>, savedDao: SavedDao) {
        if(savedList.isNotEmpty()) {
            val savedAdapter = SavedAdapter(savedList)
            binding.rvSaved.adapter = savedAdapter
            binding.rvSaved.layoutManager = LinearLayoutManager(requireContext())

            savedAdapter.setOnClickListener(object : SavedAdapter.OnClickListener{
                override fun onClick(position: Int, model: SavedEntity) {
                    val intent = Intent(requireContext(), SavedNewsDetailsActivity::class.java)
                    intent.putExtra(EXTRA_SAVED_NEWS_DETAILS, model)
                    startActivity(intent)
                }

            })

        }
    }

}