package com.ibaevzz.pizza

data class Product(val name: String = "",
                   val price: Int = 0,
                   val img: String = "",
                   val description: String = "",
                   override var type: Type = Type.Pizza): TypeInterface