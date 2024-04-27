package com.example.inviochallenge.presentation.theme.favorite

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.inviochallenge.R
import com.example.inviochallenge.domain.model.Data
import com.example.inviochallenge.domain.model.RoomModel
import com.example.inviochallenge.domain.model.Universities
import com.example.inviochallenge.presentation.theme.home.DataItem
import com.example.inviochallenge.presentation.theme.home.HomeViewModel
import com.example.inviochallenge.presentation.theme.home.UniversityItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreen(navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel()) {
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.height(50.dp),
                title = {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Text(
                            text = "Favoriler",
                            modifier = Modifier.align(Alignment.CenterStart)
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
                    }
                })
        }
    ){ innerPadding ->
        Column (
            modifier = Modifier
                .padding(innerPadding)
        ){
            if (homeViewModel.favState.value.favUnis.isNullOrEmpty()){
                Text(text = "Favori eklemediniz", fontSize = 25.sp)
            }else{
                homeViewModel.favState.value.favUnis.forEach { university ->
                    val uni = Universities(university.name,university.phone,university.fax,university.website,university.email,university.adress,university.rector)
                    UniversityItem(uni,navController,homeViewModel,Modifier.padding(all = 6.dp),false)
                }
            }
            
        }
    }
}

