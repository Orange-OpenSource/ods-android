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

package com.orange.ods.compose.component.chip

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.Chip
import androidx.compose.material.ChipColors
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.unit.dp
import com.orange.ods.R
import com.orange.ods.compose.component.OdsComposable
import com.orange.ods.compose.component.chip.OdsChipDefaults.SurfaceOverlayOpacity
import com.orange.ods.compose.component.utilities.OdsPreview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.component.utilities.selectionStateDescription
import com.orange.ods.compose.extension.noRippleClickable
import com.orange.ods.compose.text.OdsText
import com.orange.ods.compose.theme.OdsTheme
import com.orange.ods.theme.OdsComponentsConfiguration


/**
 * <a href="https://system.design.orange.com/0c1af118d/p/81aa91-chips/b/13c40e" target="_blank">ODS Chips</a>.
 *
 * Chips are small components containing a number of elements that represent a calendar event or contact.
 * The [OdsChip] is used to display input chips, choice chips and action chips. To display filter chips please use [OdsFilterChip].
 * Note that [OdsChip] is displayed outlined or filled according to your [OdsTheme] component configuration, outlined by default.
 *
 * Use Accompanist's [Flow Layouts](https://google.github.io/accompanist/flowlayout/) to wrap chips to a new line.
 *
 * @param text Text displayed into the chip.
 * @param onClick Callback invoked on chip click.
 * @param modifier [Modifier] applied to the chip.
 * @param enabled Controls the enabled state of the chip. When `false`, this chip will not respond to user input.
 * @param selected Controls the selected state of the chip. When `true`, the chip is highlighted (useful for choice chips).
 * @param leading The leading content displayed at the start of the chip, preceding the text.
 * @param onCancel Callback called on chip cancel cross click. Pass `null` for no cancel cross.
 */
@Composable
@OdsComposable
fun OdsChip(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    selected: Boolean = false,
    leading: OdsChip.Leading? = null,
    onCancel: (() -> Unit)? = null
) {
    OdsChip(
        text = text,
        onClick = onClick,
        outlined = OdsTheme.componentsConfiguration.chipStyle == OdsComponentsConfiguration.ComponentStyle.Outlined,
        modifier = modifier,
        enabled = enabled,
        selected = selected,
        leading = leading,
        onCancel = onCancel
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
@OdsComposable
private fun OdsChip(
    text: String,
    onClick: () -> Unit,
    outlined: Boolean,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    selected: Boolean = false,
    leading: OdsChip.Leading? = null,
    onCancel: (() -> Unit)? = null
) {
    val chipStateDescription = selectionStateDescription(selected)

    Chip(
        onClick = onClick,
        modifier = modifier.semantics {
            stateDescription = chipStateDescription
        },
        border = if (outlined) {
            BorderStroke(1.dp, odsChipBorderColor(selected = selected, enabled = enabled))
        } else null,
        enabled = enabled,
        colors = odsChipColors(outlined, selected),
        leadingIcon = when (leading) {
            is OdsChip.LeadingIcon -> {
                { leading.Content() }
            }
            is OdsChip.LeadingAvatar -> {
                { leading.Content(enabled) }
            }
            else -> null
        }
    ) {
        OdsText(
            text = text,
            style = OdsTheme.typography.bodyMedium
        )

        onCancel?.let {
            val iconModifier = if (enabled) Modifier.noRippleClickable {
                onCancel()
            } else Modifier
            Spacer(modifier = Modifier.size(OdsTheme.spacings.small))
            Icon(
                modifier = iconModifier.size(18.dp),
                painter = painterResource(id = R.drawable.ic_cancel),
                contentDescription = "cancel",
                tint = odsChipColors(outlined, selected).leadingIconContentColor(enabled = enabled).value
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun odsChipColors(outlined: Boolean, selected: Boolean): ChipColors {
    val selectedBackgroundColor = OdsTheme.colors.primary.copy(alpha = SurfaceOverlayOpacity)
    val selectedContentColor = OdsTheme.colors.primary
    return when {
        outlined && !selected -> OdsChipDefaults.outlinedChipColors()
        outlined && selected -> OdsChipDefaults.outlinedChipColors(backgroundColor = selectedBackgroundColor, contentColor = selectedContentColor)
        !outlined && selected -> OdsChipDefaults.chipColors(backgroundColor = selectedBackgroundColor, contentColor = selectedContentColor)
        else -> OdsChipDefaults.chipColors()
    }
}

@Composable
private fun odsChipBorderColor(selected: Boolean, enabled: Boolean) = when {
    selected && enabled -> OdsTheme.colors.primary
    selected && !enabled -> OdsTheme.colors.primary.copy(alpha = SurfaceOverlayOpacity)
    else -> OdsTheme.colors.onSurface.copy(alpha = SurfaceOverlayOpacity)
}

@UiModePreviews.Chip
@Composable
private fun PreviewOdsChip() = OdsPreview {
    var selected by remember { mutableStateOf(false) }
    OdsChip(
        text = "Text",
        selected = selected,
        onClick = { selected = !selected },
        leading = OdsChip.LeadingAvatar(painterResource(id = R.drawable.placeholder_small), "")
    )
}