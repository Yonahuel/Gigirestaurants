package com.econocom.gigirestaurants.model.network.apis.utils

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Address(
    val street1: String? = null,
    val street2: String? = null,
    val city: String? = null,
    val state: String? = null,
    val country: String? = null,
    val postalcode: String? = null,
    @SerialName("address_string")
    val addressString: String? = null,
    val phone: String? = null,
    val latitude: Double? = null,
    val longitude: Double? = null
)

@Serializable
data class Error(
    val message: String,
    val type: String,
    val code: Int
)