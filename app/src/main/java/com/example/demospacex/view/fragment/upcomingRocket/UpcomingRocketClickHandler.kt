package com.example.demospacex.view.fragment.upcomingRocket

import com.example.demospacex.models.UpcomingRocketsItem

interface UpcomingRocketClickHandler {
    fun clickedItem(rocket: UpcomingRocketsItem)
}