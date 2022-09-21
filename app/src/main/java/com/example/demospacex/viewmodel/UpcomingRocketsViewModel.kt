package com.example.demospacex.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demospacex.models.UpcomingRocketsItem
import com.example.demospacex.repository.DemoSpaceXRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpcomingRocketsViewModel
@Inject
constructor(private val repository: DemoSpaceXRepository): ViewModel(){
    private val _response = MutableLiveData<List<UpcomingRocketsItem>>()
    val responseUpcomingRockets:LiveData<List<UpcomingRocketsItem>>
        get() = _response

    init {
        getAllUpcomingRockets()
    }

    private fun getAllUpcomingRockets() = viewModelScope.launch {
        repository.getUpcomingRockets().let { response ->
            if (response.isSuccessful){
                _response.postValue(response.body())
            }else{
                Log.e("error: ", "getRockets Error: ${response.code()}")
            }
        }
    }

}