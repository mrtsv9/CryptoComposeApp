package com.example.cryptocomposeapp.domain

import com.example.cryptocomposeapp.domain.dto.CryptoDetailsResponse
import com.example.cryptocomposeapp.domain.dto.CryptoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FakeApiService {

    @GET("coins/markets?vs_currency=usd&order=market_cap_desc&per_page=20&page=1&sparkline=false")
    suspend fun getCryptos(): Response<List<CryptoResponse>>

}