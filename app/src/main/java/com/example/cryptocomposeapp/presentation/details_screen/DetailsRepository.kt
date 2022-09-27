package com.example.cryptocomposeapp.presentation.details_screen

import com.example.cryptocomposeapp.domain.dto.CryptoChartData
import com.example.cryptocomposeapp.domain.service.ApiService
import com.example.cryptocomposeapp.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class DetailsRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getCryptoChartData(id: String, days: String): Resource<CryptoChartData> {

        val response = try {
            apiService.getCryptoChartData(id, days)
        } catch (e: Exception) {
            return Resource.Error("An unknown error occured.")
        }
        return Resource.Success(response)
    }

}