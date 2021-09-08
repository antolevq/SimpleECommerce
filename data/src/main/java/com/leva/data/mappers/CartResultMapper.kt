package com.leva.data.mappers

import com.leva.data.network.dto.CartResultDTO
import com.leva.domain.BaseMapper
import com.leva.domain.model.CartResult

class CartResultMapper : BaseMapper<CartResultDTO, CartResult> {
    override fun mapFrom(from: CartResultDTO?): CartResult {
        return from?.let {
            CartResult(
                code = it.code,
                description = it.description
            )
        } ?: CartResult("", "")
    }
}