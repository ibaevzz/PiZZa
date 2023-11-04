package com.ibaevzz.pizza

import android.app.Application
import com.ibaevzz.pizza.db.ProductDatabase

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        ProductDatabase.init(this)
    }
}