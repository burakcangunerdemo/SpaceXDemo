package com.example.demospacex.models

data class Payload(
    val cap_serial: String,
    val cargo_manifest: Any,
    val customers: List<String>,
    val flight_time_sec: Any,
    val manufacturer: String,
    val mass_returned_kg: Any,
    val mass_returned_lbs: Any,
    val nationality: String,
    val norad_id: List<Any>,
    val orbit: String,
    val orbit_params: OrbitParams,
    val payload_id: String,
    val payload_mass_kg: Any,
    val payload_mass_lbs: Any,
    val payload_type: String,
    val reused: Boolean
)