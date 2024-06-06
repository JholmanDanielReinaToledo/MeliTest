package com.jdrt.melitest.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jdrt.melitest.domain.model.Product

class ProductsPagingSource(
    private val productsApi: ProductsApi,
    private val q: String,
): PagingSource<Int, Product>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        val page = params.key ?: 1
        val offset = if (page == 1) 0 else page * 30
        return try {
            val productsResponse = productsApi.getProducts(q = q, limit = 30, offset = offset)
            val products = productsResponse.results
            LoadResult.Page(
                data = products,
                prevKey = null,
                nextKey = null,
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(
                throwable = e
            )
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Product>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}