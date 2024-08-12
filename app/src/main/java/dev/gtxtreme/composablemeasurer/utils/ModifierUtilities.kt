package dev.gtxtreme.composablemeasurer.utils

import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.IntSize

fun Modifier.measureSize(onSizeMeasured: Density.(IntSize) -> Unit): Modifier =
    this then layout { measurable, constraints ->
        val placeable = measurable.measure(constraints)
        onSizeMeasured(
            IntSize(placeable.width, placeable.height),
        )
        layout(placeable.width, placeable.height) {
            placeable.place(0, 0)
        }
    }