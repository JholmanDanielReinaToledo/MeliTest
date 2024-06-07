package com.jdrt.melitest.domain.usecases.products

import androidx.paging.PagingData
import com.jdrt.melitest.domain.model.Product
import com.jdrt.melitest.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow

class GetProducts(
    private val productRepository: ProductRepository
) {
    operator fun invoke(q: String): Flow<PagingData<Product>> {
        return productRepository.getProducts(q = q, limit = 10)
    }
}