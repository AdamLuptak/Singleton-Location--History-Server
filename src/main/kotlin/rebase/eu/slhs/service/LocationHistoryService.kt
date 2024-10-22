package rebase.eu.slhs.service

import org.springframework.stereotype.Service
import rebase.eu.slhs.controller.dto.Location
import rebase.eu.slhs.controller.dto.LocationList
import rebase.eu.slhs.controller.dto.toEntity
import rebase.eu.slhs.entity.Location as LocationEntity


@Service
class LocationHistoryService {
    private val locationHistory: MutableMap<String, MutableList<LocationEntity>> = mutableMapOf()
    fun addLocation(riderId: String, location: Location) {
        val riderHistory = locationHistory.getOrPut(riderId) { mutableListOf() }
        riderHistory.add(0, location.toEntity()) // Add to the beginning to ensure the most recent is first
    }

    fun getLocationHistory(riderId: String, max: Int?): LocationList {
        val riderHistory = locationHistory[riderId]
                ?: throw NoSuchElementException("No location history found for riderId: $riderId")
        return if (max != null && max < riderHistory.size) {
            riderHistory.take(max).map { Location(it.lat, it.long) }.let { LocationList(riderId, it) }
        } else {
            riderHistory.map { Location(it.lat, it.long) }.let { LocationList(riderId, it) }
        }
    }
}
