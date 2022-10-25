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

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.orange.ods.BuildConfig
import com.orange.ods.compose.theme.OdsTheme
import kotlin.reflect.KClass

/**
 * Configures the Compose preview environment in Android Studio.
 *
 * @param content The content of the preview.
 */
@Composable
internal fun Preview(content: @Composable () -> Unit) = OdsTheme(BuildConfig.PREVIEW_THEME_CONFIGURATION) {
    Surface(color = OdsTheme.colors.surface, content = content)
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
internal open class EnumPreviewParameterProvider(clazz: KClass<out Enum<*>>) : BasicPreviewParameterProvider<Enum<*>>(*clazz.java.enumConstants)
