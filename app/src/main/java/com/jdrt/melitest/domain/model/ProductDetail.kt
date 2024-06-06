package com.jdrt.melitest.domain.model

data class ProductDetail(
    val id: String,
    val title: String,
    val price: Double,
    val permalink: String,
    val pictures: List<Picture>,
    val attributes: List<Atribute>
)
