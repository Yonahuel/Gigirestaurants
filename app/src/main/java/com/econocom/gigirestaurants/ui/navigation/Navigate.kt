package com.econocom.gigirestaurants.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.econocom.gigirestaurants.viewmodel.AppViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.econocom.gigirestaurants.ui.DetallesScreen
import com.econocom.gigirestaurants.ui.HomeScreen

@Composable
fun Navigate(
    viewModel: AppViewModel,
    navController: NavHostController,
) {
    NavHost(navController = navController, startDestination = Screen.Home.name) {
        composable(Screen.Home.name) {
            HomeScreen(viewModel = viewModel, navController = navController)
        }
        composable(Screen.Detalles.name) {
            DetallesScreen(viewModel = viewModel, navController = navController)
        }
    }
}