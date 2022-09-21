package com.example.cryptocomposeapp.presentation.main_screen

import com.example.cryptocomposeapp.domain.dto.CryptoResponse
import com.google.common.truth.Truth.assertThat
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test

class TestMainRepository {

    private val cryptos = mutableListOf<CryptoResponse>()

    @Before
    fun setup() {
        cryptos.addAll(
            listOf(
                CryptoResponse(
                    "bitcoin",
                    "btc",
                    "title",
                    "link",
                    "50",
                    "100",
                    "0"
                ),
                CryptoResponse(
                    "usdt",
                    "tether",
                    "title",
                    "link",
                    "30",
                    "100",
                    "0"
                ),
                CryptoResponse(
                    "eth",
                    "ethereum",
                    "title",
                    "link",
                    "20",
                    "100",
                    "0"
                )
            )
        )
    }

    @After
    fun teardown() {
        cryptos.clear()
    }

    @Test
    fun `check if price equals value`() {
        assertThat(getTotalPrice()).isEqualTo(100)
    }

    @Test
    fun `check if id's equals value`() {
        assertThat(getCryptosId()).isEqualTo(getCryptosId())
    }

    private fun getTotalPrice(): Int {
        return cryptos.sumOf { it.price.toInt() }
    }

    private fun getCryptosId(): List<String> {
        return cryptos.map {
            it.id
        }
    }

}