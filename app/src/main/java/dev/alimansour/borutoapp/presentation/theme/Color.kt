package dev.alimansour.borutoapp.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

val LightGray = Color(0xFFD8D8D8)
val DarkGray = Color(0xFF2A2A2A)
val StarColor = Color(0xFFFFC94D)

val ShimmerLightGray = Color(0xFFF1F1F1)
val ShimmerMediumGray = Color(0xFFE3E3E3)
val ShimmerDarkGray = Color(0xFF1D1D1D)

val screenBackgroundColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color.Black else Color.White

val titleColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) LightGray else DarkGray

val descriptionColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) LightGray.copy(alpha = 0.5f)
    else DarkGray.copy(alpha = 0.5f)

val activeIndicatorColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Purple700 else Purple500

val inactiveIndicatorColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) DarkGray else LightGray

val buttonBackgroundColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Purple700 else Purple500

val topAppBarContentColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) LightGray else Color.White

val topAppBarBackgroundColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color.Black else Purple500

val shimmerItemBackgroundColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color.Black else ShimmerLightGray

val shimmerItemForegroundColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) ShimmerDarkGray else ShimmerMediumGray

val emptyScreenForegroundColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) LightGray else DarkGray