package com.ibaevzz.pizza.adapters

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ibaevzz.pizza.R
import com.ibaevzz.pizza.databinding.TypeLayoutBinding

class TypesAdapter(private val types: List<String>, var activ: Int)
    : RecyclerView.Adapter<TypesAdapter.ViewHolder>() {
    class ViewHolder(val item: TypeLayoutBinding) : RecyclerView.ViewHolder(item.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            TypeLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return types.size
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.item.type.text = types[position]
        holder.item.root.setOnClickListener {
            this.notifyItemChanged(activ)
            this.notifyItemChanged(position)
            activ = position
            //TODO
        }
        val context = holder.item.root.context
        if (position == activ) {
            holder.item.type.setTextColor(context.getColor(R.color.type_active_text_color))
            holder.item.root.setCardBackgroundColor(context.getColor(R.color.type_background))
            holder.item.type.setTypeface(Typeface.DEFAULT_BOLD)
        } else {
            holder.item.type.setTextColor(context.getColor(R.color.type_inactive_text_color))
            holder.item.root.setCardBackgroundColor(context.getColor(R.color.white))
            holder.item.type.setTypeface(Typeface.DEFAULT)
        }
    }
}