package com.jdrt.melitest.data

import com.jdrt.melitest.data.dto.ProductsResponse
import com.jdrt.melitest.domain.model.ProductDetail
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductsApi {
    @GET("sites/MLA/search")
    suspend fun getProducts(
        @Query("q") q: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
    ): ProductsResponse

    @GET("/items/{ids}")
    suspend fun getProductDetail(@Path("ids") id: String): ProductDetail
}
