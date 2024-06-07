package com.jdrt.melitest.domain.usecases.products

import com.jdrt.melitest.domain.model.ProductDetail
import com.jdrt.melitest.domain.repository.ProductRepository

class GetProductDetail(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke (id: String): ProductDetail? {
        return productRepository.getProductDetail(id)
    }
}
