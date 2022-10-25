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

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.Chip
import androidx.compose.material.ChipColors
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ChipDefaults.ContentOpacity
import androidx.compose.material.ChipDefaults.LeadingIconOpacity
import androidx.compose.material.ContentAlpha
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.orange.ods.R
import com.orange.ods.compose.component.OdsComponentApi
import com.orange.ods.compose.component.chip.OdsChipDefaults.SurfaceOverlayOpacity
import com.orange.ods.compose.component.utilities.BasicPreviewParameterProvider
import com.orange.ods.compose.component.utilities.OdsImageCircleShape
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.theme.OdsTheme
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
 * @param outlined If set to true, a border will be drawn around the chip.
 * @param enabled When disabled, chip will not respond to user input. It will also appear visually
 * disabled and disabled to accessibility services.
 * @param selected When selected the chip is highlighted (useful for choice chips).
 * @param leadingIcon Optional icon at the start of the chip, preceding the content text.
 * @param leadingAvatar Optional avatar at the start of the chip, preceding the content text.
 * @param leadingContentDescription Content description associated to the leading element.
 * @param onCancel called when the cancel cross of the chip is clicked. Pass `null` here for no cancel cross.
 */
@ExperimentalMaterialApi
@Composable
@OdsComponentApi
fun OdsChip(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    outlined: Boolean = false,
    enabled: Boolean = true,
    selected: Boolean = false,
    leadingIcon: Painter? = null,
    leadingAvatar: Painter? = null,
    leadingContentDescription: String? = null,
    onCancel: (() -> Unit)? = null
) {
    val chipStateDescription = if (selected) stringResource(id = R.string.state_selected) else stringResource(id = R.string.state_not_selected)
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
                        contentDescription = leadingContentDescription
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

@ExperimentalMaterialApi
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


internal object OdsChipDefaults {

    /**
     * The color opacity used for chip's surface overlay.
     */
    internal const val SurfaceOverlayOpacity = 0.12f

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun chipColors(
        backgroundColor: Color = OdsTheme.colors.onSurface.copy(alpha = SurfaceOverlayOpacity)
            .compositeOver(OdsTheme.colors.surface),
        contentColor: Color = OdsTheme.colors.onSurface.copy(alpha = ContentOpacity)
    ) = ChipDefaults.chipColors(
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        leadingIconContentColor = contentColor.copy(alpha = LeadingIconOpacity),
        disabledBackgroundColor = backgroundColor.copy(
            alpha = ContentAlpha.disabled * SurfaceOverlayOpacity
        ).compositeOver(OdsTheme.colors.surface)
    )

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun outlinedChipColors(
        backgroundColor: Color = OdsTheme.colors.surface,
        contentColor: Color = OdsTheme.colors.onSurface.copy(alpha = ContentOpacity),
    ) = ChipDefaults.outlinedChipColors(
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        leadingIconContentColor = OdsTheme.colors.onSurface
    )
}

@Composable
@ExperimentalMaterialApi
private fun PreviewOdsChip(outlined: Boolean) = Preview {
    val selected = remember { mutableStateOf(false) }
    OdsChip(
        text = "Text",
        selected = selected.value,
        onClick = { selected.value = !selected.value },
        leadingAvatar = painterResource(id = R.drawable.placeholder_small),
        outlined = outlined
    )
}

@Preview(
    name = "OdsChip - Light",
    widthDp = 180
)
@Composable
@ExperimentalMaterialApi
private fun PreviewOdsChipLight(@PreviewParameter(OdsChipPreviewParameterProvider::class) outlined: Boolean) {
    PreviewOdsChip(outlined)
}

@Preview(
    name = "OdsChip - Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    widthDp = 180
)
@Composable
@ExperimentalMaterialApi
private fun PreviewOdsChipDark(@PreviewParameter(OdsChipPreviewParameterProvider::class) outlined: Boolean) {
    PreviewOdsChip(outlined)
}

internal class OdsChipPreviewParameterProvider : BasicPreviewParameterProvider<Boolean>(false, true)
