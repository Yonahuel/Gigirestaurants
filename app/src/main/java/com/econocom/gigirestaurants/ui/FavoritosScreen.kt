package com.econocom.gigirestaurants.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.econocom.gigirestaurants.database.entities.Restaurant
import com.econocom.gigirestaurants.ui.navigation.Screen
import com.econocom.gigirestaurants.ui.theme.AppColors
import com.econocom.gigirestaurants.viewmodel.AppViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FavoritosScreen(
    viewModel: AppViewModel,
    navController: NavController
) {
    val favoritos by viewModel.favoritos.collectAsState()

    Surface(
        color = AppColors.Background,
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn() {
            items(favoritos) { restaurant ->
                FavoritoItem(restaurant = restaurant, viewModel = viewModel, navController = navController)
            }
        }
    }
}

@Composable
fun FavoritoItem(
    restaurant: Restaurant,
    viewModel: AppViewModel,
    navController: NavController
) {
    Card(
        modifier = Modifier
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth()
            .clickable {
                viewModel.downloadDetalles(restaurant.locationId!!)
                viewModel.setRestaurant(restaurant)
                viewModel.getIds()
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.clickable {
                    viewModel.setRestaurant(restaurant)
                    navController.navigate(Screen.Detalles.name)
                }
            ) {
                Text(
                    text = restaurant.name ?: "null",
                    style = MaterialTheme.typography.bodyLarge,
                    color = AppColors.OnSecondary
                )
                Text(
                    text = "Distancia: ${restaurant.distance ?: "null"}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = AppColors.OnSecondary
                )
            }
            IconButton(
                onClick = { viewModel.deleteFavorito(restaurant) },
            ) {
                Icon(
                    imageVector = Icons.Outlined.Favorite,
                    contentDescription = "Eliminar favorito",
                )
            }
        }
    }
}