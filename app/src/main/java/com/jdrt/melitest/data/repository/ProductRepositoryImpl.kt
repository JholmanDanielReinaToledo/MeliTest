package com.jdrt.melitest.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.jdrt.melitest.data.ProductsApi
import com.jdrt.melitest.data.ProductsPagingSource
import com.jdrt.melitest.domain.model.Product
import com.jdrt.melitest.domain.model.ProductDetail
import com.jdrt.melitest.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow

class ProductRepositoryImpl(
    private val productsApi: ProductsApi
): ProductRepository {
    override fun getProducts(q: String, limint: Int): Flow<PagingData<Product>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                ProductsPagingSource(
                    productsApi = productsApi,
                    q = q,
                )
            }
        ).flow
    }

    override suspend fun getProductDetail(id: String): ProductDetail? {
        return try {
            return productsApi.getProductDetail(id)
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }
}
