package com.zenkun.jucr.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class ExtendedColors(
    val material: ColorScheme,
    val red: Color,
    val yellow: Color,
    val success: Color,
    val primarySurface: Color
)
