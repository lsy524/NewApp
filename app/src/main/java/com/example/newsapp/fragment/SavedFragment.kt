package com.example.newsapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentCategoryBinding
import com.example.newsapp.databinding.FragmentSavedBinding
import com.example.newsapp.util.FragmentDrawable


class SavedFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentSavedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSavedBinding.inflate(layoutInflater, container, false)


        FragmentDrawable.getDrawable(binding.newsFragment.ivNews, R.drawable.ic_grey_news)
        FragmentDrawable.getDrawable(binding.newsFragment.ivSave, R.drawable.ic_white_saved)

        binding.newsFragment.ivNews.setOnClickListener(this@SavedFragment)
        binding.newsFragment.ivCategory.setOnClickListener(this@SavedFragment)

        return binding.root
    }


    override fun onClick(v: View?) {
        when(v!!.id) {
            R.id.iv_news -> {findNavController().navigate(R.id.action_savedFragment_to_newsFragment)}
            R.id.iv_category -> {findNavController().navigate(R.id.action_savedFragment_to_categoryFragment)}
        }
    }

}