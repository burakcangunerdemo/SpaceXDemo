package com.example.demospacex.helpher

object Constants {

    const val BASE_URL: String =  "https://api.spacexdata.com/v3/"
    const val GET_ROCKETS_END_POINT: String = "rockets"
    const val GET__ONE_ROCKET_END_POINT: String = "rockets/{rocket_id}"
    const val GET_UPCOMING_ROCKETS_END_POINT: String = "launches/upcoming"
    const val GET_ONE_UPCOMING_ROCKETS_END_POINT: String = "launches/{flight_number}"

}