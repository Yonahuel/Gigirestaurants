package com.econocom.gigirestaurants

import android.Manifest
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.econocom.gigirestaurants.model.location.LocationService
import com.econocom.gigirestaurants.ui.navigation.Navigate
import com.econocom.gigirestaurants.ui.theme.GigirestaurantsTheme
import com.econocom.gigirestaurants.viewmodel.AppViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            ),
            0
        )
        Intent(applicationContext, LocationService::class.java).apply {
            action = LocationService.ACTION_START
        }

        val viewModel = ViewModelProvider(this)[AppViewModel::class.java]

        setContent {
            GigirestaurantsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainApp(viewModel)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Intent(applicationContext, LocationService::class.java).apply {
            action = LocationService.ACTION_STOP
        }
    }
}

@Composable
fun MainApp(viewModel: AppViewModel) {
    val navController = rememberNavController()

    GigirestaurantsTheme {
        Navigate(viewModel = viewModel, navController = navController)
    }
}