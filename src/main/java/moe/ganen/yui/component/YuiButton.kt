@file:OptIn(ExperimentalAnimationApi::class)

package moe.ganen.yui.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun YuiButton(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    isLoading: Boolean = false
) {
    Button(onClick = onClick, enabled = isEnabled, modifier = modifier.height(48.dp)) {
        YuiButtonContent(label = label, isLoading = isLoading)
    }
}

@Composable
fun YuiOutlinedButton(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    isLoading: Boolean = false
) {
    OutlinedButton(onClick = onClick, enabled = isEnabled, modifier = modifier.height(48.dp)) {
        YuiButtonContent(label = label, isLoading = isLoading)
    }
}

@Composable
fun YuiTextButton(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    isLoading: Boolean = false
) {
    TextButton(onClick = onClick, enabled = isEnabled, modifier = modifier.height(48.dp)) {
        YuiButtonContent(label = label, isLoading = isLoading)
    }
}

@Composable
private fun YuiButtonContent(
    label: String,
    isLoading: Boolean,
    modifier: Modifier = Modifier
) {
    AnimatedContent(
        targetState = isLoading,
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) { loading ->
        if (loading) {
            CircularProgressIndicator(
                modifier = Modifier.size(32.dp),
                color = LocalContentColor.current
            )
        } else {
            Text(text = label, style = MaterialTheme.typography.titleMedium)
        }
    }
}

@Preview
@Composable
fun YuiButtonPreview() {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        YuiButton(label = "Yui", onClick = { })
        YuiButton(label = "Yui", onClick = { }, isLoading = true)
        YuiButton(label = "Yui", onClick = { }, isEnabled = false)
        YuiButton(label = "Yui", onClick = { }, isLoading = true, isEnabled = false)
    }
}

@Preview
@Composable
fun YuiOutlinedButtonPreview() {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        YuiOutlinedButton(label = "Yui", onClick = { })
        YuiOutlinedButton(label = "Yui", onClick = { }, isLoading = true)
        YuiOutlinedButton(label = "Yui", onClick = { }, isEnabled = false)
        YuiOutlinedButton(label = "Yui", onClick = { }, isLoading = true, isEnabled = false)
    }
}

@Preview
@Composable
fun YuiTextButtonPreview() {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        YuiTextButton(label = "Yui", onClick = { })
        YuiTextButton(label = "Yui", onClick = { }, isLoading = true)
        YuiTextButton(label = "Yui", onClick = { }, isEnabled = false)
        YuiTextButton(label = "Yui", onClick = { }, isLoading = true, isEnabled = false)
    }
}
