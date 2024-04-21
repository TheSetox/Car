package com.thesetox.car.ui.util

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
private fun ColorPreview() {
    val modifier =
        Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(8.dp)
    LazyColumn(Modifier.fillMaxSize()) {
        item { Text("Orange") }
        item { Surface(modifier, color = orange, content = {}) }
        item { Text("darkGray") }
        item { Surface(modifier, color = darkGray, content = {}) }
        item { Text("lightGray") }
        item { Surface(modifier, color = lightGray, content = {}) }
        item { Text("textColor") }
        item { Surface(modifier, color = textColor, content = {}) }
    }
}

val orange = Color(0xFFFC6016)

val darkGray = Color(0xFF858585)

val lightGray = Color(0xFFD5D5D5)

val textColor = Color.Black.copy(alpha = 0.45f)
