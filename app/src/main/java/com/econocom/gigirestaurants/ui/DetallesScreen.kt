package com.econocom.gigirestaurants.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import com.econocom.gigirestaurants.fakes.detallesFake
import com.econocom.gigirestaurants.model.network.apis.DetallesApi
import com.econocom.gigirestaurants.ui.theme.AppColors
import com.econocom.gigirestaurants.viewmodel.AppViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetallesScreen(
    viewModel: AppViewModel,
    navController: NavController
) {
    val detalles by viewModel.detalles.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = detalles.name ?: "") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = AppColors.Primary,
                    titleContentColor = AppColors.OnPrimary
                ),
                modifier = Modifier.fillMaxWidth(),
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null,
                            tint = AppColors.OnPrimary
                        )
                    }
                }
            )
        },
        content = { DetallesCard(detalles, viewModel) }
    )
}

@Composable
fun DetallesCard(
    detalles: DetallesApi,
    viewModel: AppViewModel,
) {
    val context = LocalContext.current
    val restaurant by viewModel.restaurant.collectAsState()
    val ids by viewModel.ids.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 76.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(AppColors.Surface)
        ) {
            // Imagen de muestra, reemplazar url con dirección de la imagen provista por la api
            SubcomposeAsyncImage(
                model = "https://images.unsplash.com/photo-1514933651103-005eec06c04b?q=80&w=2574&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                contentDescription = null,
                contentScale = ContentScale.Crop,
                loading = {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            )
        }
        Text(
            text = detalles.description ?: "Descripción no disponible",
            style = MaterialTheme.typography.headlineLarge .copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            ),
            textAlign = TextAlign.Center,
            color = AppColors.OnSurface
        )
        ContactInfoRow(
            icon = Icons.Default.Phone,
            text = detalles.phone ?: "N/A",
            onClick = { llamar(context, detalles.phone!!) }
        )
        ContactInfoRow(
            icon = Icons.Filled.Email,
            text = detalles.email ?: "N/A",
            onClick = { enviarEmail(context, detalles.email!!) }
        )
        Row {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = null,
                modifier = Modifier.size(30.dp)
            )
            Text(
                text = "Rating: ${detalles.rating ?: "N/A"}",
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                color = AppColors.OnSurface
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        if (restaurant.locationId in ids) {
            Button(
                onClick = { viewModel.deleteFavorito(restaurant) },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Quitar de favoritos")
            }
        } else {
            Button(
                onClick = { viewModel.insertFavorito(restaurant) },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Agregar a favoritos")
            }
        }
    }
}

@Composable
private fun ContactInfoRow(
    icon: ImageVector,
    text: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.wrapContentSize()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .wrapContentWidth()
                .clickable(onClick = onClick)
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = AppColors.Primary,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 16.sp,
                color = AppColors.Primary
            )
        }
    }
}

fun llamar(context: Context, phoneNumber: String) {
    val intent = Intent(Intent.ACTION_DIAL).apply {
        data = Uri.parse("tel:$phoneNumber")
    }
    context.startActivity(intent)
}

fun enviarEmail(context: Context, emailAddress: String) {
    val intent = Intent(Intent.ACTION_SENDTO).apply {
        data = Uri.parse("mailto:")
        putExtra(Intent.EXTRA_EMAIL, arrayOf(emailAddress))
    }
    context.startActivity(intent)
}