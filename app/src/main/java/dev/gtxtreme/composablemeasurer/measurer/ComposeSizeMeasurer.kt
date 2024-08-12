package dev.gtxtreme.composablemeasurer.measurer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.IntSize

/**
Created by Gaurav Thakkar on 04/07/24
 */

/**
 * A data class that holds the size and size in pixels.
 * This size is the size of the Composable inside the [MeasureSize] utility
 */
@Stable
data class ComposableSizeMeasurer(
    val size: IntSize,
    val sizeInPx: Size,
)

/**
 * The state which will be updated with the measured size.
 * Use this utility to supply the state to the [MeasureSize] composable
 */
@Composable
fun rememberComposableSizeMeasurer(): MutableState<ComposableSizeMeasurer> {
    return remember {
        mutableStateOf(
            ComposableSizeMeasurer(
                size = IntSize.Zero,
                sizeInPx = Size.Zero,
            ),
        )
    }
}