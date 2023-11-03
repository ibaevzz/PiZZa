package com.ibaevzz.pizza.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ibaevzz.pizza.databinding.BannerLayoutBinding

class BannerAdapter(private val img: List<Int>)
    : RecyclerView.Adapter<BannerAdapter.ViewHolder>(){
    class ViewHolder(val item: BannerLayoutBinding): RecyclerView.ViewHolder(item.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(BannerLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return img.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.item.banner.setImageResource(img[position])
    }
}