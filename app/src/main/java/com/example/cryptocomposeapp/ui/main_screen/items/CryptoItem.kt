package com.example.cryptocomposeapp.ui.main_screen.items

import java.io.Serializable

data class CryptoItem(
    val id: String?,
    val abbr: String?,
    val title: String?,
    val imageLink: String?,
    val price: String?,
    var marketCap: String?,
    var priceChange: String?
): Serializable