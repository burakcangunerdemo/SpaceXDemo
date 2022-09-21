package com.example.demospacex.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demospacex.db.FavoriteRocketRepository
import com.example.demospacex.models.RocketsItem
import com.example.demospacex.repository.DemoSpaceXRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel
@Inject
constructor(private val repository: FavoriteRocketRepository): ViewModel(){
    private val _response = MutableLiveData<List<RocketsItem>>()
    val responseFavoriteRockets:LiveData<List<RocketsItem>>
        get() = _response

    init {
        getAllFavoriteRockets()
    }

    private fun getAllFavoriteRockets() = viewModelScope.launch {
        repository.getAllFavoriteRockets().let { response ->
            _response.postValue(response)
        }
    }

    fun refresh(){
        getAllFavoriteRockets()
    }

}