package com.example.demospacex.models

import java.io.Serializable

data class UpcomingRocketsItem(
    val crew: Any,
    val details: String,
    val flight_number: Int,
    val is_tentative: Boolean,
    val last_date_update: String,
    val last_ll_launch_date: Any,
    val last_ll_update: Any,
    val last_wiki_launch_date: String,
    val last_wiki_revision: String,
    val last_wiki_update: String,
    val launch_date_local: String,
    val launch_date_source: String,
    val launch_date_unix: Int,
    val launch_date_utc: String,
    val launch_site: LaunchSite,
    val launch_success: Any,
    val launch_window: Any,
    val launch_year: String,
    val links: Links,
    val mission_id: List<String>,
    val mission_name: String,
    val rocket: Rocket,
    val ships: List<Any>,
    val static_fire_date_unix: Any,
    val static_fire_date_utc: Any,
    val tbd: Boolean,
    val telemetry: Telemetry,
    val tentative_max_precision: String,
    val timeline: Any,
    val upcoming: Boolean
) : Serializable