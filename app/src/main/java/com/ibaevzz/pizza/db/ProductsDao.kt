package com.ibaevzz.pizza.db

import androidx.room.*

@Dao
interface ProductsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProduct(productEntity: ProductEntity)

    @Query("SELECT * FROM products")
    fun getAllProducts(): List<ProductEntity>

    @Delete
    fun deleteProduct(product: ProductEntity)
}