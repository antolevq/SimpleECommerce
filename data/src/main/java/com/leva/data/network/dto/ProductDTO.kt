package com.leva.data.network.dto

import com.google.gson.annotations.SerializedName

data class ProductDTO(
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("price") val price: Double?,
    @SerializedName("remote_background") val remoteBackground: String?,
    @SerializedName("fallback_background") val fallbackBackground: String?
)
