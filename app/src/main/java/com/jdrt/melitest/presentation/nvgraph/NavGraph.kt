package com.jdrt.melitest.presentation.nvgraph

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.jdrt.melitest.presentation.product_detail.DetailScreen
import com.jdrt.melitest.presentation.product_detail.DetailViewModel
import com.jdrt.melitest.presentation.search.SearchScreen
import com.jdrt.melitest.presentation.search.SearchViewModel

@Composable
fun NavGraph(
    startDestination: String = Route.SearchProductsScreen.route
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = Route.SearchProductsScreen.route) {
            val viewModel: SearchViewModel = hiltViewModel()
            SearchScreen(
                state = viewModel.state.value,
                event = viewModel::onEvent,
                navigateToDetails = {
                    navController.navigate(Route.ProductDetailScreen.createRoute(it.id))
                }
            )
        }
        composable(
            route = Route.ProductDetailScreen.route,
            arguments = listOf(navArgument("productId") { type = NavType.StringType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")
            productId?.let {
                val viewModel: DetailViewModel = hiltViewModel()
                DetailScreen(
                    viewModel = viewModel,
                    productId = it,
                    navigateUp = { navController.popBackStack() }
                )
            }
        }
    }
}
