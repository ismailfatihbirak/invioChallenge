package com.example.inviochallenge.presentation.theme.favorite

import com.example.inviochallenge.domain.model.Data
import com.example.inviochallenge.domain.model.RoomModel

data class FavoriteState(
    val isLoading : Boolean = false,
    val error : String = "",
    val favUnis : List<RoomModel> = listOf()
)
