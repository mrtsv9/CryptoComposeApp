@file:OptIn(ExperimentalCoilApi::class)

package com.example.cryptocomposeapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.cryptocomposeapp.ui.item.CryptoItem
import com.example.cryptocomposeapp.ui.main_screen.MainViewModel
import com.example.cryptocomposeapp.ui.theme.CryptoComposeAppTheme

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.gelAllCrypto()

        viewModel.cryptoLiveData.observe(this) {
            setContent {
                CryptoComposeAppTheme {
                    CryptoList(it) {
                        Toast.makeText(this, it.title.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    }
}

@Composable
fun CryptoList(list: List<CryptoItem>, selectedItem: (CryptoItem) -> Unit) {

    val cryptoList = remember {
        list
    }

    LazyColumn {
        items(items = cryptoList, itemContent = {
            CryptoListItem(item = it) { cryptoItem ->
                selectedItem(cryptoItem)
            }
        })
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
            .clip(RoundedCornerShape(corner = CornerSize(10.dp)))
    )
}

@Composable
fun CryptoListItem(item: CryptoItem, clickListener: (CryptoItem) -> Unit) {
    Card(
        modifier = Modifier
            .height(120.dp)
            .padding(horizontal = 12.dp, vertical = 8.dp),
        elevation = 12.dp,
        shape = RoundedCornerShape(corner = CornerSize(12.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(horizontal = 12.dp)
                .clickable { clickListener(item) },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Row(verticalAlignment = Alignment.CenterVertically) {
                CryptoImage(item = item)
                Spacer(modifier = Modifier.width(16.dp))

                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(IntrinsicSize.Min),
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

















