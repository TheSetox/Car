package com.thesetox.car.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thesetox.car.R
import com.thesetox.car.ui.util.PROMOTION_IMAGE_DESCRIPTION
import com.thesetox.car.ui.util.PROMOTION_SUBTITLE
import com.thesetox.car.ui.util.PROMOTION_TITLE
import com.thesetox.car.ui.util.subtitleTextStyle
import com.thesetox.car.ui.util.titleTextStyle

@Preview(showBackground = true)
@Composable
private fun PromotionSectionPreview() {
    PromotionSection()
}

@Composable
fun PromotionSection() {
    Box {
        Image(
            painter = painterResource(R.drawable.image_tacoma),
            contentDescription = PROMOTION_IMAGE_DESCRIPTION,
        )
        Column(
            Modifier
                .align(Alignment.BottomStart)
                .padding(20.dp),
        ) {
            Text(PROMOTION_TITLE, style = titleTextStyle)
            Text(
                modifier = Modifier.padding(start = 4.dp),
                text = PROMOTION_SUBTITLE,
                style = subtitleTextStyle,
            )
        }
    }
}
