/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.compose.component.utilities

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.orange.ods.BuildConfig
import com.orange.ods.compose.theme.OdsTheme

/**
 * Configures the Compose preview environment in Android Studio.
 *
 * @param content The content of the preview.
 */
@Composable
fun Preview(content: @Composable () -> Unit) = OdsTheme(BuildConfig.PREVIEW_THEME_CONFIGURATION) {
    content()
}

/**
 * A basic implementation of [PreviewParameterProvider].
 *
 * @param T The type of the preview parameter.
 * @param values The preview parameter values.
 */
internal open class BasicPreviewParameterProvider<T>(vararg values: T) : PreviewParameterProvider<T> {

    override val values = values.asSequence()
}

/**
 * A preview parameter provider for enum values.
 *
 * @param clazz The enum class.
 */
internal open class EnumPreviewParameterProvider(clazz: Class<out Enum<*>>) : BasicPreviewParameterProvider<Enum<*>>(*clazz.enumConstants)

/**
 * Multipreview annotation classes used to display both light and dark mode previews.
 *
 * The only reason why `UiModePreviews` is an annotation class is to colorize it as an annotation in Android Studio.
 * An empty `Target` annotation has been added in order to avoid using the parent `UiModePreviews` annotation which has no effect.
 */
@Target
annotation class UiModePreviews {

    companion object {
        private const val LightName = "Light"
        private const val DarkName = "Dark"
        private const val ButtonWidthDp = 200
        private const val ChipWidthDp = 180
        private const val ImageTileSizeDp = 300
        private const val TabWidthDp = 100
    }

    @Preview(name = LightName)
    @Preview(name = DarkName, uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
    annotation class Default

    @Preview(name = LightName, widthDp = ButtonWidthDp)
    @Preview(name = DarkName, uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true, widthDp = ButtonWidthDp)
    annotation class Button

    @Preview(name = LightName, widthDp = ChipWidthDp)
    @Preview(name = DarkName, uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true, widthDp = ChipWidthDp)
    annotation class Chip

    @Preview(name = LightName, widthDp = ImageTileSizeDp, heightDp = ImageTileSizeDp)
    @Preview(name = DarkName, uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true, widthDp = ImageTileSizeDp, heightDp = ImageTileSizeDp)
    annotation class ImageTile

    @Preview(name = LightName, widthDp = TabWidthDp)
    @Preview(name = DarkName, uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true, widthDp = TabWidthDp)
    annotation class Tab
}
