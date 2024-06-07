package com.jdrt.melitest.domain.usecases

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.Before

import com.jdrt.melitest.data.ProductsApi
import com.jdrt.melitest.data.repository.ProductRepositoryImpl
import com.jdrt.melitest.domain.model.ProductDetail
import io.mockk.coVerify
import org.junit.Assert.assertEquals

class ProductRepositoryImplTest {

    private lateinit var productsApi: ProductsApi
    private lateinit var productRepository: ProductRepositoryImpl

    @Before
    fun setUp() {
        productsApi = mockk()
        productRepository = ProductRepositoryImpl(productsApi)
    }

    @Test
    fun `getProductDetail returns ProductDetail on success`() = runBlocking {
        val productId = "123"
        val productDetail = mockk<ProductDetail>()

        coEvery { productsApi.getProductDetail(productId) } returns productDetail

        val result = productRepository.getProductDetail(productId)

        assertEquals(productDetail, result)
        coVerify { productsApi.getProductDetail(productId) }
    }

    @Test
    fun `getProductDetail returns null on failure`() = runBlocking {
        val productId = "123"

        coEvery { productsApi.getProductDetail(productId) } throws Exception("Network error")

        val result = productRepository.getProductDetail(productId)

        assertEquals(null, result)
        coVerify { productsApi.getProductDetail(productId) }
    }
}

