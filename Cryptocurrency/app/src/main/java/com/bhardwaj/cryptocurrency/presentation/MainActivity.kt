package com.bhardwaj.cryptocurrency.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bhardwaj.cryptocurrency.presentation.detail_screen.components.CoinDetailScreen
import com.bhardwaj.cryptocurrency.presentation.list_screen.components.CoinListScreen
import com.bhardwaj.cryptocurrency.presentation.ui.theme.CryptocurrencyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptocurrencyTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.CoinListScreen.route
                    ) {
                        composable(Screen.CoinListScreen.route) {
                            CoinListScreen(navController)
                        }

                        composable(Screen.CoinDetailScreen.route + "/{coinId}") {
                            CoinDetailScreen()
                        }
                    }
                }
            }
        }
    }
}