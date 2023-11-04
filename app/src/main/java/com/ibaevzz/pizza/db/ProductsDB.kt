package com.ibaevzz.pizza.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(version = 1, entities = [ProductEntity::class])
abstract class ProductsDB: RoomDatabase(){
    abstract fun getProductsDao(): ProductsDao
}