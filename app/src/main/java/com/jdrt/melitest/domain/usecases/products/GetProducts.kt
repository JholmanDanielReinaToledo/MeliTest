package com.jdrt.melitest.domain.usecases.products

import androidx.paging.PagingData
import com.jdrt.melitest.domain.model.Product
import com.jdrt.melitest.domain.repository.ProductsRepository
import kotlinx.coroutines.flow.Flow

class GetProducts(
    private val productsRepository: ProductsRepository
) {
    operator fun invoke(q: String): Flow<PagingData<Product>> {
        return productsRepository.getProducts(q = q, limit = 10)
    }
}