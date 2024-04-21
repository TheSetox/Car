package com.thesetox.car.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
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
import com.thesetox.car.ui.util.NONE
import com.thesetox.car.ui.util.comparisonDescriptionTextStyle
import com.thesetox.car.ui.util.comparisonTitleTextStyle
import com.thesetox.car.ui.util.labelItemTextStyle
import com.thesetox.car.ui.util.lightGray
import com.thesetox.car.ui.util.orange
import com.thesetox.car.ui.util.titleItemTextStyle

@Preview(showBackground = true)
@Composable
private fun MainItemPreview() {
    val modifier = Modifier.padding(horizontal = 16.dp)
    val car = Car.carPreview()
    MainItem(0, 0, car, modifier) {}
}

@Composable
fun MainItem(
    selectedIndex: Int,
    index: Int,
    car: Car,
    modifier: Modifier,
    selected: (Int) -> Unit,
) {
    Column {
        MainCard(selected, index, car, modifier, selectedIndex)
        HorizontalDivider(
            thickness = 2.dp,
            color = orange,
            modifier =
                modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
        )
    }
}

@Composable
private fun MainCard(
    selected: (Int) -> Unit,
    index: Int,
    car: Car,
    modifier: Modifier,
    selectedIndex: Int,
) {
    Column(
        Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .background(lightGray)
            .clickable { selected(index) },
    ) {
        HeaderSection(car, modifier)
        ProsAndConsSection(selectedIndex, index, car)
    }
}

@Composable
private fun HeaderSection(
    car: Car,
    modifier: Modifier,
) {
    Row {
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
}

@Composable
private fun ColumnScope.ProsAndConsSection(
    selectedIndex: Int,
    index: Int,
    car: Car,
) {
    AnimatedVisibility(selectedIndex == index) {
        Column(
            Modifier
                .padding(horizontal = 32.dp)
                .padding(vertical = 8.dp),
        ) {
            Text(text = "Pros :", style = comparisonTitleTextStyle)
            BulletItemList(car.prosList)
            Text(text = "Cons :", style = comparisonTitleTextStyle)
            BulletItemList(car.consList)
        }
    }
}

@Composable
private fun BulletItemList(list: List<String>) {
    val size = if (list.isEmpty()) 56 else 56 * list.size
    LazyColumn(Modifier.height(size.dp)) {
        if (list.isEmpty()) {
            item { BulletItem(NONE) }
        } else {
            items(list) { item -> BulletItem(item) }
        }
    }
}

@Composable
private fun BulletItem(item: String) {
    Row(
        Modifier.padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            modifier = Modifier.size(10.dp),
            painter = painterResource(id = R.drawable.bullet_point),
            contentDescription = null,
        )
        Spacer(Modifier.size(8.dp))
        Text(text = item, style = comparisonDescriptionTextStyle)
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
