package com.ibaevzz.pizza.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ibaevzz.pizza.databinding.BasketFragmentBinding

class BasketFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return BasketFragmentBinding.inflate(inflater).root
    }
}