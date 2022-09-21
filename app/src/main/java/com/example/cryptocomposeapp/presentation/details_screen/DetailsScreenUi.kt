package com.example.cryptocomposeapp.presentation.details_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
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
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.cryptocomposeapp.presentation.main_screen.ImageItem
import com.example.cryptocomposeapp.presentation.ui.theme.Shapes

@OptIn(ExperimentalCoilApi::class)
@Composable
fun DetailsScreen(
    title: String,
    price: String,
    priceChange: String,
    marketCap: String,
    imageLink: String,
) {

    val currentPriceChange = remember {
        if (priceChange.toFloat() >= 0) "+ $priceChange $"
        else "$priceChange $"
    }

    val currentPriceChangeColor = remember {
        if (priceChange.toFloat() >= 0) Color.Green
        else Color.Red
    }

    Box(modifier = Modifier
        .fillMaxWidth()
        .fillMaxWidth(0.5f)
        .padding(vertical = 20.dp)) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(modifier = Modifier.fillMaxWidth(0.9f),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically) {

                ImageItem(
                    url = imageLink,
                    modifier = Modifier
                        .height(88.dp)
                        .width(88.dp)
                        .clip(Shapes.large),
                    title = title)
                Column(horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center) {
                    Text(text = title, fontSize = 26.sp)
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(text = price, fontSize = 26.sp)
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(text = currentPriceChange,
                        color = currentPriceChangeColor,
                        fontSize = 26.sp)
                }
            }

            Spacer(modifier = Modifier.height(12.dp))
            Divider(color = Color.Black, thickness = 1.dp)
            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(0.9f),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(text = "Market cap: ", fontSize = 22.sp)
                Text(text = marketCap.plus(" $"), fontSize = 22.sp)
            }
        }
    }
}








