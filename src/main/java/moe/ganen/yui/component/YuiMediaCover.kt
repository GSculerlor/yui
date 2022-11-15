package moe.ganen.yui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import moe.ganen.yui.LocalBackgroundTheme
import moe.ganen.yui.R
import moe.ganen.yui.YuiTheme
import moe.ganen.yui.YuiTypography

@Composable
fun YuiMediaCover(
    imageUrl: String,
    modifier: Modifier = Modifier,
    title: String? = null,
    @DrawableRes placeholder: Int? = null,
    onClick: () -> Unit = { }
) {
    Column(
        modifier = modifier.clickable { onClick() },
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        SubcomposeAsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = "$title media card",
            loading = {
                YuiLoading(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp),
                    color = contentColorFor(backgroundColor = LocalBackgroundTheme.current.backgroundColor)
                )
            },
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(3 / 4f)
                .clip(RoundedCornerShape(8.dp))
        )
        if (!title.isNullOrEmpty()) {
            Text(
                text = title,
                style = YuiTypography.labelMedium,
                color = MaterialTheme.colorScheme.onSurface,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2
            )
        }
    }
}

private class YuiMediaCoverPreviewParamProvider :
    CollectionPreviewParameterProvider<Pair<Boolean, String>>(
        listOf(
            false to "",
            true to "",
            false to "Sousou no Frieren",
            true to "Sousou no Frieren"
        )
    )

@Preview
@Composable
private fun YuiMediaCoverPreview(@PreviewParameter(YuiMediaCoverPreviewParamProvider::class) param: Pair<Boolean, String>) {
    YuiTheme(param.first) {
        YuiMediaCover(
            title = param.second,
            imageUrl = "a",
            placeholder = R.drawable.pancake,
            modifier = Modifier.width(90.dp)
        )
    }
}
