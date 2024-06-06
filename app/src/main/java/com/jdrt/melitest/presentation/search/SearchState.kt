package com.jdrt.melitest.presentation.search

import androidx.paging.PagingData
import com.jdrt.melitest.domain.model.Product
import kotlinx.coroutines.flow.Flow

data class SearchState (
    val searchQuery: String = "",
    val products: Flow<PagingData<Product>> ? = null,
) {
}