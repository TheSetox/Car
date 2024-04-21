package com.thesetox.car.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.thesetox.car.model.Car
import com.thesetox.car.model.Car.Companion.listOfCar
import com.thesetox.car.ui.util.APP_TITLE
import com.thesetox.car.ui.util.Prepare
import com.thesetox.car.ui.util.PreparePreview
import com.thesetox.car.ui.util.logoTextStyle
import com.thesetox.car.ui.util.orange
import com.thesetox.car.viewmodel.MainViewModel

@Preview(showBackground = true)
@Composable
private fun MainScreenPreview() {
    PreparePreview { MainScreen() }
}

@Composable
fun MainScreen() {
    val state = remember { mutableStateOf<List<Car>>(listOf()) }
    Prepare(
        preview = { state.value = Car.listOfCar() },
        data = {
            val viewModel: MainViewModel = hiltViewModel()
            state.value = viewModel.listOfCar
        },
        screen = {
            Scaffold(
                topBar = { TopBar() },
                content = { it.MainContent(state.value) },
            )
        },
    )
}

@Composable
private fun PaddingValues.MainContent(listOfCar: List<Car>) {
    LazyColumn(Modifier.padding(top = calculateTopPadding())) {
        // First Section for promotion
        item { PromotionSection() }

        // Section for the Main List
        val modifier = Modifier.padding(horizontal = 16.dp)
        items(listOfCar) { MainItem(it, modifier) }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar() {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors().copy(containerColor = orange),
        title = { Text(text = APP_TITLE, style = logoTextStyle) },
    )
}
