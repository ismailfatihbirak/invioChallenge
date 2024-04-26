package com.example.inviochallenge.presentation.theme.home

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.inviochallenge.R
import com.example.inviochallenge.domain.model.Data
import com.example.inviochallenge.domain.model.RoomModel
import com.example.inviochallenge.domain.model.Universities




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController,viewModel: HomeViewModel = hiltViewModel()) {

    val stateList by remember(viewModel.state) {
        derivedStateOf {
            viewModel.state.value.unis
        }
    }
    var _stateList by rememberSaveable {
        mutableStateOf(listOf<Data>())
    }

    LaunchedEffect(stateList) {
        _stateList += stateList.subtract(_stateList)
    }



    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.height(50.dp),
                title = {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Text(
                            text = "Universiteler",
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        navController.navigate("favorite")
                    }) {
                        Icon(imageVector = Icons.Default.Favorite, contentDescription = "")
                    }
                })
        }
    ){ innerPadding ->
        Column (
            modifier = Modifier
                .padding(innerPadding)
        ){

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(_stateList) { item ->
                    DataItem(item,navController,viewModel)
                }
                item {
                    LaunchedEffect(true) {
                        viewModel.loadNextPage()
                    }
                }
            }

        }
    }
}

@Composable
fun DataItem(data: Data,navController: NavController,viewModel: HomeViewModel) {
    var isExpanded by rememberSaveable { mutableStateOf(false) }

    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 30.dp,),
        modifier = Modifier.padding(6.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (data.universities.isNotEmpty()){
                if (!isExpanded){
                    IconButton(onClick = { isExpanded = !isExpanded }) {
                        Icon(Icons.Default.Add,
                            contentDescription = "")
                    }
                }else{
                    IconButton(onClick = { isExpanded = !isExpanded }) {
                        Icon(painter = painterResource(id = R.drawable.baseline_remove_24),
                            contentDescription = "")
                    }

                }

            }
            Text(
                data.province ?: "province",
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .weight(1f)
            )
        }
        if (isExpanded) {
            data.universities.forEach { university ->
                UniversityItem(university,navController,viewModel,
                    Modifier.padding(
                    start = 30.dp,
                    top = 6.dp,
                    bottom = 6.dp))

            }
        }
    }
}

@Composable
fun UniversityItem(university: Universities,navController: NavController,viewModel: HomeViewModel,modifier: Modifier) {
    var isExpanded by rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current
    viewModel.isFavorite(university.name!!)
    val isUniversityFavorite = rememberSaveable { mutableStateOf(viewModel.state.value.favExists) }
    if (isUniversityFavorite.value == false){
        Log.e("axax","true")
    }else{
        Log.e("axax","false")
    }
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 30.dp,),
        modifier = modifier
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (university.phone != "-"){
                if (!isExpanded){
                    IconButton(onClick = { isExpanded = !isExpanded }) {
                        Icon(Icons.Default.Add,
                            contentDescription = "")
                    }
                }else{
                    IconButton(onClick = { isExpanded = !isExpanded }) {
                        Icon(painter = painterResource(id = R.drawable.baseline_remove_24),
                            contentDescription = "")
                    }

                }

            }

            Text(
                university.name ?: "name",
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .weight(1f)
            )

            IconButton(onClick = {
                if (isUniversityFavorite.value == true) {
                    viewModel.deleteFavUni(university.name!!)
                    isUniversityFavorite.value = false
                } else {
                    val insertUni = RoomModel(
                        uni_id = 0,
                        icon_state = "true",
                        name = university.name ?: "",
                        phone = university.phone ?: "",
                        fax = university.fax ?: "",
                        website = university.website ?: "",
                        email = university.email ?: "",
                        adress = university.adress ?: "",
                        rector = university.rector ?: "",
                    )
                    viewModel.insertFavUni(insertUni)
                    isUniversityFavorite.value = true
                }
            }) {
                Icon(
                    imageVector = if (isUniversityFavorite.value == true) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = ""
                )
            }

        }
    }

    if (isExpanded) {
        Column (modifier = Modifier.padding(start = 30.dp, top = 2.dp, bottom = 2.dp)){
            TextRow(string = "phone:${university.phone}" , onClick = {
                openDialer(context,university.phone!!)
            })
            Divider()
            TextRow(string = "fax:${university.fax}" , onClick = {})
            Divider()
            TextRow(string = "website:${university.website}" , onClick = {
                    navController.navigate("website/${Uri.encode(university.website)}")
            })
            Divider()
            TextRow(string = "email:${university.email}" , onClick = {})
            Divider()
            TextRow(string = "adress:${university.adress}" , onClick = {})
            Divider()
            TextRow(string = "rector:${university.rector}" , onClick = {})
            Divider()
        }
    }
}

@Composable
fun TextRow(string:String,onClick: () -> Unit) {
    Text(
        string,
        modifier = Modifier
            .padding(vertical = 10.dp)
            .clickable { onClick() }
    )
}

fun openDialer(context: Context, phoneNumber: String) {
    val intent = Intent(Intent.ACTION_DIAL).apply {
        data = Uri.parse("tel:$phoneNumber")
    }
    context.startActivity(intent)
}













