package com.econocom.gigirestaurants.model.network.apis

import com.econocom.gigirestaurants.model.network.apis.utils.Address
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Detalles(
    @SerialName("location_id")
    val locationId: Int,
    val name: String,
    val description: String,
    @SerialName("web_url")
    val webUrl: String,
    @SerialName("address_obj")
    val address: Address
)
