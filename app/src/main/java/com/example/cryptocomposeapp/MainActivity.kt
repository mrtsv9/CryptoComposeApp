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
fun ClickableColumnDemo(selectedItem: (String) -> Unit) {
    LazyColumn {
        items(100)
        {
            Text(
                "User Name ${it + 1}",
                style = MaterialTheme.typography.h3,
                modifier = Modifier
                    .padding(10.dp)
                    .clickable {
                        selectedItem("$it is selected")
                    }
            )
            Divider(color = Color.Black, thickness = 4.dp)
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
        painter = painterResource(id = R.drawable.ic_bitcoin48),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .height(48.dp)
            .width(48.dp)
            .clip(RoundedCornerShape(corner = CornerSize(10.dp)))
    )
}

@Composable
fun CryptoListItem(item: CryptoItem, clickListener: (CryptoItem) -> Unit) {
    Card(
        modifier = Modifier
            .height(IntrinsicSize.Max)
            .padding(16.dp),
        elevation = 8.dp,
        shape = RoundedCornerShape(corner = CornerSize(12.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Color.LightGray)
                .clickable { clickListener(item) },
            verticalAlignment = Alignment.CenterVertically
        ) {
            CryptoImage(item = item)
            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(IntrinsicSize.Min),

                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start
            ) {
                Text(text = item.abbr.toString(), fontSize = 18.sp)
                Text(text = item.title.toString(), fontSize = 14.sp)
            }

            Spacer(modifier = Modifier.width(160.dp))

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .background(Color.Cyan)
                    .width(IntrinsicSize.Max),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.End,
            ) {
                Text(text = item.price.toString().plus(" $"), fontSize = 18.sp)
            }
        }
    }
}

















