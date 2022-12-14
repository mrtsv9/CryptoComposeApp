package com.example.cryptocomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.cryptocomposeapp.presentation.details_screen.DetailsScreen
import com.example.cryptocomposeapp.presentation.main_screen.CryptoListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = "cryptoList"
            ) {
                composable("cryptoList") {
                    CryptoListScreen(navController = navController)
                }
                composable(
                    route = "cryptoDetails/{id}/{title}/{price}/{priceChange}/{marketCap}/{imageLink}",
                    arguments = listOf(
                        navArgument("id") {
                            type = NavType.StringType
                        },
                        navArgument("title") {
                            type = NavType.StringType
                        },
                        navArgument("price") {
                            type = NavType.StringType
                        },
                        navArgument("priceChange") {
                            type = NavType.StringType
                        },
                        navArgument("marketCap") {
                            type = NavType.StringType
                        },
                        navArgument("imageLink") {
                            type = NavType.StringType
                        }
                    )
                ) {
                    val id = it.arguments?.getString("id")!!
                    val title = it.arguments?.getString("title")!!
                    val price = it.arguments?.getString("price")!!
                    val priceChange = it.arguments?.getString("priceChange")!!
                    val marketCap = it.arguments?.getString("marketCap")!!
                    val imageLink = it.arguments?.getString("imageLink")!!

                    DetailsScreen(
                        id = id,
                        title = title,
                        price = price,
                        priceChange = priceChange,
                        marketCap = marketCap,
                        imageLink = imageLink
                    )
                }
            }
        }
    }

}