package com.econocom.gigirestaurants.model.network.apis

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import com.econocom.gigirestaurants.model.network.apis.utils.Address


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
    fun doesMatchQuery(query: String): Boolean {
        val matchingCombinations = listOf(
            "",
            ""
        )
        return matchingCombinations.any {
            it.contains(query, ignoreCase = true)
        }
    }
}