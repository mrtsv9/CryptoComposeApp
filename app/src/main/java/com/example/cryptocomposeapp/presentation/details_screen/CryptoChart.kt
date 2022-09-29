package com.example.cryptocomposeapp.presentation.details_screen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cryptocomposeapp.presentation.ui.theme.Shapes
import com.example.cryptocomposeapp.util.toChartAxisValue

@Composable
fun LinearCryptoChart(
    modifier: Modifier,
    viewModel: DetailsViewModel,
) {

    val progressBarStatus by viewModel.progressBarState.collectAsState()

    val cryptoChartData by viewModel.chartData.collectAsState()
    remember {
        viewModel.chartDays.value = "1"
        true
    }

    val chartData = cryptoChartData.prices.map {
        it[1]
    }

    if (chartData.isEmpty()) return

    val maxY = chartData.max()

    val minY = chartData.min()

    val yAxis = maxY - minY

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(maxY.toChartAxisValue().plus(" $"), fontSize = 12.sp)
            Text(minY.toChartAxisValue().plus(" $"), fontSize = 12.sp)
        }

        Divider(modifier = Modifier
            .fillMaxHeight()
            .width(1.dp), color = Color.Gray)

        if (progressBarStatus) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CircularProgressIndicator(color = Color.Green)
            }
        } else
            Canvas(modifier = modifier) {
                val totalCryptosCount = chartData.size

                chartData.forEachIndexed { index, _ ->
                    if (totalCryptosCount >= index + 2) {
                        drawLine(
                            start = Offset(
                                x = size.width / totalCryptosCount * (index + 1),
                                y = (1 - (chartData[index] - minY) / yAxis) * size.height
                            ),
                            end = Offset(
                                x = size.width / totalCryptosCount * (index + 2),
                                y = (1 - (chartData[index + 1] - minY) / yAxis) * size.height
                            ),
                            color = Color(40, 193, 218),
                            strokeWidth = 4f
                        )
                    }
                }
            }
    }

    Spacer(modifier = Modifier.height(12.dp))

    val radioOptions = listOf("24h", "1w", "1m", "6m", "1y", "All")

    val (selectedOption: String, onOptionSelected: (String) -> Unit) = remember {
        mutableStateOf(
            "24h"
        )
    }

    Row(
        modifier = Modifier
            .selectableGroup()
            .fillMaxWidth(0.9f)
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        radioOptions.forEach { days ->
            SelectableButton(
                days = days,
                isSelectedOption = selectedOption == days,
                onSelectOption = { selectedDay ->
                    if (selectedDay == selectedOption) {
                        onOptionSelected(selectedDay)
                    } else {
                        viewModel.chartDays.value = mapDaysToRequestParams(days)
                        onOptionSelected(selectedDay)
                    }
                })
        }
    }

}

fun mapDaysToRequestParams(it: String): String {
    return when (it) {
        "24h" -> "1"
        "1w" -> "7"
        "1m" -> "30"
        "6m" -> "180"
        "1y" -> "365"
        "All" -> "max"
        else -> ""
    }
}

fun checkboxResource(isSelected: Boolean): Color {
    return if (isSelected) {
        Color.Green
    } else {
        Color.LightGray
    }
}

@Composable
fun SelectableButton(
    days: String,
    isSelectedOption: Boolean,
    onSelectOption: (String) -> Unit,
) {
    Card(
        modifier = Modifier
            .width(44.dp)
            .height(32.dp)
            .clickable(interactionSource = remember {
                MutableInteractionSource()
            }, indication = null) {
                onSelectOption(days)
            },
        shape = Shapes.large
    ) {
        Row(horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(checkboxResource(isSelected = isSelectedOption))) {
            Text(
                text = days,
                fontSize = 18.sp)
        }
    }
}