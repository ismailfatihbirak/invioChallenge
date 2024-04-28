package com.example.inviochallenge.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.inviochallenge.presentation.favorite.FavoriteScreen
import com.example.inviochallenge.presentation.home.HomeScreen
import com.example.inviochallenge.presentation.splash.SplashScreen
import com.example.inviochallenge.presentation.theme.ui.InvioChallengeTheme
import com.example.inviochallenge.presentation.website.WebsiteScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InvioChallengeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ScreenTransations()
                }
            }
        }
    }
}

@Composable
fun ScreenTransations() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "splash") {
        composable("splash") { backStackEntry ->
            SplashScreen(navController = navController)
        }
        composable("home") { backStackEntry ->
            HomeScreen(navController = navController)
        }
        composable("favorite") { backStackEntry ->
            FavoriteScreen(navController)
        }
        composable("website/{url}",
            arguments= listOf(
                navArgument("url") {type = NavType.StringType}
            )) { backStackEntry ->
            val url = backStackEntry.arguments!!.getString("url")
            WebsiteScreen(url = url!!)
        }



    }
}

