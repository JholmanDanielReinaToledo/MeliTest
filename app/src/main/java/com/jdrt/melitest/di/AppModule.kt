package com.jdrt.melitest.di

import com.jdrt.melitest.data.ProductsApi
import com.jdrt.melitest.data.repository.ProductsRepositoryImpl
import com.jdrt.melitest.domain.repository.ProductsRepository
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
    fun provideProductsRepository(productsApi: ProductsApi): ProductsRepository = ProductsRepositoryImpl(productsApi)

    @Provides
    @Singleton
    fun provideProductsUseCases(
        productsRepository: ProductsRepository
    ): ProductsUseCases {
        return ProductsUseCases(
            getProducts = GetProducts(productsRepository),
            getProductDetail = GetProductDetail(productsRepository)
        )
    }
}
