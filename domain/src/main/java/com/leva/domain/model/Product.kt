package com.leva.domain.model

data class Product(
    val id: Int,
    val name: String,
    val price: Double,
    val remoteBackground: String,
    val fallbackBackground: String
)
