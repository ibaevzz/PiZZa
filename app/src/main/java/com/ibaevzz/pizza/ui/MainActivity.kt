package com.ibaevzz.pizza.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ibaevzz.pizza.R
import com.ibaevzz.pizza.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.let {
            it.setDisplayShowCustomEnabled(true)
            it.setCustomView(R.layout.action_bar)
            it.elevation = 0f
        }
    }
}