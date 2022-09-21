package com.example.cryptocomposeapp.domain.dto

import com.example.cryptocomposeapp.presentation.main_screen.items.CryptoItem
import com.google.gson.annotations.SerializedName
import java.math.RoundingMode

data class CryptoResponse (
    var id: String,
    @SerializedName("symbol")
    var abbr: String,
    @SerializedName("name")
    var title: String,
    @SerializedName("image")
    var imageLink: String,
    @SerializedName("current_price")
    var price: String,
    @SerializedName("market_cap")
    var marketCap: String?,
    @SerializedName("price_change_percentage_24h")
    var priceChange: String?
)

fun CryptoResponse.toCryptoItem(): CryptoItem {
    price = if (price.toFloat() >= 100000000) {
        price.dropLast(6).plus(" B")
    }
    else if (price.toFloat() >= 1) {
        price.plus(" $")
    } else {
        val decimal = price.toBigDecimal().setScale(5, RoundingMode.HALF_EVEN)
        decimal.toString().plus(" $")
    }
    return CryptoItem(
        id = id,
        abbr = abbr,
        title = title,
        imageLink = imageLink,
        price = price,
        marketCap = marketCap,
        priceChange = priceChange
    )
}
