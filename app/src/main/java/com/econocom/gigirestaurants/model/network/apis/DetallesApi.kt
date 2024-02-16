package com.econocom.gigirestaurants.model.network.apis

import com.econocom.gigirestaurants.model.network.apis.utils.Address
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DetallesApi(
    @SerialName("location_id")
    val locationId: Int? = null,
    val name: String? = null,
    val description: String? = null,
    @SerialName("web_url")
    val webUrl: String? = null,
    @SerialName("address_obj")
    val address: Address? = null,
    val email: String? = null,
    val phone: String? = null,
    val rating: Int? = null
)