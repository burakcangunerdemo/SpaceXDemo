package com.example.demospacex.repository

import com.example.demospacex.api.ApiService
import javax.inject.Inject

class DemoSpaceXRepository
@Inject
constructor(private val apiService: ApiService){
    suspend fun getRockets() = apiService.getRockets()
    suspend fun getUpcomingRockets() = apiService.getUpcomingRockets()
    suspend fun getOneRocket(rocketId: String) = apiService.getOneRocket(rocketId)
    suspend fun getOneUpcomingRocket(flightNumber: Int) = apiService.getOneUpcomingRocket(flightNumber)

}