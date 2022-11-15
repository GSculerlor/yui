package moe.ganen.yui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import moe.ganen.yui.YuiTheme

@Composable
fun YuiErrorMessage(
    errorMessage: String,
    onClickReload: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = errorMessage,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.error,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        YuiTextButton(
            label = "reload",
            onClick = onClickReload,
            modifier = Modifier.wrapContentWidth()
        )
    }
}

@Preview
@Composable
fun YuiErrorMessagePreview() {
    YuiTheme {
        YuiErrorMessage(
            errorMessage = "shit went wrong, please do something or call someone",
            onClickReload = { },
            modifier = Modifier.fillMaxWidth()
        )
    }
}
