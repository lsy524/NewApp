package com.example.newsapp.util

import android.widget.ImageView

class FragmentDrawable {
    companion object {
        fun getDrawable(id: ImageView, image : Int) {
            id.setImageResource(image)
        }
    }
}