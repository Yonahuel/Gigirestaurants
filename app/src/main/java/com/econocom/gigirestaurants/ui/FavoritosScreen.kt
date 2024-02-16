package com.econocom.gigirestaurants.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.econocom.gigirestaurants.database.entities.Restaurant
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
                Item(viewModel = viewModel, restaurant = restaurant)
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