package com.bhardwaj.cryptocurrency.domain.repository

import com.bhardwaj.cryptocurrency.data.remote.dto.CoinDetailDto
import com.bhardwaj.cryptocurrency.data.remote.dto.CoinDto

interface CoinRepository {
    suspend fun getCoins(): List<CoinDto>
    suspend fun getCoinById(coinId: String): CoinDetailDto
}