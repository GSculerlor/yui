package moe.ganen.yui.component

import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import moe.ganen.yui.YuiTheme
import kotlin.math.max

@Composable
fun YuiFlowContainer(
    modifier: Modifier = Modifier,
    horizontalSpacing: Dp = 0.dp,
    verticalSpacing: Dp = 0.dp,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        val placeables = mutableListOf<Placeable>()
        val coordinates = mutableListOf<Pair<Int, Int>>()

        var currentXPosition = 0
        var currentYPosition = 0
        var rowHeight = 0
        var totalRowHeight = 0

        for (measurable in measurables) {
            val placeable = measurable.measure(constraints)
            // check if placeable can be added on current row or not
            if (currentXPosition + placeable.width + horizontalSpacing.roundToPx() > constraints.maxWidth) {
                // accumulate last row height + vertical spacing
                totalRowHeight += rowHeight + verticalSpacing.roundToPx()
                // reset current x coordinate to 0 since we creating a new row
                currentXPosition = 0
                // set current y coordinate to be last measured total height
                currentYPosition = totalRowHeight
                // reset the row height
                rowHeight = 0
            }

            // save the coordinates for this placeable
            coordinates.add(currentXPosition to currentYPosition)
            // update x coordinates to be current x coordinates + width of the placeable
            currentXPosition += placeable.width + horizontalSpacing.roundToPx()
            // set the row height to be the max value of either calculated row height or current placeable's height
            rowHeight = max(rowHeight, placeable.height)

            // add placeable to placeables
            placeables.add(placeable)
        }
        totalRowHeight += rowHeight

        val layoutHeight = max(totalRowHeight, constraints.minHeight)

        layout(constraints.maxWidth, layoutHeight) {
            placeables.forEachIndexed { index, placeable ->
                // place every placeable based on its saved coordinates
                placeable.placeRelative(x = coordinates[index].first, y = coordinates[index].second)
            }
        }
    }
}

class YuiThemePreviewParameterProvider :
    CollectionPreviewParameterProvider<Boolean>(listOf(true, false))

@Preview
@Composable
private fun YuiFlowContainerPreview(@PreviewParameter(YuiThemePreviewParameterProvider::class) isDarkTheme: Boolean) {
    val tags =
        listOf(
            "Tags 1",
            "Pretty long tags",
            "Tags 2",
            "Very annoyingly long tags",
            "E",
            "Tags 4",
            "Tags 5",
            "Tags 1",
            "Pretty long tags",
            "Tags 3",
            "Tags 2",
            "Very annoyingly long tags",
            "E",
            "Tags 4",
            "Very annoyingly long tags",
            "E",
            "Tags 4",
            "Tags 1",
            "Pretty long tags",
            "Tags 5"
        )

    YuiTheme(isDarkTheme = isDarkTheme) {
        YuiFlowContainer(
            horizontalSpacing = 8.dp,
            verticalSpacing = 8.dp
        ) {
            tags.forEach {
                YuiChip(label = it, onClick = { }, modifier = Modifier.wrapContentSize())
            }
        }
    }
}
