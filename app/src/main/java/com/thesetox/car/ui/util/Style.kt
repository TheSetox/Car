package com.thesetox.car.ui.util

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thesetox.car.R

@Preview(showBackground = true)
@Composable
private fun TextStylePreview() {
    val modifier = Modifier.padding(16.dp)
    Column(modifier.fillMaxSize()) {
        Surface(color = Color.Black) {
            Column(modifier) {
                Text("logoTextStyle", style = logoTextStyle)
                Text("titleTextStyle", style = titleTextStyle)
                Text("subtitleTextStyle", style = subtitleTextStyle)
            }
        }

        Text("titleItemTextStyle", style = titleItemTextStyle)
        Text("labelItemTextStyle", style = labelItemTextStyle)
        Text("comparisonTitleTextStyle", style = comparisonTitleTextStyle)
        Text("comparisonDescriptionTextStyle", style = comparisonDescriptionTextStyle)
    }
}

val logoTextStyle =
    TextStyle(
        fontFamily = FontFamily(Font(R.font.stencil)),
        fontSize = 24.sp,
        color = Color.White,
    )

val titleTextStyle =
    TextStyle(
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White,
    )

val subtitleTextStyle =
    TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White,
    )

val titleItemTextStyle =
    TextStyle(
        fontSize = 24.sp,
        fontWeight = FontWeight.SemiBold,
        color = textColor,
    )

val labelItemTextStyle =
    TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
        color = textColor,
    )

val comparisonTitleTextStyle =
    TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        color = textColor,
    )

val comparisonDescriptionTextStyle =
    TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
        color = Color.Black,
    )
