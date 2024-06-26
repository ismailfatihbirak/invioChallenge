package com.example.inviochallenge.presentation.home

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
import com.example.inviochallenge.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
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
) : ViewModel() {

    private val _state = mutableStateOf<HomeState>(HomeState())
    val state: State<HomeState> = _state

    private val _favState = mutableStateOf<HomeState>(HomeState())
    val favState: State<HomeState> = _favState

    private val currentPage = MutableStateFlow(0)

    init {
        loadFavUni()
    }

    private fun getUniversities(pageNumber: Int) {
        getUniversitiesUseCase.executeGetUniversities(pageNumber).onEach {
            when (it) {
                is Resource.Success -> {
                    val updatedList = it.data ?: emptyList()
                    val currentState = _state.value
                    _state.value = currentState.copy(
                        unis = currentState.unis + updatedList
                    )
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
                    _favState.value = HomeState(favUnis = it.data ?: emptyList())
                }
                is Resource.Loading -> {
                    _favState.value = HomeState(isLoading = true)
                }
                is Resource.Error -> {
                    _favState.value = HomeState(error = it.message ?: "Error")
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
        currentPage.value++
        getUniversities(currentPage.value)
    }
    fun loadFavUni() {
        getFavoritesUni()
    }
    fun insertFavUni(uni: RoomModel) {
        insertFavoritesUni(uni)
    }
    fun deleteFavUni(name: String) {
        deleteFavoritesUni(name)
        loadFavUni()
    }

}
