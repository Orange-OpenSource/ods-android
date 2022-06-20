/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.compose.component.chip

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.Chip
import androidx.compose.material.ChipColors
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ChipDefaults.LeadingIconOpacity
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.orange.ods.R
import com.orange.ods.compose.component.utilities.OdsImageCircleShape
import com.orange.ods.compose.text.OdsTextBody2


/**
 * The color opacity used for chip's surface overlay.
 */
const val ChipSurfaceOverlayOpacity = 0.12f

/**
 * <a href="https://system.design.orange.com/0c1af118d/p/81aa91-chips/b/13c40e" target="_blank">ODS Chips</a>.
 *
 * Chips are small components containing a number of elements that represent a calendar event or contact.
 *
 * Use Accompanist's [Flow Layouts](https://google.github.io/accompanist/flowlayout/) to wrap chips to a new line.
 *
 * @param text Text to display in the chip.
 * @param onClick called when the chip is clicked.
 * @param modifier Modifier to be applied to the chip
 * @param outlined If set to true, a border will be drawn around the chip.
 * @param enabled When disabled, chip will not respond to user input. It will also appear visually
 * disabled and disabled to accessibility services.
 * @param leadingIcon Optional icon at the start of the chip, preceding the content text.
 * @param leadingAvatar Optional avatar at the start of the chip, preceding the content text.
 * @param leadingElementContentDescription Content description associated to the leading element (icon or avatar).
 * @param onCancel called when the cancel cross of the chip is clicked. Pass `null` here for no cancel cross.
 */
@ExperimentalMaterialApi
@Composable
fun OdsChip(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    outlined: Boolean = false,
    enabled: Boolean = true,
    selected: Boolean = false,
    leadingIcon: Painter? = null,
    leadingAvatar: Painter? = null,
    leadingElementContentDescription: String? = null,
    onCancel: (() -> Unit)? = null
) {
    Chip(
        onClick = onClick,
        modifier = modifier,
        border = if (outlined) {
            BorderStroke(1.dp, odsChipBorderColor(selected = selected, enabled = enabled))
        } else null,
        enabled = enabled,
        colors = odsChipColors(outlined, selected),
        leadingIcon = when {
            leadingIcon != null -> {
                {
                    Icon(
                        modifier = Modifier.size(dimensionResource(id = R.dimen.chip_icon_size)),
                        painter = leadingIcon,
                        contentDescription = leadingElementContentDescription
                    )
                }
            }
            leadingAvatar != null -> {
                {
                    OdsImageCircleShape(
                        painter = leadingAvatar,
                        contentDescription = leadingElementContentDescription,
                        circleSize = dimensionResource(id = R.dimen.icon_size),
                        alpha = if (enabled) 1f else LeadingIconOpacity
                    )
                }
            }
            else -> null
        }
    ) {
        OdsTextBody2(text = text)
        onCancel?.let {
            Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.ods_spacing_xs)))
            Icon(
                modifier = Modifier
                    .size(18.dp)
                    .clickable { onCancel() },
                painter = painterResource(id = R.drawable.ic_cancel),
                contentDescription = "cancel",
                tint = odsChipColors(outlined, selected).leadingIconContentColor(enabled = enabled).value
            )
        }
    }
}

@ExperimentalMaterialApi
@Composable
private fun odsChipColors(outlined: Boolean, selected: Boolean): ChipColors {
    val selectedBackgroundColor = MaterialTheme.colors.primary.copy(alpha = ChipSurfaceOverlayOpacity)
    val selectedContentColor = MaterialTheme.colors.primary
    return when {
        outlined && !selected -> ChipDefaults.outlinedChipColors()
        outlined && selected -> ChipDefaults.outlinedChipColors(backgroundColor = selectedBackgroundColor, contentColor = selectedContentColor)
        !outlined && selected -> ChipDefaults.chipColors(backgroundColor = selectedBackgroundColor, contentColor = selectedContentColor)
        else -> ChipDefaults.chipColors()
    }
}

@Composable
private fun odsChipBorderColor(selected: Boolean, enabled: Boolean): Color {
    return if (selected) {
        if (enabled) MaterialTheme.colors.primary else MaterialTheme.colors.primary.copy(alpha = ChipSurfaceOverlayOpacity)
    } else {
        MaterialTheme.colors.onSurface.copy(alpha = ChipSurfaceOverlayOpacity)
    }
}
