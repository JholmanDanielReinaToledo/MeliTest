package com.jdrt.melitest.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.jdrt.melitest.domain.model.Product
import com.jdrt.melitest.domain.model.Seller
import com.jdrt.melitest.util.Dimens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductCard(
    modifier: Modifier = Modifier,
    product: Product,
    onClick: () -> Unit
) {
    Card (
        onClick = { onClick() },
        modifier = modifier.fillMaxWidth().background(Color.Transparent)
    ){
        Row (
            verticalAlignment = Alignment.CenterVertically
        ) {
            ProductImage(product.thumbnail)
            Column (
                verticalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.padding(horizontal = Dimens.MediumPadding).background(Color.Transparent)
            ) {
                Text(text = product.condition)
                Spacer(modifier = Modifier.height(Dimens.MediumPadding))
                Text(
                    text = product.title,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(Dimens.MediumPadding))
                Text(
                    text = "$ ${product.price} ${product.currency}",
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
                )
            }
        }
    }
}

@Composable
fun ProductImage(url: String) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url.replace("http://", "https://"))
            .crossfade(true)
            .build(),
        contentDescription = null,
        modifier = Modifier
            .size(128.dp)
            .clip(RoundedCornerShape(8.dp)),
        contentScale = ContentScale.Crop
    )
}

@Preview(showBackground = true)
@Composable
fun ProductCardPreview() {
    ProductCard(product = Product(
        id = "1",
        price = 123123.0,
        title = "Producto",
        condition = "New",
        thumbnail = "",
        permalink = "",
        seller = Seller(id = 123, nickname = "123"),
        currency = "ARG"
    )) {

    }
}
