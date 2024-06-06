package com.jdrt.melitest.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.jdrt.melitest.domain.model.Product
import com.jdrt.melitest.util.Dimens
import com.jdrt.melitest.R

@Composable
fun ProductsList(
    modifier: Modifier = Modifier,
    products: LazyPagingItems<Product>,
    onClick: (Product) -> Unit
) {
    val handlePagingResult = handlePagingResult(products = products)
    if (handlePagingResult) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(Dimens.MediumPadding),
            contentPadding = PaddingValues(all = Dimens.ExtraSmallPadding2)
        ) {
            items(count = products.itemCount) {
                products[it]?.let { product ->
                    ProductCard(
                        product = product,
                        onClick = { onClick(product) }
                    )
                }
            }
        }
    }
}

@Composable
fun handlePagingResult(products: LazyPagingItems<Product>): Boolean {
    val loadState = products.loadState
    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }

    return when {
        loadState.refresh is LoadState.Loading -> {
            ShimmerEffect()
            false
        }
        
        products.itemCount == 0 -> {
            Empty()
            false
        }

        error != null -> {
            false
        }

        else -> true

    }
}

@Composable
private fun Empty() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = null,
                modifier = Modifier.size(Dimens.IconSize)
            )
            Text(text = stringResource(R.string.not_found_products))
            Text(text =  stringResource(R.string.with_description))
            Spacer(modifier = Modifier.height(Dimens.MediumPadding))
            Text(text = stringResource(R.string.try_other))
        }
    }
}

@Composable
private fun ShimmerEffect() {
    Column(verticalArrangement = Arrangement.spacedBy(Dimens.MediumPadding)) {
        repeat(10) {
            ProductCardShimmerEffect(
                modifier = Modifier.padding(horizontal = Dimens.MediumPadding)
            )
        }
    }
}
