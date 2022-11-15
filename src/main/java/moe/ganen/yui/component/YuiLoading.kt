package moe.ganen.yui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import moe.ganen.yui.YuiTheme

@Composable
fun YuiLoading(modifier: Modifier = Modifier, color: Color = Color.Unspecified) {
    Box(modifier = modifier) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center),
            color = if (color != Color.Unspecified) color else MaterialTheme.colorScheme.onBackground
        )
    }
}

@Preview
@Composable
fun YuiLoadingPreview() {
    YuiTheme {
        YuiLoading(modifier = Modifier.fillMaxWidth())
    }
}
