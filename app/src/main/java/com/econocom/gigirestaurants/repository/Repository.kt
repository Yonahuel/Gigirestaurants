package com.econocom.gigirestaurants.repository

import com.econocom.gigirestaurants.database.daos.FavoritosDao
import com.econocom.gigirestaurants.database.entities.Favorito
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class Repository @Inject constructor(
    private val dao: FavoritosDao
) {
    fun insertFavorito(favorito: Favorito, scope: CoroutineScope) {
        scope.launch(Dispatchers.IO) {
            dao.insert(favorito)
        }
    }

    fun deleteFavorito(favorito: Favorito, scope: CoroutineScope) {
        scope.launch(Dispatchers.IO) {
            dao.delete(favorito)
        }
    }

    /*fun getFavoritos(scope: CoroutineScope): Flow<List<Favorito>> {

    }*/
}