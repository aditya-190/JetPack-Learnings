package com.bhardwaj.cryptocurrency.data.remote

import retrofit2.http.GET

class CoinPaprikaApi {
    @GET("/v1/coins")
    suspend fun getCoins() {
    }
}