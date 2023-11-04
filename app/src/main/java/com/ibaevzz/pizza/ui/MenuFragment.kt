package com.ibaevzz.pizza.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.ibaevzz.pizza.R
import com.ibaevzz.pizza.Type
import com.ibaevzz.pizza.adapters.BannerAdapter
import com.ibaevzz.pizza.adapters.ProductsAdapter
import com.ibaevzz.pizza.adapters.TypesAdapter
import com.ibaevzz.pizza.databinding.MenuFragmentBinding
import com.ibaevzz.pizza.view_models.MenuViewModel

class MenuFragment: Fragment() {

    private lateinit var binding: MenuFragmentBinding
    private val viewModel by lazy {
        ViewModelProvider(this)[MenuViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
        binding.types.adapter = TypesAdapter(listOf("Пицца","Комбо", "Напитки","Десерты"), binding.products)
        binding.types.itemAnimator = object: DefaultItemAnimator(){
            override fun canReuseUpdatedViewHolder(viewHolder: RecyclerView.ViewHolder): Boolean {
                return true
            }
        }

        binding.products.layoutManager = LinearLayoutManager(requireContext())
        binding.products.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
        binding.products.addOnScrollListener(object: OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val typeAdapter = binding.types.adapter as TypesAdapter
                val productsAdapter = binding.products.adapter as ProductsAdapter
                val position = (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                when(productsAdapter.types[position]) {
                    Type.Pizza -> {
                        typeAdapter.setType(0)
                    }
                    Type.Combo -> {
                        typeAdapter.setType(1)
                    }
                    Type.Beverages -> {
                        typeAdapter.setType(2)
                    }
                    Type.Dessert -> {
                        typeAdapter.setType(3)
                    }
                }
            }
        })
        setProductsAdapter()
    }

    private fun setProductsAdapter(){
        if((requireActivity() as MainActivity).isOnline()){
            viewModel.initProducts()
        }else{
            viewModel.initProductsWithoutInternet()
        }
        viewModel.getProducts().observe(viewLifecycleOwner){
            binding.progress.visibility = View.GONE
            binding.products.adapter = ProductsAdapter(it)
        }
    }
}