package com.graphql.task.theme

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val LocalColors = staticCompositionLocalOf { lightColors() }
private val LocalTypography = staticCompositionLocalOf { AppTypography() }
private val LocalImages = staticCompositionLocalOf { AppImages() }

object AppTheme {
    val colors: AppColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    val typography: AppTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current

    val images: AppImages
        @Composable
        @ReadOnlyComposable
        get() = LocalImages.current
}

@Composable
fun AppTheme(
    modifier: Modifier = Modifier,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable
    BoxScope.() ->
    Unit,
) {
    val systemUiController = rememberSystemUiController()
    LaunchedEffect(systemUiController) {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = true,
            isNavigationBarContrastEnforced = false
        )
    }

    CompositionLocalProvider(
        LocalColors provides lightColors(),
        LocalTypography provides AppTypography(),
        LocalImages provides AppImages(),
        LocalIndication provides rememberRipple()
    ) {
        Box(modifier) {
            content()
        }
    }
}
