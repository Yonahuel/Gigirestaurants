package com.econocom.gigirestaurants.ui

import android.annotation.SuppressLint
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.econocom.gigirestaurants.model.LocationService
import com.econocom.gigirestaurants.model.network.apis.RestaurantApi
import com.econocom.gigirestaurants.ui.navigation.Screen
import com.econocom.gigirestaurants.ui.theme.Purple80
import com.econocom.gigirestaurants.viewmodel.AppViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: AppViewModel,
    navController: NavController
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val relaunch by remember { mutableStateOf(false) }
    val permissionRequest = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { permissions ->
            if (!permissions.values.all { it }) {
            } else {
                relaunch != relaunch
            }
        }
    )

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Gigi Restaurants") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Purple80
                ),
                modifier = Modifier.fillMaxWidth(),
            )
        },
        content = { Tabs(viewModel = viewModel, navController = navController)}
    )

    LaunchedEffect(key1 = relaunch) {
        try {
            //val location = LocationService().getCurrentLocation(context)
        } catch (e: LocationService.LocationServiceException) {
            when (e) {
                is LocationService.LocationServiceException.LocationDisabledException -> { }
                is LocationService.LocationServiceException.MissingPermissionException -> {
                    permissionRequest.launch(
                        arrayOf(
                            android.Manifest.permission.ACCESS_FINE_LOCATION,
                            android.Manifest.permission.ACCESS_COARSE_LOCATION
                        )
                    )
                }
                is LocationService.LocationServiceException.NoNetworkEnabledException -> { }
                is LocationService.LocationServiceException.UnknownException -> { }
            }
        }
    }
}