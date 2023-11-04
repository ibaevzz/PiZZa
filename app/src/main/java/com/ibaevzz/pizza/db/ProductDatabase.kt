package com.ibaevzz.pizza.db

import android.content.Context
import androidx.room.Room

object ProductDatabase {
    lateinit var appDatabase: ProductsDB

    fun init(context: Context) {
        appDatabase = Room.databaseBuilder(context, ProductsDB::class.java, "products.db").build()
    }
}