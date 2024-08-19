package dev.gtxtreme.composablemeasurer.measurer

import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.unit.IntSize

@Composable
fun MeasureSize(
    composableSizeMeasurer: ComposableSizeMeasurer,
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

        composableSizeMeasurer.mutableSize.value = currentSize


        // Set the layout size
        layout(width, height) {
            measurables.forEach {
                it.placeRelative(0, 0)
            }
        }
    }
}