package com.jdrt.melitest.presentation.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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

@Composable
fun ProductCard(
    modifier: Modifier = Modifier,
    product: Product,
    onClick: () -> Unit
) {
    Row (
        modifier = modifier.clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        ProductImage(product.thumbnail)
        Column (
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.padding(horizontal = Dimens.ExtraSmallPadding)
        ) {
            Text(
                text = product.title,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = "$ ${product.price.toString()}",
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
            )
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
        seller = Seller(id = 123, nickname = "123")
    )) {

    }
}
