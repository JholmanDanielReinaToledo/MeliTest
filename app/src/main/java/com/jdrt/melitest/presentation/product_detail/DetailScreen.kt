package com.jdrt.melitest.presentation.product_detail

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.jdrt.melitest.domain.model.ProductDetail
import com.jdrt.melitest.presentation.common.ImageCarousel
import com.jdrt.melitest.presentation.product_detail.components.DetailBar
import com.jdrt.melitest.util.Dimens
import com.jdrt.melitest.R

@Composable
fun DetailScreen(
    viewModel: DetailViewModel,
    productId: String,
    navigateUp: () -> Unit
) {
    val context = LocalContext.current
    var productDetail by remember { mutableStateOf<ProductDetail?>(null) }

    LaunchedEffect(productId) {
        viewModel.getProductDetail(productId) { detail ->
            productDetail = detail
        }
    }

    if (productDetail != null) {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
        ) {
            DetailBar(
                onBackClick = navigateUp,
                onOpenInNavigator = {
                    Intent(Intent.ACTION_VIEW).also {
                        it.data = Uri.parse(productDetail?.permalink)
                        if (it.resolveActivity(context.packageManager) != null) {
                            context.startActivity(it)
                        }
                    }
                }
            )

            LazyColumn(modifier = Modifier.fillMaxWidth(), contentPadding = PaddingValues(Dimens.MediumPadding)) {
                item {
                    productDetail?.pictures?.let { ImageCarousel(imageUrls = it.map { it.url }) }

                    Spacer(modifier = Modifier.height(Dimens.MediumPadding))

                    Text(text = "${productDetail?.title}", maxLines = 3, style = MaterialTheme.typography.bodyLarge)
                    Text(text = "$${productDetail?.price}", fontWeight = FontWeight.Bold)

                    Spacer(modifier = Modifier.height(Dimens.HeightPadding))
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = stringResource(R.string.characteristics), fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(Dimens.HeightPadding))
                        productDetail?.attributes?.forEach {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ){
                                Text(text = "${it.name}: \n${it.valueName}\n")
                            }
                        }
                    }
                }
            }
        }
    } else {
        Text(text = stringResource(id = R.string.loading))
    }
}
