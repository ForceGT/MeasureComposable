package dev.gtxtreme.composablemeasurer.measurer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.unit.IntSize
import dev.gtxtreme.composablemeasurer.utils.isSameAs
import dev.gtxtreme.composablemeasurer.utils.updateSize

@Composable
fun MeasureSize(
    composableSizeMeasurer: MutableState<ComposableSizeMeasurer>,
    content: @Composable () -> Unit,
) {

    SubcomposeLayout { constraints ->

        val measurables = subcompose("content", content)
            .map {
                it.measure(constraints)
            }

        // Get the maximum width and height of the measurables
        val width = measurables.maxOfOrNull { it.width } ?: 0
        val height = measurables.maxOfOrNull { it.height } ?: 0

        // Update the composable size state
        val currentSize = IntSize(
            width = width,
            height = height,
        )

        val currentSizeInPx = Size(
            width = width.toDp().toPx(),
            height = height.toDp().toPx(),
        )

        // Update the composable size only if it has changed
        if (composableSizeMeasurer.isSameAs(
                newSize = currentSize,
                newSizeInPx = currentSizeInPx
            ).not()
        ) {
            composableSizeMeasurer.updateSize(
                size = currentSize,
                sizeInPx = currentSizeInPx
            )
        }

        // Set the layout size
        layout(width, height) {
            measurables.forEach {
                it.placeRelative(0, 0)
            }
        }
    }
}