package com.stock.market.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.unit.dp

/**
 * Custom shapes for use in Composable elements.
 *
 * @property small A small rounded corner shape with a radius of 4dp.
 * @property medium A medium rounded corner shape with a radius of 4dp.
 * @property large A large rounded corner shape with no corner radius (0dp).
 */
val shape = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(4.dp),
    large = RoundedCornerShape(0.dp)
)