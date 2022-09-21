package com.example.demospacex.api

import com.example.demospacex.helpher.Constants
import com.example.demospacex.models.RocketsItem
import com.example.demospacex.models.RocketsResponse
import com.example.demospacex.models.UpcomingRocketsItem
import com.example.demospacex.models.UpcomingRocketsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET(Constants.GET_ROCKETS_END_POINT)
    suspend fun getRockets(): Response<RocketsResponse>

    @GET(Constants.GET_UPCOMING_ROCKETS_END_POINT)
    suspend fun getUpcomingRockets(): Response<UpcomingRocketsResponse>

    @GET(Constants.GET__ONE_ROCKET_END_POINT)
    suspend fun getOneRocket(@Path("rocket_id") rocketId: String): Response<RocketsItem>

    @GET(Constants.GET_ONE_UPCOMING_ROCKETS_END_POINT)
    suspend fun getOneUpcomingRocket(@Path("flight_number") flightNumber: Int): Response<UpcomingRocketsItem>


}