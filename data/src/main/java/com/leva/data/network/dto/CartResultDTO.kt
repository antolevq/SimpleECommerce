package com.leva.data.network.dto

import com.google.gson.annotations.SerializedName

data class CartResultResponseDTO(
    @SerializedName("result") val resultDTO: CartResultDTO
)

data class CartResultDTO(
    @SerializedName("code") val code: String?,
    @SerializedName("description") val description: String
)
