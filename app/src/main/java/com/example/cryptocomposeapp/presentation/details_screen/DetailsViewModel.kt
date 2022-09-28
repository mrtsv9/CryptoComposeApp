package com.example.cryptocomposeapp.presentation.details_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocomposeapp.domain.dto.CryptoChartData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val repository: DetailsRepository) :
    ViewModel() {

    val chartId = MutableStateFlow("")
    val chartDays = MutableStateFlow("")

    val progressBarState = MutableStateFlow(true)

    val chartData = MutableStateFlow(CryptoChartData(emptyList()))

    init {
        viewModelScope.launch {
            chartDays.collect {
                progressBarState.value = true
                getCryptoChartData(chartId.value, it)
            }
        }

    }

    private suspend fun getCryptoChartData(id: String, days: String) {
        repository.getCryptoChartData(id, days).data.let {
            progressBarState.value = false
            if (it != null) {
                chartData.value = it
            }
        }
    }

}