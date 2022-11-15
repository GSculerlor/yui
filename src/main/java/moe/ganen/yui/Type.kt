package moe.ganen.yui

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val OutfitFamily = FontFamily(
    fonts = listOf(
        Font(
            resId = R.font.outfit_light,
            weight = FontWeight.W300,
            style = FontStyle.Normal
        ),
        Font(
            resId = R.font.outfit,
            weight = FontWeight.W400,
            style = FontStyle.Normal
        ),
        Font(
            resId = R.font.outfit_medium,
            weight = FontWeight.W500,
            style = FontStyle.Normal
        ),
        Font(
            resId = R.font.outfit_semibold,
            weight = FontWeight.W600,
            style = FontStyle.Normal
        ),
        Font(
            resId = R.font.outfit_bold,
            weight = FontWeight.W700,
            style = FontStyle.Normal
        )
    )
)

private val OutfitTextStyle = TextStyle(fontFamily = OutfitFamily, fontStyle = FontStyle.Normal)

val YuiTypography = Typography(
    displayLarge = OutfitTextStyle.copy(
        fontWeight = FontWeight.Normal,
        fontSize = 57.sp,
        lineHeight = 64.sp
    ),
    displayMedium = OutfitTextStyle.copy(
        fontWeight = FontWeight.Normal,
        fontSize = 45.sp,
        lineHeight = 52.sp
    ),
    displaySmall = OutfitTextStyle.copy(
        fontWeight = FontWeight.Normal,
        fontSize = 36.sp,
        lineHeight = 44.sp
    ),
    headlineLarge = OutfitTextStyle.copy(
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp,
        lineHeight = 40.sp
    ),
    headlineMedium = OutfitTextStyle.copy(
        fontWeight = FontWeight.Normal,
        fontSize = 28.sp,
        lineHeight = 36.sp
    ),
    headlineSmall = OutfitTextStyle.copy(
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        lineHeight = 32.sp
    ),
    titleLarge = OutfitTextStyle.copy(
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp
    ),
    titleMedium = OutfitTextStyle.copy(
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    titleSmall = OutfitTextStyle.copy(
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),
    bodyLarge = OutfitTextStyle.copy(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    bodyMedium = OutfitTextStyle.copy(
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),
    bodySmall = OutfitTextStyle.copy(
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp
    ),
    labelLarge = OutfitTextStyle.copy(
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),
    labelMedium = OutfitTextStyle.copy(
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp
    ),
    labelSmall = OutfitTextStyle.copy(
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp
    )
)
