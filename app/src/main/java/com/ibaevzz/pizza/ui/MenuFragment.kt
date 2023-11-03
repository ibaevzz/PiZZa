package com.ibaevzz.pizza.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ibaevzz.pizza.R
import com.ibaevzz.pizza.adapters.BannerAdapter
import com.ibaevzz.pizza.adapters.TypesAdapter
import com.ibaevzz.pizza.databinding.MenuFragmentBinding

class MenuFragment: Fragment() {

    private lateinit var binding: MenuFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MenuFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.banner.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.banner.adapter = BannerAdapter(listOf(
            R.drawable.banner1,
            R.drawable.banner2,
            R.drawable.banner3
        ))

        binding.types.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.types.adapter = TypesAdapter(listOf("Пицца","Десерты", "Напитки", "Комбо"), 0)
        binding.types.itemAnimator = object: DefaultItemAnimator(){
            override fun canReuseUpdatedViewHolder(viewHolder: RecyclerView.ViewHolder): Boolean {
                return true
            }
        }
    }
}