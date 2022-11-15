package moe.ganen.yui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import moe.ganen.yui.YuiTypography

@Composable
fun YuiTitledContainer(
    title: String,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    content: @Composable (PaddingValues) -> Unit
) {
    YuiTitledContainer(
        title = {
            Text(
                text = title,
                style = YuiTypography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        },
        modifier = modifier,
        contentPadding = contentPadding,
        content = content
    )
}

@Composable
fun YuiTitledContainer(
    title: @Composable RowScope.() -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    content: @Composable (PaddingValues) -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(contentPadding),
            verticalAlignment = Alignment.CenterVertically
        ) {
            title()
        }
        content(contentPadding)
    }
}

@Preview(showBackground = true, group = "YuiTitledContainer")
@Composable
fun YuiTitledContainerPreview() {
    YuiTitledContainer(
        title = "Yui",
        contentPadding = PaddingValues(8.dp)
    ) {
        Text(
            text = "section with button",
            modifier = Modifier
                .fillMaxWidth()
                .padding(it)
        )
    }
}

@Preview(showBackground = true, group = "YuiTitledContainer")
@Composable
fun YuiTitledContainerTitlePreview() {
    YuiTitledContainer(
        title = {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "",
                modifier = Modifier.size(16.dp)
            )
            Text(
                text = "custom title yay",
                color = MaterialTheme.colorScheme.secondary,
                style = YuiTypography.titleSmall
            )
        },
        contentPadding = PaddingValues(8.dp)
    ) {
        Text(
            text = "section with button",
            modifier = Modifier
                .fillMaxWidth()
                .padding(it)
        )
    }
}
