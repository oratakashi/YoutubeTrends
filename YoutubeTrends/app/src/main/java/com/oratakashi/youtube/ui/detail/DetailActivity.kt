package com.oratakashi.youtube.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.oratakashi.youtube.R
import com.oratakashi.youtube.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private val binding : ActivityDetailBinding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }
}