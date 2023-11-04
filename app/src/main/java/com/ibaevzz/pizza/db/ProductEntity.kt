package com.ibaevzz.pizza.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.ibaevzz.pizza.Type
import com.ibaevzz.pizza.TypeInterface

@Entity(tableName = "products")
@TypeConverters(Converter::class)
data class ProductEntity(@PrimaryKey val id: Long,
                         val name: String,
                         val price: Int,
                         val img: String,
                         val description: String,
                         override val type: Type): TypeInterface