/*
 * Software Name: Orange Design System
 * SPDX-FileCopyrightText: Copyright (c) Orange SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT license,
 * the text of which is available at https://opensource.org/license/MIT/
 * or see the "LICENSE" file for more details.
 *
 * Software description: Android library of reusable graphical components
 */

package com.orange.ods.compose.component.button

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.orange.ods.compose.component.OdsComposable
import com.orange.ods.compose.component.content.OdsComponentContent
import com.orange.ods.compose.component.content.OdsComponentIcon
import com.orange.ods.compose.component.icon.OdsIcon
import com.orange.ods.compose.component.utilities.BasicPreviewParameterProvider
import com.orange.ods.compose.component.utilities.OdsPreview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.theme.OdsTheme

/**
 * OdsIconButton is a clickable icon, used to represent actions. An OdsIconButton has an overall minimum
 * touch target size of 48 x 48dp, to meet accessibility guidelines. It contains an [Icon] centered
 * inside the OdsIconButton.
 * If using a custom icon, note that the typical size for the internal icon is 24 x 24 dp.
 *
 * This component is typically used inside an App Bar for the navigation icon / actions.
 *
 * @param icon Icon to be drawn into the button.
 * @param onClick Callback to be invoked when the button is clicked.
 * @param modifier [Modifier] to be applied to the button.
 * @param enabled Controls the enabled state of the button. When `false`, this button will not be clickable.
 */
@Composable
@OdsComposable
fun OdsIconButton(
    icon: OdsIconButton.Icon,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    IconButton(onClick = onClick, modifier = modifier, enabled = enabled) {
        icon.Content(OdsIconButton.Icon.ExtraParameters(enabled))
    }
}

@Composable
internal fun OdsIconButton(
    onClick: () -> Unit,
    graphicsObject: Any,
    contentDescription: String,
    tint: Color,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    IconButton(onClick = onClick, modifier = modifier, enabled = enabled) {
        OdsIcon(graphicsObject = graphicsObject, contentDescription = contentDescription, tint = tint, enabled = enabled)
    }
}

/**
 * Contains classes to build an [com.orange.ods.compose.component.button.OdsIconButton].
 */
object OdsIconButton {

    /**
     * An icon in an [OdsIconButton].
     */
    class Icon private constructor(
        val graphicsObject: Any,
        val contentDescription: String
    ) : OdsComponentIcon<Icon.ExtraParameters>(ExtraParameters::class.java, graphicsObject, contentDescription) {

        data class ExtraParameters internal constructor(
            internal val enabled: Boolean
        ) : OdsComponentContent.ExtraParameters()

        /**
         * Creates an instance of [OdsIconButton.Icon].
         *
         * @param painter Painter of the icon.
         * @param contentDescription The content description associated to this [OdsIconButton.Icon].
         */
        constructor(painter: Painter, contentDescription: String) : this(painter as Any, contentDescription)

        /**
         * Creates an instance of [OdsIconButton.Icon].
         *
         * @param imageVector Image vector of the icon.
         * @param contentDescription The content description associated to this [OdsIconButton.Icon].
         */
        constructor(imageVector: ImageVector, contentDescription: String) : this(imageVector as Any, contentDescription)

        /**
         * Creates an instance of [OdsIconButton.Icon].
         *
         * @param bitmap Image bitmap of the icon.
         * @param contentDescription The content description associated to this [OdsIconButton.Icon].
         */
        constructor(bitmap: ImageBitmap, contentDescription: String) : this(bitmap as Any, contentDescription)

        override val tint: Color
            @Composable
            get() = OdsTheme.colors.onSurface

        @Composable
        override fun Content(modifier: Modifier) {
            enabled = extraParameters.enabled
            super.Content(modifier)
        }
    }

}

@UiModePreviews.Default
@Composable
private fun PreviewOdsIconButton(@PreviewParameter(OdsIconButtonPreviewParameterProvider::class) enabled: Boolean) = OdsPreview {
    OdsIconButton(
        onClick = {},
        icon = OdsIconButton.Icon(painterResource(id = android.R.drawable.ic_dialog_info), ""),
        enabled = enabled
    )
}

private class OdsIconButtonPreviewParameterProvider : BasicPreviewParameterProvider<Boolean>(true, false)

