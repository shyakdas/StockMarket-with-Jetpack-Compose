package com.stock.market.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

/**
 * A custom dark color palette for use in the application's theming.
 */
private val DarkColorPalette = darkColors(
    primary = Color.Green,
    background = DarkBlue,
    onPrimary = Color.DarkGray,
    onBackground = TextWhite
)

/**
 * A Composable function for applying the StockMarketApp theme to the content.
 *
 * @param content The Composable content to apply the theme to.
 */
@Composable
fun StockMarketAppTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = DarkColorPalette,
        typography = Typography,
        shapes = Shapes(),
        content = content
    )
}