package moe.ganen.yui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider
import androidx.compose.ui.unit.dp
import moe.ganen.yui.LocalBackgroundTheme
import moe.ganen.yui.R
import moe.ganen.yui.YuiTheme
import moe.ganen.yui.YuiTypography

@Composable
fun YuiChapterItem(
    title: String,
    scanlator: String,
    modifier: Modifier = Modifier,
    isRead: Boolean = false,
    externalUrl: String? = null,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .background(color = LocalBackgroundTheme.current.backgroundColor)
            .clickable { onClick() }
            .padding(horizontal = 24.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ChapterDetail(
            title = title,
            scanlator = scanlator,
            isRead = isRead,
            modifier = Modifier.weight(1f, true)
        )
        if (!externalUrl.isNullOrBlank()) {
            Icon(
                painter = painterResource(id = R.drawable.ic_open_in_external),
                contentDescription = "",
                tint = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .size(16.dp)
                    .alpha(if (isRead) 0.5f else 1f)
            )
        }
    }
}

@Composable
private fun ChapterDetail(
    title: String,
    scanlator: String,
    isRead: Boolean,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier.alpha(if (isRead) 0.5f else 1f)
    ) {
        Text(
            text = title,
            style = YuiTypography.titleSmall,
            fontWeight = if (isRead) FontWeight.Normal else FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = scanlator,
            style = YuiTypography.labelMedium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

private class YuiChapterItemParamProvider :
    CollectionPreviewParameterProvider<YuiChapterItemParamProvider.ChapterItem>(
        listOf(
            ChapterItem("Ch. 1 - Denial", "Scanlator", false),
            ChapterItem("Ch. 2 - Anger", "Different Scanlator", false, "external_url"),
            ChapterItem("Ch. 3 - Bargaining", "Different Scanlator", true),
            ChapterItem("Ch. 4 - Depression", "Scanlator", true, "external_url"),
            ChapterItem("Ch. 5 - Acceptance", "Who?", true)
        )
    ) {
    data class ChapterItem(
        val title: String,
        val scanlator: String,
        val isRead: Boolean,
        val externalUrl: String? = null
    )
}

@Preview(showBackground = true)
@Composable
private fun YuiChapterItemPreview(@PreviewParameter(YuiChapterItemParamProvider::class) param: YuiChapterItemParamProvider.ChapterItem) {
    YuiTheme {
        YuiChapterItem(
            title = param.title,
            scanlator = param.scanlator,
            isRead = param.isRead,
            externalUrl = param.externalUrl
        ) { }
    }
}
