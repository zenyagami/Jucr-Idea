package com.zenkun.jucr.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

val LocalExtendedColors = staticCompositionLocalOf {
    ExtendedColors(
        material = lightColorScheme(),
        red = JucrRed,
        yellow = JucrYellow,
        success = JucrGreenSuccess,
        primarySurface = PrimarySurface
    )
}
private val DarkColorPalette = darkColorScheme(
    primary = JucrRed,
)

private val LightColorPalette = lightColorScheme(
    primary = JucrRed,
    onSurface = JucrBlack,
)

internal val LightTheme = ExtendedColors(
    material = lightColorScheme(),
    red = JucrRed,
    yellow = JucrYellow,
    success = JucrGreenSuccess,
    primarySurface = PrimarySurface
)

internal val DarkTheme = ExtendedColors(
    material = darkColorScheme(),
    red = JucrRed,
    yellow = JucrYellow,
    success = JucrGreenSuccess,
    primarySurface = PrimarySurface
)


@Composable
fun JucrAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {

    val theme = if (darkTheme) {
        DarkTheme.copy(material = DarkColorPalette)
    } else {
        LightTheme.copy(material = LightColorPalette)
    }

    CompositionLocalProvider(LocalExtendedColors provides theme) {
        MaterialTheme(
            colorScheme = theme.material,
            content = content
        )
    }
}
object JucrAppTheme {
    val colors: ExtendedColors
        @Composable
        get() = LocalExtendedColors.current
}
