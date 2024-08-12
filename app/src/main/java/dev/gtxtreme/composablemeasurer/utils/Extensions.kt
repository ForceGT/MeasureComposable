package dev.gtxtreme.composablemeasurer.utils

import androidx.compose.runtime.MutableState
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.IntSize
import dev.gtxtreme.composablemeasurer.measurer.ComposableSizeMeasurer

fun MutableState<ComposableSizeMeasurer>.updateSize(
    size: IntSize,
    sizeInPx: Size,
) {
    value = value.copy(
        size = size,
        sizeInPx = sizeInPx
    )
}

fun MutableState<ComposableSizeMeasurer>.isSameAs(
    newSize: IntSize?,
    newSizeInPx: Size?
): Boolean {
    return value.size == newSize && value.sizeInPx == newSizeInPx
}