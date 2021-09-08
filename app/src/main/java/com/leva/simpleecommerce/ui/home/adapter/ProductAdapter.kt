package com.leva.simpleecommerce.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.leva.domain.model.Product
import com.leva.extentions.convertToPresentation
import com.leva.extentions.loadImage
import com.leva.extentions.showNumberPickerDialog
import com.leva.simpleecommerce.databinding.ItemProductBinding

internal class ProductAdapter(
    private var productList: List<Product>,
    private val onValueSelected: (quantity: Int, product:Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    inner class ProductViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            binding.name.text = product.name
            binding.price.text = product.price.convertToPresentation()
            binding.background.loadImage(product.remoteBackground, product.fallbackBackground)
            binding.btnAddToCart.setOnClickListener {
                binding.root.context.showNumberPickerDialog { valueSelected ->
                    onValueSelected.invoke(valueSelected, product)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(productList[position])
    }

    override fun getItemCount() = productList.size

}