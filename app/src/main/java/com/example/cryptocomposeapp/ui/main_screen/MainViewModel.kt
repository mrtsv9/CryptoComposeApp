package com.example.cryptocomposeapp.ui.main_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocomposeapp.domain.RetrofitInstance
import com.example.cryptocomposeapp.domain.dto.toCryptoItem
import com.example.cryptocomposeapp.domain.service.ApiService
import com.example.cryptocomposeapp.ui.item.CryptoItem
import kotlinx.coroutines.launch
import retrofit2.Retrofit

class MainViewModel : ViewModel() {

    private val retrofit = RetrofitInstance.getRetrofitInstance()
    private val api = retrofit.create(ApiService::class.java)

    private val _cryptoLiveData: MutableLiveData<List<CryptoItem>> by lazy {
        MutableLiveData<List<CryptoItem>>()
    }

    val cryptoLiveData: LiveData<List<CryptoItem>> = _cryptoLiveData

    fun gelAllCrypto() {
        viewModelScope.launch {
            val cryptos = api.getCryptos().map { it.toCryptoItem() }
            _cryptoLiveData.postValue(cryptos)
        }
    }


}