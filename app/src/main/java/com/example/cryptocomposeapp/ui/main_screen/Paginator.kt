package com.example.cryptocomposeapp.ui.main_screen

interface Paginator<Key, Item> {
    suspend fun loadNextItems()
    fun reset()
}