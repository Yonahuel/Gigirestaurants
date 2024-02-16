package com.econocom.gigirestaurants.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Restaurant {
    @PrimaryKey
    var locationId: Int? = null
    var name: String? = null
    var distance: String? = null
    var rating: String? = null
    var bearing: String? = null
}