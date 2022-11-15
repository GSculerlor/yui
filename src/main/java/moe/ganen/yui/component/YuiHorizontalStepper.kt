@file:OptIn(ExperimentalComposeUiApi::class)

package moe.ganen.yui.component

import androidx.annotation.IntRange
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider
import androidx.compose.ui.unit.dp
import moe.ganen.yui.YuiTheme

@Stable
class YuiHorizontalStepperState(@IntRange(from = 0) currentStep: Int = 0) {
    private var _currentStep by mutableStateOf(currentStep)

    @get:IntRange(from = 0)
    var currentStep: Int
        get() = _currentStep
        internal set(value) {
            if (value != _currentStep) {
                _currentStep = value
            }
        }

    fun updateCurrentStep(@IntRange(from = 0) currentStep: Int) {
        _currentStep = currentStep
    }

    companion object {
        fun saver(): Saver<YuiHorizontalStepperState, *> =
            Saver(
                save = {
                    listOf<Any>(
                        it._currentStep
                    )
                },
                restore = {
                    YuiHorizontalStepperState(currentStep = it[0] as Int)
                }
            )
    }
}

@Composable
fun rememberYuiHorizontalStepperState(initialStep: Int = 0): YuiHorizontalStepperState {
    return rememberSaveable(initialStep, saver = YuiHorizontalStepperState.saver()) {
        YuiHorizontalStepperState(currentStep = initialStep)
    }
}

@Composable
fun YuiHorizontalStepper(
    stepperCount: Int,
    currentStep: Int,
    modifier: Modifier = Modifier
) {
    val stepperIndicator = @Composable {
        Box(
            modifier = Modifier
                .height(4.dp)
                .fillMaxWidth()
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary)
        )
    }
    val stepperLine = @Composable {
        Box(
            modifier = Modifier
                .height(4.dp)
                .fillMaxWidth()
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primaryContainer)
        )
    }

    Layout(
        contents = listOf(stepperIndicator, stepperLine),
        modifier = modifier
    ) { (stepperIndicatorMeasurable, stepperLineMeasurable), constraints ->
        require(stepperLineMeasurable.size == 1) { "line measurable size should be one" }
        require(stepperIndicatorMeasurable.size == 1) { "indicator measurable size should be one" }

        val stepperIndicatorMaxWidth = constraints.maxWidth / stepperCount
        val stepperIndicatorPlaceable = stepperIndicatorMeasurable.first().measure(
            constraints.copy(maxWidth = stepperIndicatorMaxWidth)
        )
        val stepperLinePlaceable = stepperLineMeasurable.first().measure(constraints)

        layout(constraints.maxWidth, stepperLinePlaceable.height) {
            stepperLinePlaceable.place(0, 0)
            stepperIndicatorPlaceable.place(currentStep * stepperIndicatorMaxWidth, 0)
        }
    }
}

private class YuiHorizontalStepperParamProvider :
    CollectionPreviewParameterProvider<Pair<Int, Int>>(
        listOf(
            10 to 0,
            10 to 5,
            10 to 9,
            100 to 0,
            100 to 50,
            100 to 99
        )
    )

@Preview
@Composable
private fun YuiHorizontalStepperPreview(@PreviewParameter(YuiHorizontalStepperParamProvider::class) param: Pair<Int, Int>) {
    YuiTheme {
        YuiHorizontalStepper(stepperCount = param.first, currentStep = param.second)
    }
}
