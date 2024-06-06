package com.jdrt.melitest.domain.model

data class Product (
    val id: String,
    val title: String,
    val price: Double,
    val thumbnail: String,
    val condition: String,
    val permalink: String,
    val seller: Seller
)
