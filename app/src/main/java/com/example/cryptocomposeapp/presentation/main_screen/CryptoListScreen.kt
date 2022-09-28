package com.example.cryptocomposeapp.presentation.main_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cryptocomposeapp.presentation.base.ImageItem
import com.example.cryptocomposeapp.presentation.ui.theme.Shapes
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun CryptoListScreen(
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel()
) {

    LazyColumn {
        items(viewModel.state.items.size) { i ->
            val item = viewModel.state.items[i]
            if (i >= viewModel.state.items.size - 1 && !viewModel.state.endReached && !viewModel.state.isLoading) {
                viewModel.loadNextItems()
            }
            Card(
                modifier = Modifier
                    .height(120.dp)
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                elevation = 12.dp,
                shape = Shapes.large
            ) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .clickable {
                        val encodedUrl =
                            URLEncoder.encode(item.imageLink, StandardCharsets.UTF_8.toString())
                        navController.navigate(
                            "cryptoDetails/${item.id}/${item.title}/" +
                                    "${item.price}/${item.priceChange}/" +
                                    "${item.marketCap}/${encodedUrl}"
                        )
                    }
                    .padding(horizontal = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween) {

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        ImageItem(item.imageLink.toString(), title = item.title.toString())
                        Spacer(modifier = Modifier.width(16.dp))

                        Column(
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(IntrinsicSize.Max),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.Start
                        ) {
                            Text(text = item.abbr.toString(), fontSize = 24.sp)
                            Text(text = item.title.toString(), fontSize = 16.sp)
                        }
                    }

                    Text(text = item.price.toString(), fontSize = 24.sp)
                }
            }
        }
        item {
            if (viewModel.state.isLoading) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator(color = Color.Green)
                }
            }
        }
    }
}