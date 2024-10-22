package rebase.eu.slhs.controller

import rebase.eu.slhs.service.LocationHistoryService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import rebase.eu.slhs.controller.dto.Location
import rebase.eu.slhs.controller.dto.LocationList
import java.net.URI

@RestController
@RequestMapping("/location")
class LocationController(val locationHistoryService: LocationHistoryService) {
    @PostMapping("/{riderId}/now")
    fun postLocation(
            @PathVariable riderId: String,
            @RequestBody location: Location
    ): ResponseEntity<Void> {
        locationHistoryService.addLocation(riderId, location)
        return ResponseEntity.created(URI("location/$riderId")).build()
    }

    @GetMapping("/{riderId}")
    fun getLocationHistory(
            @PathVariable riderId: String,
            @RequestParam(required = false) max: Int?
    ): ResponseEntity<LocationList> {
        return runCatching {
            ResponseEntity.ok(locationHistoryService.getLocationHistory(riderId, max))
        }.getOrElse {
            ResponseEntity.notFound().build()
        }
    }
}
