package com.example.inviochallenge.presentation.theme.home

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inviochallenge.domain.model.RoomModel
import com.example.inviochallenge.domain.use_case.delete_fav_uni.DeleteFavUniUseCase
import com.example.inviochallenge.domain.use_case.get_fav_uni.GetFavUniUseCase
import com.example.inviochallenge.domain.use_case.get_universities.GetUniversityUseCase
import com.example.inviochallenge.domain.use_case.insert_fav_uni.InsertFavUniUseCase
import com.example.inviochallenge.domain.use_case.is_favorite_exists.isFavoriteExistsUseCase
import com.example.inviochallenge.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUniversitiesUseCase : GetUniversityUseCase,
    private val getFavUniUseCase: GetFavUniUseCase ,
    private val insertFavUniUseCase: InsertFavUniUseCase ,
    private val deleteFavUniUseCase: DeleteFavUniUseCase ,
    private val isFavoriteExistsUseCase: isFavoriteExistsUseCase ,
) : ViewModel() {

    private val _state = mutableStateOf<HomeState>(HomeState())
    val state: State<HomeState> = _state

    init {
        loadFavUni()
    }

    private fun getUniversities(pageNumber: Int) {
        getUniversitiesUseCase.executeGetUniversities(pageNumber).onEach {
            when (it) {
                is Resource.Success -> {
                    val updatedList = it.data ?: emptyList()
                    val currentState = _state.value

                    Log.e("ababab",_state.value.page.toString())

                    _state.value = currentState.copy(
                        unis = currentState.unis + updatedList,
                        page = currentState.page + 1
                    )

                    Log.e("ababab",_state.value.page.toString())
                }
                is Resource.Loading -> {
                    _state.value = HomeState(isLoading = true)
                }
                is Resource.Error -> {
                    _state.value = HomeState(error = it.message ?: "Error")
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getFavoritesUni() {
        getFavUniUseCase.executeGetUniversities().onEach {
            when (it) {
                is Resource.Success -> {
                    _state.value = HomeState(favUnis = it.data ?: emptyList())
                }
                is Resource.Loading -> {
                    _state.value = HomeState(isLoading = true)
                }
                is Resource.Error -> {
                    _state.value = HomeState(error = it.message ?: "Error")
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun isFavoriteExists(name: String) {
        isFavoriteExistsUseCase.executeIsFavoriteExists(name).onEach {
            when (it) {
                is Resource.Success -> {
                    _state.value = HomeState(favExists = it.data!!)
                }
                is Resource.Loading -> {
                    _state.value = HomeState(isLoading = true)
                }
                is Resource.Error -> {
                    _state.value = HomeState(error = it.message ?: "Error")
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


    fun loadNextPage() {
        val currentPage = _state.value.page
        getUniversities(currentPage)
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
    fun isFavorite(name: String) {
        isFavoriteExists(name)
    }
}
