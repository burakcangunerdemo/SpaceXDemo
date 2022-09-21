package com.example.demospacex.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.demospacex.db.FavoriteRocketRepository
import com.example.demospacex.models.FavoriteRocket
import com.example.demospacex.models.RocketsItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RocketDetailViewModel
@Inject
constructor(private val repository: FavoriteRocketRepository, private val savedStateHandle: SavedStateHandle): ViewModel(){
    private val _response = MutableLiveData<RocketsItem>()
    val responseRocket:LiveData<RocketsItem>
        get() = _response

    init {
        savedStateHandle.get<RocketsItem>("rocket")?.let {
            changedRocketItem(it)
        }
    }

    private fun changedRocketItem(rocket: RocketsItem) = viewModelScope.launch {
        _response.postValue(rocket)
    }

    fun insertFavoriteRocket(favoriteRocket: FavoriteRocket) = viewModelScope.launch {
        repository.insertFavoriteRocket(favoriteRocket)
    }

    fun deleteFavoriteRocket(favoriteRocket: FavoriteRocket) = viewModelScope.launch {
        repository.deleteFavoriteRocket(favoriteRocket)
    }

    fun updateRocket(rocket: RocketsItem)= viewModelScope.launch {
        _response.postValue(rocket)
    }

}