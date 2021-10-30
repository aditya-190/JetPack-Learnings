package com.bhardwaj.cryptocurrency.presentation.detail_screen

import com.bhardwaj.cryptocurrency.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coin: CoinDetail? = null,
    val error: String = ""
)
