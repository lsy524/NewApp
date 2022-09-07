package com.example.newsapp.fragment


import android.app.ActionBar
import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentCategoryBinding
import com.example.newsapp.util.FragmentDrawable


class CategoryFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentCategoryBinding

    private val TAG = CategoryFragment::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCategoryBinding.inflate(layoutInflater, container, false)

        FragmentDrawable.getDrawable(binding.newsFragment.ivNews, R.drawable.ic_grey_news)
        FragmentDrawable.getDrawable(binding.newsFragment.ivCategory, R.drawable.ic_white_category)


        binding.newsFragment.ivNews.setOnClickListener(this@CategoryFragment)
        binding.newsFragment.ivSave.setOnClickListener(this@CategoryFragment)

        return binding.root
    }

    override fun onClick(v: View?) {
        when(v!!.id) {
            R.id.iv_news -> {findNavController().navigate(R.id.action_categoryFragment_to_newsFragment)}
            R.id.iv_save -> {findNavController().navigate(R.id.action_categoryFragment_to_savedFragment)}
        }
    }


}