package com.example.inviochallenge.presentation.theme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.inviochallenge.domain.model.Data
import com.example.inviochallenge.presentation.theme.favorite.FavoriteScreen
import com.example.inviochallenge.presentation.theme.home.HomeScreen
import com.example.inviochallenge.presentation.theme.home.HomeViewModel
import com.example.inviochallenge.presentation.theme.splash.SplashScreen
import com.example.inviochallenge.presentation.theme.ui.InvioChallengeTheme
import com.example.inviochallenge.presentation.theme.website.WebsiteScreen
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
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

