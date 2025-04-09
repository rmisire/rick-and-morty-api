package org.mathieu.cleanrmapi.domain.location.models

interface LocationRepository {

    /**
     * Fetches the details of a specific location based on the provided ID.
     *
     * @param id The unique identifier of the location to be fetched.
     * @return Details of the specified location.
     */
    suspend fun getLocation(id: Int): Location?
}