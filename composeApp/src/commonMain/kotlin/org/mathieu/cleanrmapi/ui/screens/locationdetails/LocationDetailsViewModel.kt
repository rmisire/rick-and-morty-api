package org.mathieu.cleanrmapi.ui.screens.locationdetails

import org.koin.core.component.inject
import org.mathieu.cleanrmapi.domain.character.models.Character
import org.mathieu.cleanrmapi.domain.location.models.LocationRepository
import org.mathieu.cleanrmapi.ui.core.ViewModel

class LocationDetailsViewModel :
    ViewModel<LocationDetailsState>(LocationDetailsState.Loading) {

    private val locationRepository: LocationRepository by inject()

    fun init(locationId: Int) {

        println("DEBUG - Location called with id = $locationId")
        fetchData(
            source = { locationRepository.getLocation(id = locationId) }
        ) {

            onSuccess { location ->
                if (location != null) {
                    updateState {
                        LocationDetailsState.Loaded(
                            name = location.name,
                            type = location.type,
                            dimension = location.dimension,
                            residents = location.residents
                        )
                    }
                }
            }

            onFailure {
                updateState {
                    LocationDetailsState.Error(message = it.message ?: it.toString())
                }
            }

        }
    }
}

sealed interface LocationDetailsState {
    object Loading : LocationDetailsState

    data class Error(val message: String) : LocationDetailsState

    data class Loaded(
        val name: String,
        val type: String,
        val dimension: String,
        val residents: List<Character>
    ) : LocationDetailsState
}
