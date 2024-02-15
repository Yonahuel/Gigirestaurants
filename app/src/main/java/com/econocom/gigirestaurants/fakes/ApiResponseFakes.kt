package com.econocom.gigirestaurants.fakes

import com.econocom.gigirestaurants.model.network.apis.DetallesApi
import com.econocom.gigirestaurants.model.network.apis.RestaurantApi
import com.econocom.gigirestaurants.model.network.apis.utils.Address

val listaFakes = listOf(
    RestaurantApi(
        locationId = 1,
        name = "Restaurant 1",
        rating = "4",
        distance = "2 km",
        addressObj = Address(
            street1 = "calle 1",
            street2 = "calle 2",
            city = "Olivos",
            state = "Buenos Aires",
            country = "Argentina",
            postalcode = "1636",
            addressString = "Calle 1 y calle 2, Olivos, Buenos Aires, Argentina",
            phone = "+54 9 1155658963"
        )
    ),
    RestaurantApi(
        locationId = 2,
        name = "Restaurant 2",
        rating = "5",
        distance = "3 km",
        addressObj = Address(
            street1 = "calle 2",
            street2 = "calle 3",
            city = "Olivos",
            state = "Buenos Aires",
            country = "Argentina",
            postalcode = "1636",
            addressString = "Calle 2 y calle 3, Olivos, Buenos Aires, Argentina",
            phone = "+54 9 11489961215"
        )
    ),
    RestaurantApi(
        locationId = 3,
        name = "Restaurant 3",
        rating = "7",
        distance = "6 km",
        addressObj = Address(
            street1 = "calle 3",
            street2 = "calle 4",
            city = "Olivos",
            state = "Buenos Aires",
            country = "Argentina",
            postalcode = "1636",
            addressString = "Calle 3 y calle 4, Olivos, Buenos Aires, Argentina",
            phone = "+54 9 1144588962"
        )
    ),
    RestaurantApi(
        locationId = 4,
        name = "Restaurant 4",
        rating = "10",
        distance = "1 km",
        addressObj = Address(
            street1 = "calle 4",
            street2 = "calle 5",
            city = "Olivos",
            state = "Buenos Aires",
            country = "Argentina",
            postalcode = "1636",
            addressString = "Calle 4 y calle 5, Olivos, Buenos Aires, Argentina",
            phone = "+54 9 1199878533"
        )
    )
)

val detallesFake = DetallesApi(
    locationId = 1,
    name = "Restaurant 1",
    description = "Restaurant de comida italiana",
    webUrl = "https://www.restaurant.com",
    address = Address(
        street1 = "calle 1",
        street2 = "calle 2",
        city = "Olivos",
        state = "Buenos Aires",
        country = "Argentina",
        postalcode = "1636",
        addressString = "Calle 1 y calle 2, Olivos, Buenos Aires, Argentina",
        phone = "+54 9 1155658963"
    ),
    email = "restaurant@restaurant.com",
    phone = "+54 9 1155658963",
    rating = 4
)