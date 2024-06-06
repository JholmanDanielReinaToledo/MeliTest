package com.jdrt.melitest.presentation.nvgraph

sealed class Route(
    val route: String
) {
    object ProductDetailScreen : Route("productDetailScreen/{productId}") {
        fun createRoute(productId: String) = "productDetailScreen/$productId"
    }
    object SearchProductsScreen: Route(route = "searchProductsScreen")
}