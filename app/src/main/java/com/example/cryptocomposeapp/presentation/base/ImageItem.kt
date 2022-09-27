package com.example.cryptocomposeapp.presentation.base

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.cryptocomposeapp.presentation.ui.theme.Shapes

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ImageItem(
    url: String,
    modifier: Modifier = Modifier,
    title: String = "Title",
) {
    Image(
        painter = rememberImagePainter(url),
        contentDescription = title,
        contentScale = ContentScale.Fit,
        modifier = modifier
            .height(64.dp)
            .width(64.dp)
            .clip(Shapes.large)
    )
}

