package com.leva.data.repository

import com.leva.data.network.api.ProductApi
import com.leva.data.mappers.CartResultMapper
import com.leva.data.mappers.ProductMapper
import com.leva.domain.model.CartResult
import com.leva.domain.model.Product
import com.leva.domain.repository.ProductRepository

class ProductRepositoryImpl(
    private val productApi: ProductApi,
    private val productMapper: ProductMapper,
    private val cartResultMapper: CartResultMapper
) : ProductRepository {
    override suspend fun getProductList(): List<Product> {
        return productMapper.mapFrom(productApi.getProducts())
    }

    override suspend fun postCart(totalAmount: Double, totalItems: Int): CartResult {
        return cartResultMapper.mapFrom(
            productApi.postCart(
                totalAmount = totalAmount,
                totalItems = totalItems
            ).resultDTO
        )
    }

}