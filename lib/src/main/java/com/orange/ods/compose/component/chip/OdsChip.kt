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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.Chip
import androidx.compose.material.ChipColors
import androidx.compose.material.ChipDefaults.LeadingIconOpacity
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.orange.ods.R
import com.orange.ods.compose.component.OdsComponentApi
import com.orange.ods.compose.component.chip.OdsChipDefaults.SurfaceOverlayOpacity
import com.orange.ods.compose.component.utilities.BasicPreviewParameterProvider
import com.orange.ods.compose.component.utilities.OdsImageCircleShape
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.theme.OdsTheme
import com.orange.ods.utilities.extension.enable
import com.orange.ods.utilities.extension.noRippleClickable


/**
 * <a href="https://system.design.orange.com/0c1af118d/p/81aa91-chips/b/13c40e" target="_blank">ODS Chips</a>.
 *
 * Chips are small components containing a number of elements that represent a calendar event or contact.
 * The [OdsChip] is used to display input chips, choice chips and action chips. To display filter chips please use [OdsFilterChip].
 *
 * Use Accompanist's [Flow Layouts](https://google.github.io/accompanist/flowlayout/) to wrap chips to a new line.
 *
 * @param text Text to display in the chip.
 * @param onClick called when the chip is clicked.
 * @param modifier Modifier to be applied to the chip
 * @param outlined If true, a border will be drawn around the chip otherwise a filled chip will be displayed.
 * @param enabled When disabled, chip will not respond to user input. It will also appear visually
 * disabled and disabled to accessibility services.
 * @param selected When selected the chip is highlighted (useful for choice chips).
 * @param leadingIcon Optional icon at the start of the chip, preceding the content text.
 * @param leadingAvatar Optional avatar at the start of the chip, preceding the content text.
 * @param leadingContentDescription Content description associated to the leading element.
 * @param onCancel called when the cancel cross of the chip is clicked. Pass `null` here for no cancel cross.
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
@OdsComponentApi
fun OdsChip(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    outlined: Boolean = true,
    enabled: Boolean = true,
    selected: Boolean = false,
    leadingIcon: Painter? = null,
    leadingAvatar: Painter? = null,
    leadingContentDescription: String? = null,
    onCancel: (() -> Unit)? = null
) {
    val chipStateDescription = odsChipStateDescription(selected)

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
        leadingIcon = when {
            leadingIcon != null -> {
                {
                    Icon(
                        modifier = Modifier.size(dimensionResource(id = R.dimen.chip_icon_size)),
                        painter = leadingIcon,
                        contentDescription = leadingContentDescription,
                        tint = if (enabled) OdsTheme.colors.onSurface else OdsTheme.colors.onSurface.enable(enabled = false)
                    )
                }
            }
            leadingAvatar != null -> {
                {
                    OdsImageCircleShape(
                        painter = leadingAvatar,
                        contentDescription = leadingContentDescription,
                        circleSize = dimensionResource(id = R.dimen.icon_size),
                        alpha = if (enabled) 1f else LeadingIconOpacity
                    )
                }
            }
            else -> null
        }
    ) {
        Text(
            text = text,
            style = OdsTheme.typography.body2
        ) // Don't use an OdsText here cause the color of the chip content is already managed by odsChipColors()

        onCancel?.let {
            val iconModifier = if (enabled) Modifier.noRippleClickable {
                onCancel()
            } else Modifier
            Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.spacing_s)))
            Icon(
                modifier = iconModifier.size(18.dp),
                painter = painterResource(id = R.drawable.ic_cancel),
                contentDescription = "cancel",
                tint = odsChipColors(outlined, selected).leadingIconContentColor(enabled = enabled).value
            )
        }
    }
}

@Composable
internal fun odsChipStateDescription(selected: Boolean) =
    if (selected) stringResource(id = R.string.state_selected) else stringResource(id = R.string.state_not_selected)

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
private fun PreviewOdsChip(@PreviewParameter(OdsChipPreviewParameterProvider::class) outlined: Boolean) = Preview {
    var selected by remember { mutableStateOf(false) }
    OdsChip(
        text = "Text",
        selected = selected,
        onClick = { selected = !selected },
        leadingAvatar = painterResource(id = R.drawable.placeholder_small),
        outlined = outlined
    )
}

private class OdsChipPreviewParameterProvider : BasicPreviewParameterProvider<Boolean>(false, true)
