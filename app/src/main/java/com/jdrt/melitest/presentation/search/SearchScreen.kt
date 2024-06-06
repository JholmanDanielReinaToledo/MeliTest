package com.jdrt.melitest.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.jdrt.melitest.domain.model.Product
import com.jdrt.melitest.presentation.common.ProductsList
import com.jdrt.melitest.presentation.common.SearchBar
import com.jdrt.melitest.util.Dimens

@Composable
fun SearchScreen(
    state: SearchState,
    event: (SearchEvent) -> Unit,
    navigateToDetails: (Product) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(
                top = Dimens.MediumPadding,
                start = Dimens.MediumPadding,
                end = Dimens.MediumPadding
            )
            .statusBarsPadding()
    ) {
        SearchBar(
            text = state.searchQuery,
            readOnly = false,
            onValueChange = { event(SearchEvent.UpdateSearchQuery(it)) },
            onSearch = { event(SearchEvent.SearchProducts) }
        )
        Spacer(modifier = Modifier.height(Dimens.MediumPadding))
        state.products?.let {
            val products = it.collectAsLazyPagingItems()
            ProductsList(products = products, onClick = navigateToDetails)
        }
    }
}