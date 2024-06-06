package com.jdrt.melitest.presentation.product_detail.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.jdrt.melitest.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailBar(
    onBackClick: () -> Unit,
    onOpenInNavigator: () -> Unit
) {
    TopAppBar(
        title = {},
        modifier = Modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color.Transparent,
            actionIconContentColor = Color.Black,
            navigationIconContentColor = Color.Black
        ),
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back_arrow),
                    contentDescription = "ic_back_arrow"
                )
            }
        },
        actions = {
            IconButton(onClick = onOpenInNavigator) {
                Icon(painter = painterResource(id = R.drawable.ic_network), contentDescription = "ic_network")
            }
        }
    )
}

@Preview
@Composable
fun DetailBarPreview() {
    DetailBar(onBackClick = {}, onOpenInNavigator = {})
}
