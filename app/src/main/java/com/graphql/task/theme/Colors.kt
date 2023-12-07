package com.graphql.task.theme

import androidx.compose.ui.graphics.Color

data class AppColors(
    val background: Color,

    val fillPrimary: Color,
    val fillSecondary: Color,
    val black: Color,
    val white: Color,
) {
}

internal fun lightColors() = AppColors(
    background = Color(0xFFF5F7F9),
    fillPrimary = Color(0xFF1B1B1B),
    fillSecondary = Color(0xFF9FE2BF),
    black = Color.Black,
    white = Color.White,

    )


