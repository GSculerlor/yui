package moe.ganen.yui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import moe.ganen.yui.R
import moe.ganen.yui.YuiTheme

@Composable
fun YuiMediaCard(
    title: String,
    content: String,
    mediaUrl: String?,
    @DrawableRes placeholder: Int,
    modifier: Modifier = Modifier,
    peekContent: (@Composable BoxScope.() -> Unit)? = null
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        YuiPeekingContainer(
            peekingSize = 30.dp,
            modifier = Modifier.fillMaxWidth(),
            peekContent = peekContent
        ) {
            Row(modifier = Modifier.fillMaxSize()) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(mediaUrl)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(id = placeholder),
                    fallback = painterResource(id = placeholder),
                    error = painterResource(id = placeholder),
                    contentDescription = "$title media card",
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(68.dp),
                    contentScale = ContentScale.Crop
                )
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 8.dp, horizontal = 12.dp)
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleMedium,
                        color = LocalYuiPeekingContainerTheme.current.contentColor,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    if (content.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = content,
                            style = MaterialTheme.typography.bodyMedium,
                            color = LocalYuiPeekingContainerTheme.current.contentColor,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun YuiMediaCardPreview(@PreviewParameter(MediaCardProvider::class) params: Pair<String, String>) {
    YuiTheme {
        YuiMediaCard(
            title = params.first,
            content = params.second,
            mediaUrl = "",
            placeholder = R.drawable.frieren,
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
        )
    }
}

@Preview
@Composable
private fun YuiMediaCardWithPeekContentPreview(@PreviewParameter(MediaCardProvider::class) params: Pair<String, String>) {
    YuiTheme {
        YuiMediaCard(
            title = params.first,
            content = params.second,
            mediaUrl = "",
            placeholder = R.drawable.frieren,
            peekContent = {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "",
                    modifier = Modifier
                        .size(20.dp)
                        .align(Alignment.Center)
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
        )
    }
}

class MediaCardProvider : CollectionPreviewParameterProvider<Pair<String, String>>(
    listOf(
        "media card with desc" to "description here, often very very long shit is written here",
        "media card with short desc" to "short desc",
        "media card with no desc" to ""
    )
)
