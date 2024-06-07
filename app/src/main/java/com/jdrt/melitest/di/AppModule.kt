package com.jdrt.melitest.di

import com.jdrt.melitest.data.ProductsApi
import com.jdrt.melitest.data.repository.ProductRepositoryImpl
import com.jdrt.melitest.domain.repository.ProductRepository
import com.jdrt.melitest.domain.usecases.products.GetProductDetail
import com.jdrt.melitest.domain.usecases.products.GetProducts
import com.jdrt.melitest.domain.usecases.products.ProductsUseCases
import com.jdrt.melitest.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideProductsApi(): ProductsApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideProductsRepository(productsApi: ProductsApi): ProductRepository = ProductRepositoryImpl(productsApi)

    @Provides
    @Singleton
    fun provideProductsUseCases(
        productRepository: ProductRepository
    ): ProductsUseCases {
        return ProductsUseCases(
            getProducts = GetProducts(productRepository),
            getProductDetail = GetProductDetail(productRepository)
        )
    }
}
