package com.leva.simpleecommerce.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.leva.simpleecommerce.R
import com.leva.simpleecommerce.databinding.FragmentShoppingCartBinding
import com.leva.simpleecommerce.ui.cart.adapter.CartAdapter
import com.leva.simpleecommerce.viewmodels.CartViewModel
import com.plexia.domain.DataState
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.lang.Error

class CartFragment : Fragment() {

    private lateinit var binding: FragmentShoppingCartBinding
    private val cartViewModel: CartViewModel by sharedViewModel()
    private lateinit var adapter: CartAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShoppingCartBinding.inflate(inflater, container, false)
        binding.rvCartItem.adapter = CartAdapter(mutableListOf()) { quantity, product ->
            cartViewModel.updateItemsQuantity(quantity, product)
        }
        initObservers()
        binding.btnBuy.setOnClickListener {
            cartViewModel.postCart()
        }

        return binding.root
    }

    private fun initObservers() {
        cartViewModel.cartResponse.observe(viewLifecycleOwner) { dataState ->
            binding.progressBar.visibility =
                if (dataState is DataState.Loading) View.VISIBLE else View.GONE

            when (dataState) {
                is DataState.Error -> {
                    Toast.makeText(context, dataState.errorMessage, Toast.LENGTH_SHORT)
                        .show()
                }
                is DataState.Success -> {
                    Toast.makeText(context, getString(R.string.purchase_ok), Toast.LENGTH_SHORT)
                        .show()
                    cartViewModel.emptyCart()
                }
            }
        }

        cartViewModel.cartItemList.observe(viewLifecycleOwner) {
            (binding.rvCartItem.adapter as CartAdapter).refreshList(it)
        }

        cartViewModel.totalAmount.observe(viewLifecycleOwner) { totalAmount ->
            binding.txtTotalAmount.text = totalAmount
        }

        cartViewModel.cartItemList.observe(viewLifecycleOwner) { cartItemList ->
            binding.txtEmptyCart.visibility =
                if (cartItemList.isNullOrEmpty()) View.VISIBLE else View.GONE
            binding.btnBuy.visibility =
                if (cartItemList.isNullOrEmpty()) View.GONE else View.VISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}