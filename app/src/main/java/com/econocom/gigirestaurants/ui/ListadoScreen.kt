package com.econocom.gigirestaurants.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.econocom.gigirestaurants.model.network.apis.RestaurantApi
import com.econocom.gigirestaurants.ui.navigation.Screen
import com.econocom.gigirestaurants.ui.theme.AppColors
import com.econocom.gigirestaurants.ui.utils.BarraBusqueda
import com.econocom.gigirestaurants.viewmodel.AppViewModel
import java.text.DecimalFormat

@Composable
fun ListaRestaurants(
    viewModel: AppViewModel,
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.Background)
            .padding(top = 8.dp)
    ) {
        BarraBusqueda(viewModel = viewModel, navController = navController)
    }
}

@Composable
fun RestaurantItem(
    restaurant: RestaurantApi,
    viewModel: AppViewModel,
    navController: NavController
) {
    val distancia = restaurant.distance?.toDouble()
    val format = DecimalFormat("0.00")

    Card(
        modifier = Modifier
            .padding(top = 16.dp)
            .fillMaxWidth()
            .clickable {
                viewModel.downloadDetalles(restaurant.locationId!!)
                viewModel.setRestaurant(restaurant.asRestaurant())
                viewModel.getIds()
                navController.navigate(Screen.Detalles.name)
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            // Imagen de muestra, reemplazar con url de la imagen provista por la api
            AsyncImage(
                model = "https://images.unsplash.com/photo-1514933651103-005eec06c04b?q=80&w=2574&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = restaurant.name ?: "null",
                    style = MaterialTheme.typography.bodyLarge,
                    color = AppColors.OnSecondary
                )
                if (distancia != null) {
                    Text(
                        text = "Distancia: ${format.format(distancia)} mi",
                        style = MaterialTheme.typography.bodyMedium,
                        color = AppColors.OnSecondary
                    )
                }
            }
        }
    }
}