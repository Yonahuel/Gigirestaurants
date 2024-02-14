package com.econocom.gigirestaurants.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.econocom.gigirestaurants.viewmodel.AppViewModel
import com.econocom.gigirestaurants.model.network.apis.RestaurantApi
import com.econocom.gigirestaurants.ui.navigation.Screen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: AppViewModel,
    navController: NavController
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Gigi Restaurants") },
                colors = TopAppBarDefaults.topAppBarColors(

                ),
                modifier = Modifier.fillMaxWidth(),
            )
        }
    ) {
        TabScreen(viewModel, navController)
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaRestaurants(
    viewModel: AppViewModel,
    navController: NavController
) {
    val lista by viewModel.listaRestaurants.collectAsState()
    val query by viewModel.textoBusqueda.collectAsState()
    val buscando by viewModel.buscando.collectAsState()

    Column {
        SearchBar(
            modifier = Modifier.fillMaxWidth(),
            query = query,
            onQueryChange = {},
            onSearch = {},
            active = buscando,
            onActiveChange = {}
        ) {

        }
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(lista) {restaurant ->
                RestaurantItem(restaurant = restaurant, viewModel = viewModel, navController = navController)
            }
        }
    }
}

@Composable
fun TabScreen(
    viewModel: AppViewModel,
    navController: NavController
    ) {
    var tabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("Home", "Detalles")

    Column(modifier = Modifier.fillMaxWidth()) {
        TabRow(selectedTabIndex = tabIndex) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    text = { Text(text = title) },
                    selected = tabIndex == index,
                    onClick = { tabIndex = index }
                )
            }
        }
        when (tabIndex) {
            0 -> ListaRestaurants(viewModel, navController)
            1 -> FavoritosScreen(viewModel)
        }
    }
}

@Composable
@UiComposable
fun TabRow(
    selectedTabIndex: Int,
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    contentColor: Color = contentColorFor(backgroundColor = backgroundColor),
    indicator: @Composable @UiComposable (tabPositions: List<TabPosition>) -> Unit = @Composable { tabPositions ->
        TabRowDefaults.Indicator(
            Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex])
        )
    },
    divider: @Composable @UiComposable () -> Unit = @Composable {
        Divider()
    },
    tabs: @Composable @UiComposable () -> Unit
    ): Unit {}



@Composable
fun RestaurantItem(
    restaurant: RestaurantApi,
    viewModel: AppViewModel,
    navController: NavController
) {

    Card(
        modifier = Modifier
            .clickable {
                viewModel.downloadDetalles(restaurant.locationId!!)
                navController.navigate(Screen.Detalles.name)
            }
    ) {
        Row {
            AsyncImage(
                model = "url",
                contentDescription = null,
            )
            Column {
                Text(text = "Nombre")
                Text(text = "Tipo")
            }
        }
    }
}