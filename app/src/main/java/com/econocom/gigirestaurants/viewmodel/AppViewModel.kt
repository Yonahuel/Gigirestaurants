package com.econocom.gigirestaurants.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.econocom.gigirestaurants.database.entities.Restaurant
import com.econocom.gigirestaurants.model.network.DataDownloader
import com.econocom.gigirestaurants.model.network.apis.DetallesApi
import com.econocom.gigirestaurants.model.network.apis.RestaurantApi
import com.econocom.gigirestaurants.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    application: Application,
    private val dataDownloader: DataDownloader,
    private val repository: Repository
): AndroidViewModel(application) {
    private val tag = "AppViewModel"

    // Lista de restaurants cercanos
    private val _listaRestaurants = MutableStateFlow<List<RestaurantApi>>(emptyList())
    val listaRestaurants = _listaRestaurants.asStateFlow()

    // Texto ingresado por el usuario
    private val _textoBusqueda = MutableStateFlow("")
    val textoBusqueda = _textoBusqueda.asStateFlow()

    // Listado Favoritos
    private val _favoritos = MutableStateFlow<List<Restaurant>>(emptyList())
    val favoritos = _favoritos.asStateFlow()

    // Ubicación actual
    private val _ubicacion = MutableStateFlow("")
    private val ubicacion = _ubicacion.asStateFlow()

    private val _buscando = MutableStateFlow(false)
    val buscando = _buscando.asStateFlow()

    private val _detalles = MutableStateFlow(DetallesApi())
    val detalles = _detalles.asStateFlow()

    private val _resultadosBusqueda = MutableStateFlow<List<RestaurantApi>>(emptyList())
    val resultadosBusqueda = _resultadosBusqueda.asStateFlow()

    private val _restaurant = MutableStateFlow(Restaurant())
    val restaurant = _restaurant.asStateFlow()

    private val _ids = MutableStateFlow<List<Int>>(emptyList())
    val ids = _ids.asStateFlow()

    init {
        viewModelScope.launch {
            dataDownloader.downloadRestaurant().collect {
                _listaRestaurants.value = it
            }
        }
    }

    fun downloadDetalles(locationId: Int) {
        viewModelScope.launch {
            dataDownloader.downloadDetalles(locationId = locationId).collect {
                _detalles.value = it
            }
        }
    }

    fun searchQuery(query: String) {
        viewModelScope.launch {
            dataDownloader.searchQuery(query = query).collect {
                _resultadosBusqueda.value = it
            }
        }
    }

    fun getFavoritos() {
        viewModelScope.launch {
            repository.getFavoritos().collect {
                _favoritos.value = it
            }
        }
    }

    fun setTextoBusqueda(texto: String) { _textoBusqueda.value = texto }
    fun insertFavorito(favorito: Restaurant) { repository.insertFavorito(favorito, viewModelScope) }
    fun deleteFavorito(favorito: Restaurant) { repository.deleteFavorito(favorito, viewModelScope) }
    fun setRestaurant(restaurant: Restaurant) { _restaurant.value = restaurant}
}