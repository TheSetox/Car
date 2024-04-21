package com.thesetox.car.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thesetox.car.R
import com.thesetox.car.model.Car
import com.thesetox.car.model.Car.Companion.carPreview
import com.thesetox.car.ui.util.labelItemTextStyle
import com.thesetox.car.ui.util.lightGray
import com.thesetox.car.ui.util.orange
import com.thesetox.car.ui.util.titleItemTextStyle

@Preview(showBackground = true)
@Composable
private fun MainItemPreview() {
    val modifier = Modifier.padding(horizontal = 16.dp)
    val car = Car.carPreview()
    MainItem(car, modifier)
}

@Composable
fun MainItem(
    car: Car,
    modifier: Modifier,
) {
    Column {
        Row(
            Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .background(lightGray),
        ) {
            Image(
                modifier =
                    Modifier
                        .padding(start = 24.dp)
                        .size(125.dp),
                painter = painterResource(car.image),
                contentDescription = car.name,
            )
            Column(
                Modifier
                    .align(Alignment.CenterVertically)
                    .padding(16.dp),
            ) {
                Text(modifier = modifier, text = car.name, style = titleItemTextStyle)
                Text(
                    modifier = modifier,
                    text = "Price: ${car.price}",
                    maxLines = 1,
                    style = labelItemTextStyle,
                )
                Ratings(rating = car.rating, modifier)
            }
        }
        Spacer(
            modifier
                .padding(8.dp)
                .fillMaxWidth()
                .height(2.dp)
                .background(orange),
        )
    }
}

@Composable
private fun Ratings(
    rating: Int,
    modifier: Modifier = Modifier,
) {
    LazyRow(modifier.padding(top = 4.dp)) {
        repeat(rating) {
            item {
                Image(
                    modifier = Modifier.padding(2.dp),
                    painter = painterResource(id = R.drawable.rating_star),
                    contentDescription = null,
                )
            }
        }
    }
}
