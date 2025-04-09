package org.mathieu.cleanrmapi.data.local.objects

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.mathieu.cleanrmapi.data.local.RMDatabase
import org.mathieu.cleanrmapi.data.remote.responses.LocationResponse
import org.mathieu.cleanrmapi.data.validators.annotations.MustBeCommaSeparatedIds
import org.mathieu.cleanrmapi.domain.character.models.Character
import org.mathieu.cleanrmapi.domain.episode.models.Episode
import org.mathieu.cleanrmapi.domain.location.models.Location
import org.mathieu.cleanrmapi.domain.location.models.LocationPreview


/**
 * Represents a location entity stored in the SQLite database. This object provides fields
 * necessary to represent all the attributes of a location from the data source.
 * The object is specifically tailored for SQLite storage using Realm.
 *
 * @property id Unique identifier of the location.
 * @property name Name of the location.
 * @property type The type or category of the location.
 */
@Entity(tableName = RMDatabase.LOCATION_TABLE)
class LocationObject(
    @PrimaryKey
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    @MustBeCommaSeparatedIds
    val residentsIds: String,
)

internal fun LocationObject.toModel() = Location(
    id = id,
    name = name,
    type = type,
    dimension = dimension,
    residents = emptyList()
)

internal fun LocationResponse.toDBObject() = LocationObject(
    id = id,
    name = name,
    type = type,
    dimension = dimension,
    residentsIds = residents.mapNotNull { it.split("/").lastOrNull() }.joinToString(",")

)
