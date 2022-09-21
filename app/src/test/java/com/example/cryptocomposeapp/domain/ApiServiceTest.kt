package com.example.cryptocomposeapp.domain

import com.TestCoroutineRule
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ApiServiceTest {

    @get:Rule
    val mainCoroutineRule = TestCoroutineRule()

    @Test
    fun `check if the http code is equals to 200`() = runTest {
        val apiService = RetrofitInstance.getRetrofitInstance().create(FakeApiService::class.java)

        val response = runBlocking {
            apiService.getCryptos()
        }

        assertThat(response.code()).isEqualTo(200)

    }

}