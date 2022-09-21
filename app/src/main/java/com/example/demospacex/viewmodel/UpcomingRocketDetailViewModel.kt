package com.example.demospacex.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.demospacex.models.RocketsItem
import com.example.demospacex.models.UpcomingRocketsItem
import com.example.demospacex.repository.DemoSpaceXRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpcomingRocketDetailViewModel
@Inject
constructor(private val savedStateHandle: SavedStateHandle): ViewModel(){
    private val _response = MutableLiveData<UpcomingRocketsItem>()
    val responseOneUpcomingRocket:LiveData<UpcomingRocketsItem>
        get() = _response


    init {
        savedStateHandle.get<UpcomingRocketsItem>("rocket")?.let {
            changedRocketItem(it)
        }
    }

    private fun changedRocketItem(rocket: UpcomingRocketsItem) = viewModelScope.launch {
        _response.postValue(rocket)
    }

}