package com.jdrt.melitest.presentation.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.paging.cachedIn
import com.jdrt.melitest.domain.usecases.products.ProductsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.ViewModel

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val productsUseCases: ProductsUseCases
): ViewModel() {
    private val _state = mutableStateOf(SearchState())
    val state: State<SearchState> = _state

    fun onEvent(e: SearchEvent) {
        when (e) {
            is SearchEvent.UpdateSearchQuery -> {
                _state.value = state.value.copy(searchQuery = e.searchQuery)
            }
            is SearchEvent.SearchProducts -> {
                searchProducts()
            }
        }
    }

    fun searchProducts() {
        val products = productsUseCases.getProducts(q = state.value.searchQuery).cachedIn(viewModelScope)
        _state.value = state.value.copy(products = products)
    }
}