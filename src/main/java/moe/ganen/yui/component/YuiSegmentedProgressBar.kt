package moe.ganen.yui.component

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import moe.ganen.yui.YuiTheme
import kotlin.math.floor

@Composable
fun YuiSegmentedProgressBar(
    segmentCount: Int,
    currentlyActiveSegmentIndex: Int,
    modifier: Modifier = Modifier,
    isPaused: Boolean = false,
    horizontalSpace: Dp = 8.dp,
    activeColor: Color = MaterialTheme.colorScheme.inversePrimary,
    durationPerSegment: Long = 5000,
    onFinishedProgress: (Int) -> Unit
) {
    val progress = remember { Animatable(0f) }
    val pages = remember {
        derivedStateOf {
            floor(progress.value * segmentCount).toInt().coerceAtMost(segmentCount)
        }
    }

    LaunchedEffect(currentlyActiveSegmentIndex) {
        progress.snapTo(
            targetValue = (currentlyActiveSegmentIndex + 1) / segmentCount.toFloat(),
        )
    }

    LaunchedEffect(pages.value) {
        // onFinishedProgress(pages.value)
    }

    YuiSegmentedProgressBar(
        segmentCount = segmentCount,
        horizontalSpace = horizontalSpace,
        activeColor = activeColor,
        progress = progress.value,
        modifier = modifier
    )
}

@Composable
fun YuiSegmentedProgressBar(
    segmentCount: Int,
    horizontalSpace: Dp,
    activeColor: Color,
    progress: Float,
    modifier: Modifier = Modifier
) {
    val segmentProgressBars = @Composable {
        var progressLeft = segmentCount * progress

        repeat(segmentCount) {
            val currentSegmentProgress = progressLeft.coerceAtMost(1f)

            ProgressBar(
                progress = currentSegmentProgress,
                color = activeColor
            )

            progressLeft -= currentSegmentProgress
        }
    }

    Layout(content = segmentProgressBars, modifier = modifier) { measurables, constraints ->
        val maxSegmentBarWidth =
            (constraints.maxWidth - (horizontalSpace * (segmentCount - 1)).roundToPx()) / segmentCount

        val placeables = measurables.map { segmentProgressBar ->
            segmentProgressBar.measure(constraints.copy(maxWidth = maxSegmentBarWidth))
        }

        val height = placeables.first().height

        layout(width = constraints.maxWidth, height = height) {
            var xPosition = 0
            placeables.forEach { placeable ->
                placeable.place(x = xPosition, y = 0)
                xPosition += (maxSegmentBarWidth + horizontalSpace.roundToPx())
            }
        }
    }
}

@Composable
private fun ProgressBar(
    progress: Float,
    modifier: Modifier = Modifier,
    color: Color = Color.White
) {
    Box(
        modifier = modifier
            .height(2.dp)
            .fillMaxWidth()
            .clip(CircleShape)
            .background(color.copy(alpha = 0.5f))
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(progress)
                .background(color)
        )
    }
}

@Preview
@Composable
private fun StoryProgressBarPreview() {
    YuiTheme {
        ProgressBar(progress = 0.4f)
    }
}

@Preview
@Composable
private fun YuiSegmentedProgressBarPreview() {
    YuiTheme {
        YuiSegmentedProgressBar(
            segmentCount = 10,
            currentlyActiveSegmentIndex = 1,
            horizontalSpace = 8.dp,
            onFinishedProgress = { }
        )
    }
}
