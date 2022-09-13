package com.example.cryptocomposeapp.ui.item

data class CryptoItem(
    val id: String?,
    val abbr: String?,
    val title: String?,
    val imageLink: String?,
    val price: String?,
    var marketCap: String?,
    var priceChange: String?
)