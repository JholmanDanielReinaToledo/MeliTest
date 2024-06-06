package com.jdrt.melitest.domain.repository

import androidx.paging.PagingData
import com.jdrt.melitest.domain.model.Product
import com.jdrt.melitest.domain.model.ProductDetail
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {
    fun getProducts(q: String, limit: Int): Flow<PagingData<Product>>
    suspend fun getProductDetail(id: String): ProductDetail?
}