package com.example.cryptocomposeapp.domain.dto

import com.example.cryptocomposeapp.ui.item.CryptoItem
import com.google.gson.annotations.SerializedName

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
