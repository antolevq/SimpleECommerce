package com.leva.domain.usecase

import com.leva.domain.model.CartResult
import com.leva.domain.model.Product
import com.plexia.domain.DataState
import kotlinx.coroutines.flow.Flow

interface ProductUseCase {
    fun getProductList(): Flow<DataState<List<Product>>>
    fun postCart(totalAmount: Double, totalItems: Int): Flow<DataState<CartResult>>
}