package com.ibaevzz.pizza.db

import androidx.room.TypeConverter
import com.ibaevzz.pizza.Type

class Converter {
    @TypeConverter
    fun typeToString(type: Type): String{
        return type.name
    }

    @TypeConverter
    fun stringToType(s: String): Type{
        return Type.valueOf(s)
    }
}