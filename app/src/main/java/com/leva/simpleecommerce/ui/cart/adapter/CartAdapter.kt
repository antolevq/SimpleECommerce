package com.leva.simpleecommerce.ui.cart.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.leva.domain.model.CartItem
import com.leva.domain.model.Product
import com.leva.extentions.convertToPresentation
import com.leva.extentions.showNumberPickerDialog
import com.leva.simpleecommerce.R
import com.leva.simpleecommerce.databinding.ItemCartBinding

internal class CartAdapter(
    private var cartItemList: MutableList<CartItem>,
    private val onValueSelected: (quantity: Int, product: Product) -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    inner class CartViewHolder(private val binding: ItemCartBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(cartItem: CartItem) {
            val context = binding.root.context
            cartItem.product.let { product ->
                binding.txtProductName.text = product.name
                binding.txtQuantityPrice.text = context.getString(
                    R.string.quantity_price,
                    cartItem.quantity,
                    product.price.convertToPresentation()
                )
                binding.txtTotalAmount.text =
                    (cartItem.quantity * product.price).convertToPresentation()
                binding.btnChangeQuantity.setOnClickListener {
                    context.showNumberPickerDialog(
                        minValue = 0,
                        startValue = cartItem.quantity
                    ) { valueSelected ->
                        onValueSelected.invoke(valueSelected, product)
                    }
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        return CartViewHolder(
            ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(cartItemList[position])
    }

    override fun getItemCount() = cartItemList.size

    @SuppressLint("NotifyDataSetChanged")
    fun refreshList(updatedList: List<CartItem>) {
        cartItemList.clear()
        cartItemList.addAll(updatedList)
        this.notifyDataSetChanged()
    }
}