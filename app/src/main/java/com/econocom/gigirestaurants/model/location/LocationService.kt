package com.econocom.gigirestaurants.model.location

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEach

class LocationService: Service() {
    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private lateinit var locationClient: LocationClient

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        locationClient = DefaultLocationClient(
            applicationContext,
            LocationServices.getFusedLocationProviderClient(applicationContext)
        )
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when(intent?.action) {
            ACTION_START -> start()
            ACTION_STOP -> stop()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun start() {
        locationClient
            .getLocationUpdates(10000L)
            .catch { e -> e.printStackTrace() }
            .onEach { location ->
                _lat.value = location.latitude
                _long.value = location.longitude
            }
    }
    private fun stop() {
        stopSelf()
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceScope.cancel()
    }
    companion object {
        const val ACTION_START = "ACTION_START"
        const val ACTION_STOP = "ACTION_STOP"
        private val _lat = MutableStateFlow(0.0)
        val lat get() = _lat.asStateFlow()
        private val _long = MutableStateFlow(0.0)
        val long get() = _long.asStateFlow()
    }

}