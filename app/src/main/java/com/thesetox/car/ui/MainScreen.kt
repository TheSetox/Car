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
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
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
import com.thesetox.car.viewmodel.Action
import com.thesetox.car.viewmodel.MainViewModel

@Preview(showBackground = true)
@Composable
private fun MainScreenPreview() {
    PreparePreview { MainScreen() }
}

@Composable
fun MainScreen() {
    var listOfCarState: State<List<Car>> = remember { mutableStateOf(listOf()) }
    val listOfMakeState = remember { mutableStateOf<List<String>>(listOf()) }
    val listOfModelState = remember { mutableStateOf<List<String>>(listOf()) }
    var onAction: (Action) -> Unit = {}
    Prepare(
        preview = {
            listOfCarState = mutableStateOf(Car.listOfCar())
            listOfMakeState.value = Car.listOfString()
            listOfModelState.value = Car.listOfString()
        },
        data = {
            val viewModel: MainViewModel = hiltViewModel()
            listOfCarState = viewModel.listOfCarState.collectAsState()
            listOfMakeState.value = viewModel.listOfMakeState.value
            listOfModelState.value = viewModel.listOfModelState.value
            onAction = { viewModel.onAction(it) }
            onAction(Action.LoadCarList)
        },
        screen = {
            Scaffold(
                topBar = { TopBar() },
                content = {
                    it.MainContent(
                        listOfCar = listOfCarState.value,
                        listOfMake = listOfMakeState.value,
                        listOfModel = listOfModelState.value,
                        onAction = onAction,
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
    onAction: (Action) -> Unit,
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
                onAction = onAction,
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
