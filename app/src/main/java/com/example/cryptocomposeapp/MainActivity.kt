@file:OptIn(ExperimentalCoilApi::class)

package com.example.cryptocomposeapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.cryptocomposeapp.ui.main_screen.items.CryptoItem
import com.example.cryptocomposeapp.ui.main_screen.MainViewModel
import com.example.cryptocomposeapp.ui.main_screen.ScreenState
import com.example.cryptocomposeapp.ui.theme.CryptoComposeAppTheme
import com.example.cryptocomposeapp.ui.theme.Shapes
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
//            val viewModel = viewModel<MainViewModel>()
            CryptoList(viewModel = viewModel) {
                Toast.makeText(this, it.title.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@Composable
fun CryptoList(
    viewModel: MainViewModel,
    selectedItem: (CryptoItem) -> Unit
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
                    .clickable { selectedItem(item) }
                    .padding(horizontal = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween) {

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        CryptoImage(item = item)
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

@Composable
fun CryptoImage(item: CryptoItem) {
    Image(
        painter = rememberImagePainter(item.imageLink),
        contentDescription = item.title,
        contentScale = ContentScale.Fit,
        modifier = Modifier
            .height(64.dp)
            .width(64.dp)
            .clip(Shapes.large)
    )
}


















