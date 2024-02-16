package com.econocom.gigirestaurants.repository

import com.econocom.gigirestaurants.database.daos.RestaurantDao
import com.econocom.gigirestaurants.database.entities.Restaurant
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class Repository @Inject constructor(
    private val dao: RestaurantDao
) {
    fun insertFavorito(favorito: Restaurant, scope: CoroutineScope) {
        scope.launch(Dispatchers.IO) {
            dao.insert(favorito)
        }
    }

    fun deleteFavorito(favorito: Restaurant, scope: CoroutineScope) {
        scope.launch(Dispatchers.IO) {
            dao.delete(favorito)
        }
    }

    fun getFavoritos(): Flow<List<Restaurant>> {
        return dao.getAllFlow()
    }

    fun getIdsFlow(): Flow<List<Int>> {
        return dao.getIdsFlow()
    }
}