package com.zenkun.jucr.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.zenkun.jucr.R

private val montserratFamily = FontFamily(
    Font(R.font.montserrat_bold, FontWeight.Bold),
    Font(R.font.montserrat_semibold, FontWeight.SemiBold),
    Font(R.font.montserrat_light, FontWeight.Light),
)

private val defaultTypography = Typography()
val Typography = Typography(
    headlineSmall = defaultTypography.headlineSmall.copy(
        fontFamily = montserratFamily
    ),
    displaySmall = defaultTypography.displaySmall.copy(
        fontFamily = montserratFamily
    ),
    displayMedium = defaultTypography.displayMedium.copy(
        fontFamily = montserratFamily
    ),
    displayLarge = defaultTypography.displayLarge.copy(
        fontFamily = montserratFamily
    ),

    titleMedium = defaultTypography.titleMedium.copy(
        fontFamily = montserratFamily,
        fontWeight = FontWeight.Bold,
    ),
    titleLarge = defaultTypography.titleLarge.copy(
        fontFamily = montserratFamily
    ),

    titleSmall = defaultTypography.titleSmall.copy(
        fontFamily = montserratFamily
    ),
    bodyLarge = defaultTypography.bodyLarge.copy(
        fontFamily = montserratFamily
    ),
    bodyMedium = defaultTypography.bodyMedium.copy(
        fontFamily = montserratFamily,
        fontWeight = FontWeight.SemiBold,
    ),
    bodySmall = defaultTypography.bodySmall.copy(
        fontFamily = montserratFamily
    ),
    labelLarge = defaultTypography.labelLarge.copy(
        fontFamily = montserratFamily
    ),
    labelMedium = defaultTypography.labelMedium.copy(
        fontFamily = montserratFamily
    ),
    labelSmall = defaultTypography.labelSmall.copy(
        fontSize = 12.sp,
        fontFamily = montserratFamily
    ),
)
