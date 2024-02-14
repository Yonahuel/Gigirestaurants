package com.econocom.gigirestaurants.model.network.apis.utils

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Address(
    val street1: String,
    val street2: String,
    val city: String,
    val state: String,
    val country: String,
    val postalcode: String,
    @SerialName("address_string")
    val addressString: String,
    val phone: String,
    val latitude: Double,
    val longitude: Double
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