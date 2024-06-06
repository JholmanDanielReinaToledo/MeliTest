package com.jdrt.melitest.domain.usecases.products

import com.jdrt.melitest.domain.model.ProductDetail
import com.jdrt.melitest.domain.repository.ProductsRepository

class GetProductDetail(
    private val productsRepository: ProductsRepository
) {
    suspend operator fun invoke (id: String): ProductDetail? {
        return productsRepository.getProductDetail(id)
    }
}
