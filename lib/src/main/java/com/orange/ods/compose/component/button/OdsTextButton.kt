/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.compose.component.button

import androidx.annotation.DrawableRes
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.orange.ods.compose.theme.Black900
import com.orange.ods.compose.theme.OdsPrimaryRippleTheme
import com.orange.ods.compose.theme.OdsRippleTheme
import com.orange.ods.compose.theme.Orange200
import com.orange.ods.compose.theme.White100

/**
 * <a href="https://system.design.orange.com/0c1af118d/p/06a393-buttons/b/79b091" target="_blank">ODS Buttons</a>.
 *
 * Text buttons are typically used for less-pronounced actions, including those located in dialogs
 * and cards. In cards, text buttons help maintain an emphasis on card content.
 *
 * @param text Text displayed in the button
 * @param onClick Will be called when the user clicks the button
 * @param modifier Modifier to be applied to the button
 * @param iconRes Drawable resource of the icon. If `null`, no icon will be displayed.
 * @param enabled Controls the enabled state of the button. When `false`, this button will not
 * be clickable.
 * @param hasPrimaryColor Controls the style of the button. When `true`, the text is displayed in `primary` color, otherwise it uses the `onSurface` color.
 * @param isOnDarkSurface optional allow to force the button display on a dark or light
 * surface. By default the system night mode value is used to know if the button is displayed
 * on dark or light.
 */
@Composable
fun OdsTextButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    @DrawableRes
    iconRes: Int? = null,
    enabled: Boolean = true,
    hasPrimaryColor: Boolean = false,
    isOnDarkSurface: Boolean = isSystemInDarkTheme()
) {
    val contentColor = when {
        !hasPrimaryColor && isOnDarkSurface -> White100
        !hasPrimaryColor && !isOnDarkSurface -> Black900
        else -> Orange200
    }
    CompositionLocalProvider(LocalRippleTheme provides if (hasPrimaryColor) OdsPrimaryRippleTheme else OdsRippleTheme) {
        TextButton(
            onClick = onClick,
            modifier = modifier,
            enabled = enabled,
            interactionSource = remember { MutableInteractionSource() },
            shape = odsButtonShape,
            colors = ButtonDefaults.textButtonColors(
                contentColor = contentColor,
                disabledContentColor = contentColor.copy(alpha = ContentAlpha.disabled)
            )
        ) {
            iconRes?.let { ButtonIcon(it) }
            Text(text.uppercase())
        }
    }
}