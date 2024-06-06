package com.jdrt.melitest.presentation.product_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jdrt.melitest.domain.model.ProductDetail
import com.jdrt.melitest.domain.usecases.products.ProductsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val productsUseCases: ProductsUseCases
) : ViewModel() {
    fun getProductDetail(id: String, onResult: (ProductDetail?) -> Unit) {
        viewModelScope.launch {
            try {
                val productDetail = productsUseCases.getProductDetail(id)
                onResult(productDetail)
            } catch (e: Exception) {
                e.printStackTrace()
                onResult(null)
            }
        }
    }
}