package com.example.inviochallenge.presentation.theme.favorite

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inviochallenge.domain.model.RoomModel
import com.example.inviochallenge.domain.use_case.delete_fav_uni.DeleteFavUniUseCase
import com.example.inviochallenge.domain.use_case.get_fav_uni.GetFavUniUseCase
import com.example.inviochallenge.domain.use_case.get_universities.GetUniversityUseCase
import com.example.inviochallenge.domain.use_case.insert_fav_uni.InsertFavUniUseCase
import com.example.inviochallenge.presentation.theme.home.HomeState
import com.example.inviochallenge.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getFavUniUseCase: GetFavUniUseCase,
    private val insertFavUniUseCase: InsertFavUniUseCase,
    private val deleteFavUniUseCase: DeleteFavUniUseCase,
) : ViewModel() {

    private val _state = mutableStateOf<FavoriteState>(FavoriteState())
    val state: State<FavoriteState> = _state

    init {
        loadFavUni()
    }

    private fun getFavoritesUni() {
        getFavUniUseCase.executeGetUniversities().onEach {
            when (it) {
                is Resource.Success -> {
                    _state.value = FavoriteState(favUnis = it.data ?: emptyList())
                }
                is Resource.Loading -> {
                    _state.value = FavoriteState(isLoading = true)
                }
                is Resource.Error -> {
                    _state.value = FavoriteState(error = it.message ?: "Error")
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun insertFavoritesUni(uni : RoomModel) {
        viewModelScope.launch{
            insertFavUniUseCase.executeInsertFavUni(uni)
        }
    }

    private fun deleteFavoritesUni(name : String) {
        viewModelScope.launch{
            deleteFavUniUseCase.executeDeleteFavUni(name)
        }
    }

    fun loadFavUni() {
        getFavoritesUni()
    }
    fun insertFavUni(uni: RoomModel) {
        insertFavoritesUni(uni)
    }
    fun deleteFavUni(name: String) {
        deleteFavoritesUni(name)
    }
}