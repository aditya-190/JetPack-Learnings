package com.bhardwaj.cryptocurrency.presentation.list_screen

import com.bhardwaj.cryptocurrency.domain.model.Coin

data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String = ""
)
