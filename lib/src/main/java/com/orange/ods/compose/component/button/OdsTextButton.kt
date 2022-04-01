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
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Colors
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.orange.ods.compose.theme.OdsDisplayAppearance
import com.orange.ods.compose.theme.OdsPrimaryRippleTheme
import com.orange.ods.compose.theme.OdsRippleTheme
import com.orange.ods.compose.theme.darkThemeColors
import com.orange.ods.compose.theme.lightThemeColors

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
 * @param displayAppearance optional allow to force the button display on a dark or light
 * surface. By default the appearance applied is based on the system night mode value.
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
    displayAppearance: OdsDisplayAppearance = OdsDisplayAppearance.DEFAULT
) {
    CompositionLocalProvider(LocalRippleTheme provides if (hasPrimaryColor) OdsPrimaryRippleTheme else OdsRippleTheme) {
        TextButton(
            onClick = onClick,
            modifier = modifier,
            enabled = enabled,
            interactionSource = remember { MutableInteractionSource() },
            shape = odsButtonShape,
            colors = ButtonDefaults.textButtonColors(
                contentColor = MaterialTheme.colors.textButtonColor(displayAppearance, hasPrimaryColor),
                disabledContentColor = MaterialTheme.colors.textButtonDisabledColor(displayAppearance)
            )
        ) {
            iconRes?.let { ButtonIcon(it) }
            Text(text.uppercase())
        }
    }
}

@Composable
private fun Colors.textButtonColor(displayAppearance: OdsDisplayAppearance, hasPrimaryColor: Boolean) =
    when (displayAppearance) {
        OdsDisplayAppearance.DEFAULT -> if (hasPrimaryColor) MaterialTheme.colors.primary else MaterialTheme.colors.onSurface
        OdsDisplayAppearance.ON_DARK -> if (hasPrimaryColor) darkThemeColors.primary else darkThemeColors.onSurface
        OdsDisplayAppearance.ON_LIGHT -> if (hasPrimaryColor) lightThemeColors.primary else lightThemeColors.onSurface
    }

@Composable
private fun Colors.textButtonDisabledColor(displayAppearance: OdsDisplayAppearance) =
    textButtonColor(displayAppearance = displayAppearance, hasPrimaryColor = false).copy(alpha = ContentAlpha.disabled)