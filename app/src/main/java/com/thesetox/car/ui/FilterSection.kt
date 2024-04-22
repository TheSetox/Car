package com.thesetox.car.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thesetox.car.ui.util.DEFAULT_MAKE
import com.thesetox.car.ui.util.DEFAULT_MODEL
import com.thesetox.car.ui.util.darkGray
import com.thesetox.car.ui.util.filterTitleTextStyle
import com.thesetox.car.ui.util.labelItemTextStyle
import com.thesetox.car.viewmodel.Action

@Preview(showBackground = true)
@Composable
private fun FilterSectionPreview() {
    FilterSection(listOf(), listOf()) {}
}

@Composable
fun FilterSection(
    listOfMake: List<String>,
    listOfModel: List<String>,
    onAction: (Action) -> Unit,
) {
    var selectedMake by remember { mutableStateOf(DEFAULT_MAKE) }
    var selectedModel by remember { mutableStateOf(DEFAULT_MODEL) }

    Surface(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(24.dp),
        shape = RoundedCornerShape(8.dp),
        color = darkGray,
    ) {
        Column(
            Modifier
                .padding(horizontal = 20.dp, vertical = 16.dp)
                .padding(bottom = 4.dp)
                .fillMaxWidth(),
        ) {
            Text(text = "Filters", style = filterTitleTextStyle)
            Spacer(modifier = Modifier.size(8.dp))
            FilterButton(
                defaultLabel = DEFAULT_MAKE,
                list = listOfMake,
                action = Action.LoadMakeList,
                onAction = onAction,
            ) {
                selectedMake = it
                onAction(Action.FilterList(selectedMake, selectedModel))
            }
            FilterButton(
                defaultLabel = DEFAULT_MODEL,
                list = listOfModel,
                action = Action.LoadModelList,
                onAction = onAction,
            ) {
                selectedModel = it
                onAction(Action.FilterList(selectedMake, selectedModel))
            }
        }
    }
}

@Composable
fun FilterButton(
    defaultLabel: String,
    list: List<String>,
    action: Action,
    onAction: (Action) -> Unit,
    onSelected: (String) -> Unit,
) {
    var isExpanded by remember { mutableStateOf(false) }
    var selectedFilter by remember { mutableStateOf(defaultLabel) }
    Surface(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .clickable {
                    onAction(action)
                    isExpanded = !isExpanded
                },
        shape = RoundedCornerShape(8.dp),
        shadowElevation = 8.dp,
    ) {
        Text(
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
            text = selectedFilter,
            style = labelItemTextStyle,
        )
        DropdownMenu(
            modifier = Modifier.fillMaxWidth(.8F),
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false },
        ) {
            DropdownMenuItem(
                text = { Text(text = defaultLabel) },
                onClick = {
                    selectedFilter = defaultLabel
                    isExpanded = false
                    onSelected(selectedFilter)
                },
            )
            list.forEach { label ->
                DropdownMenuItem(
                    text = { Text(text = label) },
                    onClick = {
                        selectedFilter = label
                        isExpanded = false
                        onSelected(selectedFilter)
                    },
                )
            }
        }
    }
}
