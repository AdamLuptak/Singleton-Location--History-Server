package rebase.eu.slhs.controller.dto

import com.fasterxml.jackson.annotation.JsonProperty

import rebase.eu.slhs.entity.Location as LocationEntity

data class Location(
        val lat: Double,
        val long: Double
)
fun Location.toEntity(): LocationEntity {
    return LocationEntity(
        lat = this.lat,
        long = this.long
    )
}
data class LocationList(
        @JsonProperty("rider_id") val riderId: String,
        val history: List<Location>
)