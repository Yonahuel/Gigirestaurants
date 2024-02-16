package com.econocom.gigirestaurants.ui.utils

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.econocom.gigirestaurants.viewmodel.AppViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraBusqueda(
    modifier: Modifier = Modifier,
    viewModel: AppViewModel
) {
    val query by viewModel.textoBusqueda.collectAsState()
    val buscando by viewModel.buscando.collectAsState()
    val resultados by viewModel.resultadosBusqueda.collectAsState()
    val mostrarResultados by remember { mutableStateOf(false) }

    SearchBar(
        query = query,
        onQueryChange =  viewModel::setTextoBusqueda,
        onSearch = { viewModel.searchQuery(query) },
        active = buscando,
        onActiveChange = { }
    ) {

    }
}