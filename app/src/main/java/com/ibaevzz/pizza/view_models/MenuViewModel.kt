package com.ibaevzz.pizza.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.getValue
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.ibaevzz.pizza.Product
import com.ibaevzz.pizza.Type
import com.ibaevzz.pizza.TypeInterface
import com.ibaevzz.pizza.db.ProductDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MenuViewModel: ViewModel() {
    private var products: MutableLiveData<List<TypeInterface>>? = null

    fun initProducts(){
        if (products!=null) return
        products = MutableLiveData()
        val types = listOf("pizza", "combo", "beverages", "dessert")
        var index = 0

        val productsList = mutableListOf<Product>()
        val ref = Firebase.database.reference

        ref.child("menu").child(types[index++]).get().addOnCompleteListener(object:
            OnCompleteListener<DataSnapshot> {
            override fun onComplete(task: Task<DataSnapshot>) {
                productsList.addAll(task.result.getValue<List<Product>>()?.map {
                    it.type = Type.valueOf(types[index - 1].replaceFirstChar { s -> s.uppercase() })
                    it
                }?: emptyList())
                if(index==4){
                    products?.value = productsList
                    return
                }
                ref.child("menu").child(types[index++]).get().addOnCompleteListener(this)
            }
        })
    }

    fun initProductsWithoutInternet(){
        if (products!=null) return
        products = MutableLiveData()
        CoroutineScope(Dispatchers.IO).launch {
            val productsList = ProductDatabase.appDatabase.getProductsDao().getAllProducts()
            withContext(Dispatchers.Main){
                products?.value = productsList
            }
        }
    }

    fun getProducts(): LiveData<List<TypeInterface>> = products!!
}