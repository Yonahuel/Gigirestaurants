package com.econocom.gigirestaurants.ui.utils

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.econocom.gigirestaurants.ui.RestaurantItem
import com.econocom.gigirestaurants.ui.theme.AppColors
import com.econocom.gigirestaurants.viewmodel.AppViewModel

@Composable
fun BarraBusqueda(
    modifier: Modifier = Modifier,
    viewModel: AppViewModel,
    navController: NavController
) {
    val searchText by viewModel.searchText.collectAsState()
    var isSearching by remember { mutableStateOf(false) }
    val resultados by viewModel.resultadosBusqueda.collectAsState()
    val restaurants by viewModel.listaRestaurants.collectAsState()
    val keyboardController = LocalSoftwareKeyboardController.current
    val textFieldFocusManager = LocalView.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextField(
            value = searchText,
            onValueChange = viewModel::onSearchTextChange,
            modifier = modifier.fillMaxWidth(),
            placeholder = { Text(text = "Buscar") },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = {
                    isSearching = !isSearching
                    viewModel.searchQuery(searchText)
                    textFieldFocusManager.clearFocus()
                    keyboardController?.hide()
                }
            ),
            trailingIcon = {
                if (!isSearching) {
                    IconButton(onClick = {
                        isSearching = !isSearching
                        viewModel.searchQuery(searchText)
                        textFieldFocusManager.clearFocus()
                        keyboardController?.hide()
                    }) {
                        Icon(imageVector = Icons.Outlined.Search, contentDescription = "Buscar")
                    }
                } else {
                    IconButton(onClick = {
                        viewModel.setTextoBusqueda("")
                        isSearching = !isSearching
                    }) {
                        Icon(imageVector = Icons.Outlined.Clear, contentDescription = "Borrar búsqueda")
                    }
                }
            }
        )
        Spacer(modifier = modifier.height(8.dp))
        if (!isSearching) {
            Surface(
                color = AppColors.Background,
                modifier = Modifier.fillMaxSize()
            ) {
                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    items(restaurants) { restaurant ->
                        RestaurantItem(
                            restaurant = restaurant,
                            viewModel = viewModel,
                            navController = navController
                        )
                    }
                }
            }
        } else {
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(resultados) { restaurant ->
                    RestaurantItem(
                        restaurant = restaurant,
                        viewModel = viewModel,
                        navController = navController
                    )
                }
            }
        }
    }
}