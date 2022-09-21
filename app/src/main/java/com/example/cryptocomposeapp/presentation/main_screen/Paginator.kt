package com.example.cryptocomposeapp.presentation.main_screen

interface Paginator<Key, Item> {
    suspend fun loadNextItems()
    fun reset()
}