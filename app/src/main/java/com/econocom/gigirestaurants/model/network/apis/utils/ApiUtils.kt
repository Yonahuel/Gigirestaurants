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
data class Ancestors(
    val abbrv: String,
    val level: String,
    val name: String,
    @SerialName("location_id")
    val locationId: Int
)

@Serializable
data class RankingData(
    @SerialName("geo_location_id")
    val geoLocationId: Int,
    @SerialName("ranking_string")
    val rankingString: String,
    @SerialName("geo_location_name")
    val geoLocationName: String,
    @SerialName("ranking_out_of")
    val rankingOutOf: Int,
    val ranking: Int
)

@Serializable
data class Error(
    val message: String,
    val type: String,
    val code: Int
)