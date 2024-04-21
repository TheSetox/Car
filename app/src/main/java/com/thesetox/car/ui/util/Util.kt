package com.thesetox.car.ui.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf

private val LocalPreviewMode: ProvidableCompositionLocal<Boolean> = compositionLocalOf { false }

@Composable
fun Prepare(
    preview: () -> Unit = {},
    data: @Composable () -> Unit = {},
    screen: @Composable () -> Unit,
) {
    if (LocalPreviewMode.current) preview() else data()

    screen()
}

@Composable
fun PreparePreview(composablePreview: @Composable () -> Unit) {
    CompositionLocalProvider(LocalPreviewMode provides true) {
        composablePreview()
    }
}
