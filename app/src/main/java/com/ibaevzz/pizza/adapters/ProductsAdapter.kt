package com.ibaevzz.pizza.adapters

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ibaevzz.pizza.Product
import com.ibaevzz.pizza.Type
import com.ibaevzz.pizza.TypeInterface
import com.ibaevzz.pizza.databinding.ProductLayoutBinding
import com.ibaevzz.pizza.db.ProductDatabase
import com.ibaevzz.pizza.db.ProductEntity
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

class ProductsAdapter(private val list: List<TypeInterface>): RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>(){
    class ProductViewHolder(val binding: ProductLayoutBinding): RecyclerView.ViewHolder(binding.root)

    val first = mutableListOf(-1 , -1, -1, -1)
    val types = mutableListOf<Type>()

    init{
        list.mapIndexed{index, product ->
            when(product.type){
                Type.Pizza -> {
                    if (first[0]==-1)
                        first[0]=index
                    types.add(Type.Pizza)
                }
                Type.Combo -> {
                    if (first[1]==-1)
                        first[1]=index
                    types.add(Type.Combo)
                }
                Type.Beverages -> {
                    if (first[2]==-1)
                        first[2]=index
                    types.add(Type.Beverages)
                }
                Type.Dessert -> {
                    if (first[3]==-1)
                        first[3]=index
                    types.add(Type.Dessert)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(ProductLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = list[position]
        if(product is Product){
            holder.binding.name.text = product.name
            holder.binding.description.text = product.description
            holder.binding.price.text = if (product.type==Type.Pizza) "от ${product.price} р" else product.price.toString()
            if(product.img!="") {
                Picasso.get().load(product.img).into(holder.binding.pizza, object : Callback {
                    override fun onSuccess() {
                        val bitmap = (holder.binding.pizza.drawable as BitmapDrawable).bitmap
                        CoroutineScope(Dispatchers.IO).launch {

                            val oldProducts = ProductDatabase.appDatabase.getProductsDao().getAllProducts()
                            for(i in oldProducts){
                                val path = holder.binding.root.context.cacheDir
                                val file = File(path, "image${i.img}.png")
                                file.delete()
                                ProductDatabase.appDatabase.getProductsDao().deleteProduct(i)
                            }

                            val path = holder.binding.root.context.cacheDir
                            val file = File(path, "image${product.name.hashCode()}.png")
                            val out: OutputStream = FileOutputStream(file)
                            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
                            out.close()

                            val productEntity = ProductEntity(
                                holder.adapterPosition.toLong(), product.name, product.price,
                                "$path/image${holder.adapterPosition}.png", product.description, product.type
                            )
                            ProductDatabase.appDatabase.getProductsDao()
                                .insertProduct(productEntity)
                        }
                    }

                    override fun onError(e: Exception) {
                        Log.e("error", e.message ?: "Error")
                    }
                })
            }
        }else if(product is ProductEntity){
            holder.binding.name.text = product.name
            holder.binding.description.text = product.description
            holder.binding.price.text = if (product.type==Type.Pizza) "от ${product.price} р" else product.price.toString()
            holder.binding.pizza.setImageBitmap(BitmapFactory.decodeFile(product.img))
        }
    }
}