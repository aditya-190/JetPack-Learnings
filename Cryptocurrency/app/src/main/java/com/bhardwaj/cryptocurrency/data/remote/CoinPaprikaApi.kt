package com.bhardwaj.cryptocurrency.data.remote

class CoinPaprikaApi {
    @GET(/v1/coins)
    suspend fun getCoins()
}