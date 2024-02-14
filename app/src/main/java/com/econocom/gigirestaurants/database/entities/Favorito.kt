package com.econocom.gigirestaurants.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Favorito {
    @PrimaryKey
    var locationId: Int = 0
}