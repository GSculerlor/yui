package moe.ganen.yui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import moe.ganen.yui.YuiTheme
import moe.ganen.yui.YuiTypography

@Composable
fun YuiChip(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.secondaryContainer,
    contentColor: Color = contentColorFor(backgroundColor = backgroundColor)
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .wrapContentWidth()
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier
                .background(backgroundColor)
                .align(Alignment.TopCenter)
                .padding(vertical = 4.dp, horizontal = 8.dp)
                .height(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = label,
                color = contentColor,
                style = YuiTypography.bodySmall
            )
        }
    }
}

@Preview
@Composable
fun YuiChipPreview() {
    YuiTheme {
        YuiChip(label = "Yui", onClick = { })
    }
}
