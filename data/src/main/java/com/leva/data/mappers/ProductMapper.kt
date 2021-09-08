package com.leva.data.mappers

import com.leva.data.network.dto.ProductDTO
import com.leva.domain.BaseMapper
import com.leva.domain.model.Product

class ProductMapper : BaseMapper<ProductDTO, Product> {
    override fun mapFrom(from: ProductDTO?): Product {
        return from?.let {
            Product(
                id = it.id ?: 0,
                name = it.name.orEmpty(),
                price = it.price ?: 0.toDouble(),
                remoteBackground = it.remoteBackground.orEmpty(),
                fallbackBackground = it.fallbackBackground.orEmpty()
            )
        } ?: Product(0, "", 0.toDouble(), "", "")
    }
}