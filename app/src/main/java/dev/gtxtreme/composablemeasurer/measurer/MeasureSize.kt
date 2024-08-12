package dev.gtxtreme.composablemeasurer.measurer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.IntSize
import dev.gtxtreme.composablemeasurer.utils.isSameAs
import dev.gtxtreme.composablemeasurer.utils.updateSize

@Composable
fun MeasureSize(
    composableSizeMeasurer: MutableState<ComposableSizeMeasurer>,
    content: @Composable () -> Unit,
) {
    Layout(
        content = { content() },
    ) { measurables, constraints ->
        // Measure the content
        val placeables = measurables.map { it.measure(constraints) }

        // Get the maximum width and height of the placeables
        val width = placeables.maxOfOrNull { it.width } ?: 0
        val height = placeables.maxOfOrNull { it.height } ?: 0

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

        // Set the layout with the size
        layout(width, height) {
            placeables.forEach {
                it.placeRelative(0, 0)
            }
        }
    }
}