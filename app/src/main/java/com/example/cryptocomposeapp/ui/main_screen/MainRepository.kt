package com.example.cryptocomposeapp.ui.main_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cryptocomposeapp.domain.dto.CryptoResponse
import com.example.cryptocomposeapp.domain.dto.toCryptoItem
import com.example.cryptocomposeapp.domain.service.ApiService
import com.example.cryptocomposeapp.ui.main_screen.items.CryptoItem
import kotlinx.coroutines.delay
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getCryptosByPage(page: Int): Result<List<CryptoItem>> {
        delay(500)
        val result = apiService.getCryptosByPage(page)
        return if (!result.isSuccessful or result.body().isNullOrEmpty()) Result.success(emptyList())
        else Result.success(result).map { response ->
            response.body()!!.map {
                it.toCryptoItem()
            }
        }
    }

}