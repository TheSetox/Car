package com.thesetox.car.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.thesetox.car.model.Car
import com.thesetox.car.model.Car.Companion.listOfCar
import com.thesetox.car.model.Car.Companion.listOfString
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
    val listOfCar = remember { mutableStateOf<List<Car>>(listOf()) }
    val listOfMake = remember { mutableStateOf<List<String>>(listOf()) }
    val listOfModel = remember { mutableStateOf<List<String>>(listOf()) }
    var onSelected: (String, String) -> Unit = { _, _ -> }

    Prepare(
        preview = {
            listOfCar.value = Car.listOfCar()
            listOfMake.value = Car.listOfString()
            listOfModel.value = Car.listOfString()
        },
        data = {
            val viewModel: MainViewModel = hiltViewModel()
            listOfCar.value = viewModel.listOfCar.value
            listOfMake.value = viewModel.listOfMake
            listOfModel.value = viewModel.listOfModel
            onSelected = viewModel.onSelectedFilter()
        },
        screen = {
            Scaffold(
                topBar = { TopBar() },
                content = {
                    it.MainContent(
                        listOfCar = listOfCar.value,
                        listOfMake = listOfMake.value,
                        listOfModel = listOfModel.value,
                        onSelected = onSelected,
                    )
                },
            )
        },
    )
}

@Composable
private fun PaddingValues.MainContent(
    listOfCar: List<Car>,
    listOfMake: List<String>,
    listOfModel: List<String>,
    onSelected: (String, String) -> Unit,
) {
    val firstIndex = 0
    var selectedItemIndex by remember { mutableIntStateOf(firstIndex) }
    LazyColumn(
        Modifier
            .padding(top = calculateTopPadding()),
    ) {
        // First Section for promotion
        item { PromotionSection() }

        // Second Section for filter
        item {
            FilterSection(
                listOfMake = listOfMake,
                listOfModel = listOfModel,
                onSelected = onSelected,
            )
        }

        // Section for the Main List
        val modifier = Modifier.padding(horizontal = 16.dp)
        itemsIndexed(listOfCar) { index, car ->
            MainItem(selectedItemIndex, index, car, modifier) { selectedIndex ->
                selectedItemIndex = if (selectedItemIndex == selectedIndex) -1 else selectedIndex
            }
        }
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
