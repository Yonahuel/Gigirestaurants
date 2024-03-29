package com.econocom.gigirestaurants.model.network.apis

import com.econocom.gigirestaurants.database.entities.Restaurant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import com.econocom.gigirestaurants.model.network.apis.utils.Address
import com.econocom.gigirestaurants.model.network.apis.utils.Error


@Serializable
data class RestaurantApi(
    @SerialName("location_id")
    val locationId: Int? = null,
    val name: String? = null,
    val distance: String? = null,
    val rating: String? = null,
    val bearing: String? = null,
    @SerialName("address_obj")
    val addressObj: Address? = null,
) {
    fun asRestaurant(): Restaurant {
        val restaurant = Restaurant()
        restaurant.locationId = this.locationId
        restaurant.name = this.name
        restaurant.bearing = this.bearing
        restaurant.rating = this.rating
        restaurant.distance = this.distance

        return restaurant
    }
}

@Serializable
data class RestaurantResponse(
    val data: List<RestaurantApi>? = null,
    val error: Error? = null
)