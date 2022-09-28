package com.example.cryptocomposeapp.domain.service

import com.example.cryptocomposeapp.domain.dto.CryptoChartData
import com.example.cryptocomposeapp.domain.dto.CryptoResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("coins/markets?vs_currency=usd&order=market_cap_desc&per_page=20")
    suspend fun getCryptosByPage(
        @Query("page") page: Int
    ): List<CryptoResponse>

    @GET("coins/{id}/market_chart?vs_currency=usd")
    suspend fun getCryptoChartData(
        @Path("id") id: String,
        @Query("days") days: String
    ): CryptoChartData

}