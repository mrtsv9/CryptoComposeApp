package com.example.cryptocomposeapp.util

fun Float.toChartAxisValue(): String {
    return if (this >= 100.0) this.toInt().toString()
    else if (this >= 10.0) this.toInt().toString()
    else String.format("%.3f", this)
}