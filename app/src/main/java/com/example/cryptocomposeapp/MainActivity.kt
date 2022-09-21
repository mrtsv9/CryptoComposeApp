package com.example.cryptocomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import com.example.cryptocomposeapp.presentation.details_screen.DetailsScreen
import com.example.cryptocomposeapp.presentation.main_screen.CryptoListScreen
import com.example.cryptocomposeapp.presentation.main_screen.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

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
                    route = "cryptoDetails/{title}/{price}/{priceChange}/{marketCap}/{imageLink}",
                    arguments = listOf(
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
                    val title = it.arguments?.getString("title")!!
                    val price = it.arguments?.getString("price")!!
                    val priceChange = it.arguments?.getString("priceChange")!!
                    val marketCap = it.arguments?.getString("marketCap")!!
                    val imageLink = it.arguments?.getString("imageLink")!!

                    DetailsScreen(
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



