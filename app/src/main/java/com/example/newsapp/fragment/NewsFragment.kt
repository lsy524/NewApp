package com.example.newsapp.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentNewsBinding


class NewsFragment : Fragment(), View.OnClickListener {

    private lateinit var binding : FragmentNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentNewsBinding.inflate(layoutInflater, container, false)


        binding.newsFragment.ivCategory.setOnClickListener(this@NewsFragment)
        binding.newsFragment.ivSave.setOnClickListener(this@NewsFragment)

        return binding.root
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.iv_category -> { findNavController().navigate(R.id.action_newsFragment_to_categoryFragment) }
            R.id.iv_save -> { findNavController().navigate(R.id.action_newsFragment_to_savedFragment) }


        }

    }


}