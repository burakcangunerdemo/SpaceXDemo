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
class RocketsViewModel
@Inject
constructor(private val repository: DemoSpaceXRepository, private val favorite: FavoriteRocketRepository): ViewModel(){
    private val _response = MutableLiveData<List<RocketsItem>>()
    val responseRockets:LiveData<List<RocketsItem>>
        get() = _response

    init {
        getAllRockets()
    }

    private fun getAllRockets() = viewModelScope.launch {
        repository.getRockets().let { response ->
            if (response.isSuccessful){
                var tempList = mutableListOf<RocketsItem>()
                response.body()?.let {

                    for (item in it){
                        var tempItem = item
                        if (favorite.getOneFavoriteRocket(item.rocket_id) > 0){
                            tempItem.isFavorite = true
                        }
                        tempList.add(tempItem)
                    }
                }
                _response.postValue(tempList)
            }else{
                Log.e("error: ", "getRockets Error: ${response.code()}")
            }
        }
    }

    fun refresh(){
        getAllRockets()
    }

}