package com.ibaevzz.pizza.ui

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ibaevzz.pizza.R
import com.ibaevzz.pizza.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.let {
            it.setDisplayShowCustomEnabled(true)
            it.setCustomView(R.layout.action_bar)
            it.elevation = 0f
            it.setBackgroundDrawable(resources.getDrawable(R.color.background))
        }

        val menuFragment = MenuFragment()
        val profileFragment = ProfileFragment()
        val basketFragment = BasketFragment()
        supportFragmentManager.beginTransaction().add(R.id.container, profileFragment).commit()
        supportFragmentManager.beginTransaction().add(R.id.container, basketFragment).commit()
        supportFragmentManager.beginTransaction().add(R.id.container, menuFragment).commit()

        binding.bottomNav.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.menu -> {
                    supportFragmentManager.beginTransaction().hide(profileFragment).hide(basketFragment).commit()
                    supportFragmentManager.beginTransaction().show(menuFragment).commit()
                }
                R.id.profile -> {
                    supportFragmentManager.beginTransaction().hide(menuFragment).hide(basketFragment).commit()
                    supportFragmentManager.beginTransaction().show(profileFragment).commit()
                }
                R.id.basket -> {
                    supportFragmentManager.beginTransaction().hide(profileFragment).hide(menuFragment).commit()
                    supportFragmentManager.beginTransaction().show(basketFragment).commit()
                }
            }
            return@setOnItemSelectedListener true
        }
    }

    fun isOnline(): Boolean {
        val connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                return true
            }
        }
        return false
    }
}