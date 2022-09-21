package com.example.cryptocomposeapp.presentation.main_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cryptocomposeapp.domain.dto.CryptoResponse
import com.example.cryptocomposeapp.domain.dto.toCryptoItem
import com.example.cryptocomposeapp.domain.service.ApiService
import com.example.cryptocomposeapp.presentation.main_screen.items.CryptoItem
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.delay
import javax.inject.Inject

@ActivityScoped
class MainRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getCryptosByPage(page: Int): Result<List<CryptoItem>> {
        delay(500)
        val result = apiService.getCryptosByPage(page)
        return if (result.isEmpty()) Result.success(emptyList())
        else Result.success(result).map { list ->
            list.map {
                it.toCryptoItem()
            }
        }
    }

}