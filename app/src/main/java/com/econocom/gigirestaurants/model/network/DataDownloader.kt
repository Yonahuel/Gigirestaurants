package com.econocom.gigirestaurants.model.network

import android.util.Log
import com.econocom.gigirestaurants.model.network.apis.RestaurantApi
import io.ktor.client.HttpClient
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.client.statement.bodyAsText
import io.ktor.http.isSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.serialization.json.Json

class DataDownloader {
    private val tag = "DataDownloader - Error en la solicitud: "
    private val url = "https://api.content.tripadvisor.com/api/v1/location/nearby_search?"
    private val idioma = "es_MX"
    private val apiKey = ""

    suspend fun downloadRestaurant(
        latLong: String,
        httpClient: HttpClient = ktorHttpClient,
        baseUrl: String = url
    ): Flow<List<RestaurantApi>> {
        val data = MutableStateFlow<List<RestaurantApi>>(emptyList())
        try {
            val httpResponse = httpClient.get { url(
                "${baseUrl}latLong=${latLong}&key=${apiKey}&category=restaurants&language=${idioma}")
            }
            if (httpResponse.status.isSuccess()) {
                val responseContent = httpResponse.bodyAsText()
                if (responseContent.isNotEmpty()) {
                    val apiResponse = Json.decodeFromString<List<RestaurantApi>>(responseContent)
                    data.value = apiResponse
                } else {
                    Log.d(tag, "La respuesta está vacía")
                }
            } else {
                Log.d(tag, "Respuesta no exitosa: ${httpResponse.status}")
            }
        } catch (e: ClientRequestException) {
            Log.d(tag, "Error en la solicitud cliente: ${e.message}")
        } catch (e: ServerResponseException) {
            Log.d(tag, "Error en la respuesta del servidor: ${e.message}")
        } catch (e: Exception) {
            Log.d(tag, "Error desconocido: ${e.message}")
        }
        return data
    }
}