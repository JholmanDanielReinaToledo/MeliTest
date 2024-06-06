package com.jdrt.melitest.data.dto

import com.jdrt.melitest.domain.model.Product

data class ProductsResponse (
    val paging: Paging,
    val query: String,
    val results: List<Product>,
)

data class Paging (
    val total: Int,
    val offset: Int,
    val limit: Int,
)
