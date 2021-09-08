package com.leva.data.network.api

import com.leva.data.network.GET_PRODUCTS_ENDPOINT
import com.leva.data.network.POST_CART_ENDPOINT
import com.leva.data.network.dto.CartResultDTO
import com.leva.data.network.dto.CartResultResponseDTO
import com.leva.data.network.dto.ProductDTO
import retrofit2.http.*

interface ProductApi {

    @GET(GET_PRODUCTS_ENDPOINT)
    suspend fun getProducts(): List<ProductDTO>

    @Headers("Accept: application/json")
    @FormUrlEncoded
    @POST(POST_CART_ENDPOINT)
    suspend fun postCart(
        @Field("totalAmount") totalAmount: Double,
        @Field("totalItems") totalItems: Int
    ): CartResultResponseDTO


}