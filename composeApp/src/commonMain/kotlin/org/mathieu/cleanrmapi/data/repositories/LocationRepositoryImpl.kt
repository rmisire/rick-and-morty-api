package org.mathieu.cleanrmapi.data.repositories

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.mathieu.cleanrmapi.data.local.DataStore
import org.mathieu.cleanrmapi.data.local.LocationDAO
import org.mathieu.cleanrmapi.data.local.objects.LocationObject
import org.mathieu.cleanrmapi.data.local.objects.toDBObject
import org.mathieu.cleanrmapi.data.local.objects.toModel
import org.mathieu.cleanrmapi.data.remote.LocationAPI
import org.mathieu.cleanrmapi.domain.location.models.Location
import org.mathieu.cleanrmapi.domain.location.models.LocationRepository

internal class LocationRepositoryImpl (
    private val dataStore: DataStore,
    private val locationApi: LocationAPI,
    private val locationDao: LocationDAO,
) : LocationRepository  {

    override suspend fun getLocation (id: Int): Location? {
        val locationLocal = GetLocationObjectIfExists(id)

        return locationLocal.toModel()
    }


    private object GetLocationObjectIfExists : KoinComponent {

        private val locationApi: LocationAPI by inject()
        private val locationLocal: LocationDAO by inject()

        suspend operator fun invoke(locationId: Int): LocationObject =
            tryToGetLocationLocally(locationId)
                .fetchRemotelyIfNotFound(locationId)
                .throwIfWeCannotFindIt()

        private suspend fun tryToGetLocationLocally(id: Int) = locationLocal.getLocation(id)

        private suspend fun LocationObject?.fetchRemotelyIfNotFound(id: Int): LocationObject? {
            return if (this != null) {
                this
            } else {
                locationApi.getLocation(id = id)
                    ?.toDBObject()
                    ?.also { obj ->
                        locationLocal.insert(obj)
                }
            }
        }


        private fun LocationObject?.throwIfWeCannotFindIt(): LocationObject {
            if (this == null) {
                throw Exception("Cannot find the location")
            }
            return this
        }
    }

}