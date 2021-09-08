package com.leva.domain.repository

import com.leva.domain.model.CartResult
import com.leva.domain.model.Product

interface ProductRepository {
    suspend fun getProductList(): List<Product>
    suspend fun postCart(totalAmount: Double, totalItems: Int): CartResult
}