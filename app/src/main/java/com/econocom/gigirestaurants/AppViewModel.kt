package com.econocom.gigirestaurants

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.econocom.gigirestaurants.model.network.DataDownloader
import com.econocom.gigirestaurants.model.network.apis.RestaurantApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    application: Application,
    private val dataDownloader: DataDownloader
): AndroidViewModel(application) {
    private val tag = "AppViewModel"

    // Lista de restaurants cercanos
    private val _listaRestaurants = MutableStateFlow<List<RestaurantApi>>(emptyList())
    val listaRestaurants = _listaRestaurants.asStateFlow()

    // Restaurant seleccionado
    private val _restaurant = MutableStateFlow(RestaurantApi())
    val restaurant = _restaurant.asStateFlow()

    // Texto ingresado por el usuario
    private val _textoBusqueda = MutableStateFlow("")
    val textoBusqueda = _textoBusqueda.asStateFlow()

    // Listado Favoritos
    private val _favoritos = MutableStateFlow<List<RestaurantApi>>(emptyList())
    val favoritos = _favoritos.asStateFlow()

    // Ubicación actual
    private val _ubicacion = MutableStateFlow("")
    private val ubicacion = _ubicacion.asStateFlow()

    init {
        viewModelScope.launch {
            dataDownloader.downloadRestaurant(latLong = ubicacion.value).collect {
                _listaRestaurants.value = it
            }
        }
    }

    fun setRestaurant(restaurant: RestaurantApi) { _restaurant.value = restaurant }
    fun setTextoBusqueda(texto: String) { _textoBusqueda.value = texto }
}