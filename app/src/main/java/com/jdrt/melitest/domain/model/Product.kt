package com.jdrt.melitest.domain.model

import com.google.gson.annotations.SerializedName

data class Product (
    val id: String,
    val title: String,
    val price: Double,
    val thumbnail: String,
    val condition: String,
    val permalink: String,
    @SerializedName("currency_id")
    val currency: String,
    val seller: Seller,
)
