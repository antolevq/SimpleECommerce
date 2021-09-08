package com.leva.simpleecommerce.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.leva.simpleecommerce.databinding.FragmentHomeBinding
import com.leva.simpleecommerce.ui.home.adapter.ProductAdapter
import com.leva.simpleecommerce.viewmodels.CartViewModel
import com.plexia.domain.DataState
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()
    private val cartViewModel: CartViewModel by sharedViewModel()
    private lateinit var binding: FragmentHomeBinding

    // This property is only valid between onCreateView and
    // onDestroyView.

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        binding = FragmentHomeBinding.inflate(inflater, container, false)

        homeViewModel.productList.observe(viewLifecycleOwner) { dataState ->
            when (dataState) {
                is DataState.Loading -> {
                }
                is DataState.Error -> {
                }
                is DataState.Success -> {
                    binding.rvProductList.adapter =
                        ProductAdapter(dataState.data) { quantity, product ->
                            cartViewModel.updateItemsQuantity(quantity, product)
                        }
                }

            }

        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}