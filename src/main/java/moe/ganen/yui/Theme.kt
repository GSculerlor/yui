package moe.ganen.yui

import android.os.Build
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun YuiTheme(
    isDarkTheme: Boolean = false,
    useDynamicsColorScheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    val colorScheme = when {
        useDynamicsColorScheme && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (isDarkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        isDarkTheme -> YuiDarkColorScheme
        else -> YuiLightColorScheme
    }

    // TODO: provide more color context
    val backgroundTheme =
        BackgroundTheme(backgroundColor = if (isDarkTheme) Color(0xFF272329) else Color(0xFFf7f2fa))
    MaterialTheme(
        colorScheme = colorScheme,
        typography = YuiTypography,
    ) {
        CompositionLocalProvider(LocalBackgroundTheme provides backgroundTheme) {
            content()
        }
    }

    SideEffect {
        systemUiController.setStatusBarColor(
            color = backgroundTheme.backgroundColor,
            darkIcons = !isDarkTheme
        )
    }
}
