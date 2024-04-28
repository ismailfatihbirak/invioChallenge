package com.example.inviochallenge.presentation.splash

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.inviochallenge.R
import com.example.inviochallenge.presentation.home.HomeViewModel
import com.google.gson.Gson
import kotlinx.coroutines.delay
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun SplashScreen(navController: NavController, viewModel: HomeViewModel = hiltViewModel()) {
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center){
        Image(painter = painterResource(id = R.drawable.big_elips),
            contentDescription = "")
        Image(painter = painterResource(id = R.drawable.little_elips), contentDescription = "")
        Image(painter = painterResource(id = R.drawable.splash_icon), contentDescription = "")
    }
    viewModel.loadNextPage()
    LaunchedEffect(true){
        delay(1000)
        val state = viewModel.state.value
        if (state!=null){
            navController.navigate("home"){
                popUpTo("splash"){ inclusive = true}
            }
        }
    }



}

