package com.example.cryptocomposeapp.presentation.details_screen

import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cryptocomposeapp.presentation.base.ImageItem
import com.example.cryptocomposeapp.presentation.ui.theme.Shapes

@Composable
fun DetailsScreen(
    id: String,
    title: String,
    price: String,
    priceChange: String,
    marketCap: String,
    imageLink: String,
    viewModel: DetailsViewModel = hiltViewModel(),
) {

    remember {
        viewModel.chartId.value = title
        true
    }

    val currentPriceChange = remember {
        if (priceChange.toFloat() >= 0) "+ $priceChange $"
        else "$priceChange %"
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
            verticalArrangement = Arrangement.Center
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
                    Text(text = id, fontSize = 26.sp)
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(text = price, fontSize = 26.sp)
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(text = currentPriceChange,
                        color = currentPriceChangeColor,
                        fontSize = 26.sp)
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LinearCryptoChart(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp),
                    viewModel = viewModel
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Divider(color = Color.Black, thickness = 1.dp)
            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(0.9f),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(text = "Market cap: ", fontSize = 22.sp)
                Text(text = marketCap.plus(" $"), fontSize = 22.sp)
            }
        }
    }
}

