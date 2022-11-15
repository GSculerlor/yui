@file:OptIn(ExperimentalMaterial3Api::class)

package moe.ganen.yui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider
import androidx.compose.ui.unit.dp
import moe.ganen.yui.YuiShapes
import moe.ganen.yui.YuiTheme

@Composable
fun YuiTextField(
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    isError: Boolean = false,
    errorMessage: String? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcons: (@Composable RowScope.() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions(),
    shape: Shape = YuiShapes.extraSmall,
    colors: TextFieldColors = TextFieldDefaults.outlinedTextFieldColors()
) {
    Column(modifier = modifier) {
        TextField(
            value = value,
            onValueChange = onValueChange,
            leadingIcon = leadingIcon,
            trailingIcon = {
                YuiTextFieldTrailingIcon(
                    showClearIcon = value.isNotEmpty(),
                    onClickClearIcon = { onValueChange("") },
                    additionalContent = trailingIcons
                )
            },
            textStyle = MaterialTheme.typography.labelLarge,
            placeholder = {
                Text(
                    text = hint,
                    style = MaterialTheme.typography.labelLarge
                )
            },
            enabled = isEnabled,
            isError = isError,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            maxLines = 1,
            visualTransformation = visualTransformation,
            singleLine = true,
            colors = colors,
            shape = shape,
            modifier = Modifier.fillMaxWidth()
        )
        if (isError && errorMessage != null) {
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = errorMessage,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}

@Composable
fun YuiSearchTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    hint: String = "search",
    isEnabled: Boolean = true,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions(),
    shape: Shape = CircleShape,
    colors: TextFieldColors = TextFieldDefaults.outlinedTextFieldColors(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        textColor = MaterialTheme.colorScheme.onSurfaceVariant,
        cursorColor = MaterialTheme.colorScheme.onSurfaceVariant,
        focusedBorderColor = Color.Transparent,
        errorBorderColor = Color.Transparent,
        disabledBorderColor = Color.Transparent,
        unfocusedBorderColor = Color.Transparent
    ),
    trailingIcons: (@Composable RowScope.() -> Unit)? = null,
) {
    YuiTextField(
        value = value,
        trailingIcons = trailingIcons,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "search",
                modifier = Modifier.size(20.dp)
            )
        },
        onValueChange = onValueChange,
        hint = hint,
        modifier = modifier.height(48.dp),
        isEnabled = isEnabled,
        isError = false,
        errorMessage = null,
        visualTransformation = visualTransformation,
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
        shape = shape,
        colors = colors
    )
}

@Composable
private fun YuiTextFieldTrailingIcon(
    showClearIcon: Boolean,
    modifier: Modifier = Modifier,
    onClickClearIcon: () -> Unit,
    additionalContent: (@Composable RowScope.() -> Unit)? = null
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        additionalContent?.invoke(this)
        AnimatedVisibility(
            visible = showClearIcon,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            IconButton(
                onClick = onClickClearIcon
            ) {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = "clear",
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

private class YuiTextFieldValuePreview : CollectionPreviewParameterProvider<String>(
    listOf(
        "",
        "Yui",
        "Yui is a young human with Purple hair and eyes."
    )
)

@Preview(showBackground = true)
@Composable
private fun YuiTextFieldPreview(@PreviewParameter(YuiTextFieldValuePreview::class) param: String) {
    YuiTheme {
        YuiTextField(
            value = param,
            onValueChange = { },
            hint = "Hint",
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun YuiTextFieldTrailingIconsPreview(@PreviewParameter(YuiTextFieldValuePreview::class) param: String) {
    YuiTheme {
        YuiTextField(
            value = param,
            onValueChange = { },
            hint = "Hint",
            modifier = Modifier.fillMaxWidth(),
            trailingIcons = {
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Default.FilterList,
                        contentDescription = "filter",
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun YuiTextFieldErrorPreview(@PreviewParameter(YuiTextFieldValuePreview::class) param: String) {
    YuiTheme {
        YuiTextField(
            value = param,
            onValueChange = { },
            hint = "Hint",
            isError = true,
            errorMessage = "shit went wrong",
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun YuiSearchTextFieldPreview(@PreviewParameter(YuiTextFieldValuePreview::class) param: String) {
    YuiTheme {
        YuiSearchTextField(
            value = param,
            onValueChange = { },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun YuiTextSearchFieldTrailingIconsPreview(@PreviewParameter(YuiTextFieldValuePreview::class) param: String) {
    YuiTheme {
        YuiSearchTextField(
            value = param,
            onValueChange = { },
            hint = "Hint",
            modifier = Modifier.fillMaxWidth(),
            trailingIcons = {
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Default.FilterList,
                        contentDescription = "filter",
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun YuiTextFieldTrailingIconPreview(@PreviewParameter(YuiTextFieldValuePreview::class) param: String) {
    YuiTheme {
        YuiTextFieldTrailingIcon(
            showClearIcon = param.isNotEmpty(),
            onClickClearIcon = { },
            additionalContent = {
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Default.FilterList,
                        contentDescription = "filter",
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        )
    }
}