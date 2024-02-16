package com.econocom.gigirestaurants.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.econocom.gigirestaurants.database.entities.Restaurant
import com.econocom.gigirestaurants.model.network.apis.RestaurantApi
import com.econocom.gigirestaurants.ui.navigation.Screen
import com.econocom.gigirestaurants.ui.theme.AppColors
import com.econocom.gigirestaurants.viewmodel.AppViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FavoritosScreen(
    viewModel: AppViewModel
) {
    val favoritos by viewModel.favoritos.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Favoritos") },
                colors = TopAppBarDefaults.topAppBarColors(
                    
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }
    ) {
        LazyColumn() {
            items(favoritos) { restaurant ->
                FavoritoItem(restaurant = restaurant, viewModel = viewModel)
            }
        }
    }
}

@Composable
fun FavoritoItem(
    restaurant: Restaurant,
    viewModel: AppViewModel,
) {
    Card(
        modifier = Modifier
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth()
            .clickable {
                viewModel.downloadDetalles(restaurant.locationId!!)
                viewModel.setRestaurant(restaurant)
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            Column {
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
        }
    }
}

@Composable
fun Item(
    viewModel: AppViewModel,
    restaurant: Restaurant
) {
    Row {
        Text(
            text = "Nombre"
        )
        IconButton(
            onClick = { viewModel.deleteFavorito(restaurant) }
        ) {
            Icon(
                imageVector = Icons.Outlined.Favorite,
                contentDescription = null,
            )
        }
    }
}