package com.jdrt.melitest.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jdrt.melitest.domain.model.Product
import com.jdrt.melitest.util.Constants

class ProductsPagingSource(
    private val productsApi: ProductsApi,
    private val q: String,
): PagingSource<Int, Product>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        val page = params.key ?: 1
        val offset = (page - 1) * Constants.PAGE_SIZE

        return try {
            val productsResponse = productsApi.getProducts(q = q, limit = Constants.PAGE_SIZE, offset = offset)
            val products = productsResponse.results
            val nextKey = if (products.isEmpty()) null else page + 1
            val prevKey = if (page == 1) null else page - 1

            LoadResult.Page(
                data = products,
                prevKey = prevKey,
                nextKey = nextKey,
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
