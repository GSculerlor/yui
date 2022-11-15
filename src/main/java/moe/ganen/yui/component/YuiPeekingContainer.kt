@file:OptIn(ExperimentalMaterial3Api::class)

package moe.ganen.yui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import moe.ganen.yui.YuiTheme
import kotlin.math.max

@Immutable
data class YuiPeekingContainerColor(
    val contentBackgroundColor: Color = Color.Unspecified,
    val peekingBackgroundColor: Color = Color.Unspecified,
    val contentColor: Color = Color.Unspecified,
    val peekingColor: Color = Color.Unspecified
)

val LocalYuiPeekingContainerTheme = staticCompositionLocalOf { YuiPeekingContainerColor() }

@Composable
fun YuiPeekingContainer(
    peekingSize: Dp,
    modifier: Modifier = Modifier,
    contentBackgroundColor: Color = MaterialTheme.colorScheme.surface,
    peekingBackgroundColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    peekContent: (@Composable BoxScope.() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier.height(IntrinsicSize.Min),
        content = {
            val containerColor =
                YuiPeekingContainerColor(
                    contentBackgroundColor = contentBackgroundColor,
                    peekingBackgroundColor = peekingBackgroundColor,
                    contentColor = contentColorFor(backgroundColor = contentBackgroundColor),
                    peekingColor = contentColorFor(backgroundColor = peekingBackgroundColor)
                )
            CompositionLocalProvider(LocalYuiPeekingContainerTheme provides containerColor) {
                // TODO: might worth to combine both of this custom layout measurement into a single measurement logic
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(MaterialTheme.shapes.medium)
                        .background(peekingBackgroundColor)
                        .layout { measurable, constraints ->
                            val halfWidth = constraints.maxWidth / 2
                            val childConstraints = constraints.copy(
                                minWidth = minOf(constraints.minWidth, halfWidth),
                                maxWidth = halfWidth
                            )
                            val placeable = measurable.measure(childConstraints)

                            layout(constraints.maxWidth, constraints.maxHeight) {
                                placeable.place(halfWidth, 0)
                            }
                        }
                ) {
                    if (peekContent != null) {
                        peekContent()
                    }
                }
            }
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = contentBackgroundColor,
                    contentColor = contentColorFor(backgroundColor = contentBackgroundColor)
                )
            ) {
                content()
            }
        }
    ) { measurables, constraints ->
        // Peeking size must be greater than 0
        check(peekingSize > 0.dp)
        // Check if the measurables's size is 2
        check(measurables.size == 2)

        // Let background only drawn on peeked size since we'll place it on constraint's max width
        val peekingBgWidth =
            peekingSize.roundToPx() + (16.dp * 2).roundToPx()
        val bgConstraints = constraints.copy(
            maxWidth = peekingBgWidth,
            minWidth = minOf(constraints.minWidth, peekingBgWidth)
        )
        val backgroundPlaceable = measurables[0].measure(bgConstraints)

        // Reduce the max size children can occupy to create a peeking background
        val maxAllowedWidth = if (peekContent != null) {
            constraints.maxWidth - peekingSize.roundToPx()
        } else {
            constraints.maxWidth
        }
        val peekingWidth = if (peekContent != null) {
            peekingSize.roundToPx()
        } else {
            0
        }
        val contentConstraints = constraints.copy(
            maxWidth = maxAllowedWidth,
            minWidth = max(0, constraints.minWidth - peekingWidth)
        )
        val contentPlaceable = measurables[1].measure(contentConstraints)

        // Set min height to be 48 dp
        val height = max(contentPlaceable.height, 80.dp.roundToPx())
        layout(
            width = constraints.maxWidth,
            height = height
        ) {
            backgroundPlaceable.placeRelative(x = constraints.maxWidth - peekingBgWidth, y = 0)
            contentPlaceable.placeRelative(x = 0, y = 0)
        }
    }
}

@Preview
@Composable
fun YuiPeekingContainerPreview() {
    YuiTheme {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            YuiPeekingContainer(peekingSize = 32.dp) {
                Text(
                    text = "container without peeking content",
                    style = MaterialTheme.typography.titleMedium
                )
            }
            YuiPeekingContainer(peekingSize = 32.dp, peekContent = {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = "",
                    modifier = Modifier
                        .size(20.dp)
                        .align(Alignment.Center)
                )
            }) {
                Text(
                    text = "container with peeking content",
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}
