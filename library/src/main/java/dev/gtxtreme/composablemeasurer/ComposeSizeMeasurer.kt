package dev.gtxtreme.composablemeasurer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.IntSize

/**
Created by Gaurav Thakkar on 04/07/24
 */

/**
 * A data class that holds the size and size in pixels.
 * This size is the size of the Composable inside the [MeasureSize] utility
 */
@Stable
class ComposableSizeMeasurer(
    private val density: Density
) {
    internal val mutableSize = mutableStateOf(IntSize.Zero)

    val size by mutableSize

    val sizeInPx
        get() =
            with(density) {
                Size(
                    width = size.width.toDp().toPx(),
                    height = size.height.toDp().toPx(),
                )
            }
}


/**
 * The state which will be updated with the measured size.
 * Use this utility to supply the state to the [MeasureSize] composable
 */
@Composable
fun rememberComposableSizeMeasurer(
    density: Density = LocalDensity.current
): ComposableSizeMeasurer {
    return remember {
        ComposableSizeMeasurer(
            density = density
        )
    }
}