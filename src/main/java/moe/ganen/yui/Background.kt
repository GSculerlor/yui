package moe.ganen.yui

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

@Immutable
data class BackgroundTheme(
    val backgroundColor: Color = Color.Unspecified,
    val tonalElevation: Dp = Dp.Unspecified
)

val LocalBackgroundTheme = staticCompositionLocalOf { BackgroundTheme() }
