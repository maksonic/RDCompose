package ru.maksonic.rdcompose.shared.theme

import androidx.compose.runtime.staticCompositionLocalOf

/**
 * @Author maksonic on 24.06.2022
 */
val LocalRDImage = staticCompositionLocalOf<RDImage> {
    error("No images provided")
}

data class RDImage(
    val placeholder: Int,
    val placeholderLandscape: Int
)