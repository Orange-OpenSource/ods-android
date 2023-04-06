/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.compose.component.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ListItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.orange.ods.R
import com.orange.ods.compose.component.OdsComponentApi
import com.orange.ods.compose.component.control.OdsCheckbox
import com.orange.ods.compose.component.control.OdsRadioButton
import com.orange.ods.compose.component.control.OdsSwitch
import com.orange.ods.compose.component.divider.OdsDivider
import com.orange.ods.compose.component.utilities.BasicPreviewParameterProvider
import com.orange.ods.compose.component.utilities.OdsImageCircleShape
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.text.OdsTextCaption
import com.orange.ods.compose.text.OdsTextSubtitle1
import com.orange.ods.compose.theme.OdsTheme
import com.orange.ods.utilities.extension.getElementOfType
import com.orange.ods.utilities.extension.isNotNullOrBlank
import com.orange.ods.utilities.extension.orElse
import kotlin.reflect.KClass

/**
 * <a href="https://system.design.orange.com/0c1af118d/p/09a804-lists/b/669743" target="_blank">ODS Lists</a>.
 *
 * Lists are continuous, vertical indexes of text or images.
 *
 * This composable allows you to display a Checkbox, a Switch, a RadioButton, an Icon or a Caption text as trailing element. If this does not meet your
 * needs, you can use the other [OdsListItem] signature which accept any Composable as trailing.
 *
 * To specify an icon type, use [Modifier.iconType] on [modifier] and call [OdsListItemIconScope.OdsListItemIcon] in the [icon] lambda.
 *
 * @param modifier Modifier to be applied to the list item
 * @param text The primary text of the list item
 * @param trailing The `OdsListItemTrailing` element to display at the end of the list item
 * @param icon The leading supporting visual of the list item
 * @param secondaryText The secondary text of the list item
 * @param singleLineSecondaryText Whether the secondary text is single line
 * @param overlineText The text displayed above the primary text
 */
@Composable
@OdsComponentApi
fun OdsListItem(
    modifier: Modifier = Modifier,
    text: String,
    trailing: OdsListItemTrailing,
    icon: @Composable (OdsListItemIconScope.() -> Unit)? = null,
    secondaryText: String? = null,
    singleLineSecondaryText: Boolean = true,
    overlineText: String? = null
) {
    OdsListItem(
        modifier = modifier.trailing(trailing),
        text = text,
        icon = icon,
        secondaryText = secondaryText,
        singleLineSecondaryText = singleLineSecondaryText,
        overlineText = overlineText,
        trailing = { OdsListItemTrailing(trailing = trailing) }
    )
}

/**
 * <a href="https://system.design.orange.com/0c1af118d/p/09a804-lists/b/669743" target="_blank">ODS Lists</a>.
 *
 * Lists are continuous, vertical indexes of text or images.
 *
 * Note: Prefer the usage of [OdsListItem] with an [OdsListItemTrailing] parameter as trailing if possible.
 * @see com.orange.ods.compose.component.list.OdsListItem
 *
 * To make this [OdsListItem] clickable, use [Modifier.clickable].
 * To specify an icon type, use [Modifier.iconType] on [modifier] and call [OdsListItemIconScope.OdsListItemIcon] in the [icon] lambda.
 *
 * @param modifier Modifier to be applied to the list item
 * @param text The primary text of the list item
 * @param icon The leading supporting visual of the list item
 * @param secondaryText The secondary text of the list item
 * @param singleLineSecondaryText Whether the secondary text is single line
 * @param overlineText The text displayed above the primary text
 * @param trailing The trailing composable. Prefer other [OdsListItem] signature with an [OdsListItemTrailing] parameter as trailing if the trailing is one of
 * the following elements: Checkbox, Switch, RadioButton, Icon or Caption text
 */
@Composable
@OdsComponentApi
fun OdsListItem(
    modifier: Modifier = Modifier,
    text: String,
    icon: @Composable (OdsListItemIconScope.() -> Unit)? = null,
    secondaryText: String? = null,
    singleLineSecondaryText: Boolean = true,
    overlineText: String? = null,
    trailing: @Composable (() -> Unit)? = null
) {
    val hasText = text.isNotBlank()
    OdsListItem(
        modifier = modifier,
        text = {
            if (hasText) {
                OdsTextSubtitle1(text = text)
            }
        },
        hasText = hasText,
        icon = icon,
        secondaryText = secondaryText,
        singleLineSecondaryText = singleLineSecondaryText,
        overlineText = overlineText,
        trailing = trailing
    )
}

@Composable
internal fun OdsListItem(
    modifier: Modifier = Modifier,
    text: @Composable () -> Unit,
    hasText: Boolean,
    icon: @Composable (OdsListItemIconScope.() -> Unit)? = null,
    secondaryText: String? = null,
    singleLineSecondaryText: Boolean = true,
    overlineText: String? = null,
    trailing: @Composable (() -> Unit)? = null
) {
    val iconType = modifier.getElementOfType<OdsListItemIconTypeModifier>()?.iconType
    val listItemIconScope = OdsListItemIconScope(iconType)
    if (iconType == OdsListItemIconType.WideImage) {
        Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
            icon?.let { listItemIconScope.it() }
            OdsListItemInternal(
                modifier = Modifier
                    .weight(1f)
                    .iconType(OdsListItemIconType.WideImage),
                listItemScope = listItemIconScope,
                text = text,
                hasText = hasText,
                icon = null,
                secondaryText = secondaryText,
                singleLineSecondaryText = singleLineSecondaryText,
                overlineText = overlineText,
                trailing = trailing
            )
        }
    } else {
        OdsListItemInternal(
            modifier = modifier,
            listItemScope = listItemIconScope,
            text = text,
            hasText = hasText,
            icon = icon,
            secondaryText = secondaryText,
            singleLineSecondaryText = singleLineSecondaryText,
            overlineText = overlineText,
            trailing = trailing
        )
    }

    modifier.getElementOfType<OdsListItemDividerModifier>()?.let { dividerModifier ->
        val startIndent = dividerModifier.startIndent
            .orElse { iconType?.getDividerStartIndent() }
            .orElse { dimensionResource(id = R.dimen.spacing_m) }
        OdsDivider(startIndent = startIndent)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun OdsListItemInternal(
    modifier: Modifier = Modifier,
    listItemScope: OdsListItemIconScope,
    text: @Composable () -> Unit,
    hasText: Boolean,
    icon: @Composable (OdsListItemIconScope.() -> Unit)? = null,
    secondaryText: String? = null,
    singleLineSecondaryText: Boolean = true,
    overlineText: String? = null,
    trailing: @Composable (() -> Unit)? = null,
) {
    val iconType = modifier.getElementOfType<OdsListItemIconTypeModifier>()?.iconType
    val requiredHeight = computeRequiredHeight(
        hasIcon = icon != null,
        iconType = iconType,
        hasOverlineText = overlineText.isNotNullOrBlank(),
        hasText = hasText,
        hasSecondaryText = secondaryText.isNotNullOrBlank(),
        singleLineSecondaryText = singleLineSecondaryText
    )
    val secondaryTextLinesNumber = if (singleLineSecondaryText || (overlineText != null && secondaryText != null)) 1 else 2

    ListItem(
        modifier = modifier
            .fillMaxWidth()
            .requiredHeight(requiredHeight),

        icon = icon?.let { { listItemScope.it() } },

        secondaryText = if (secondaryText.isNotNullOrBlank()) {
            {
                Text(
                    text = secondaryText,
                    style = OdsTheme.typography.body2,
                    maxLines = secondaryTextLinesNumber,
                    overflow = TextOverflow.Ellipsis,
                    color = OdsTheme.colors.onSurface
                )
            }
        } else null,
        singleLineSecondaryText = singleLineSecondaryText,
        overlineText = if (overlineText.isNotNullOrBlank()) {
            { Text(text = overlineText, style = OdsTheme.typography.overline, color = OdsTheme.colors.onSurface.copy(alpha = 0.6f)) }
        } else null,
        trailing = trailing,
        text = text
    )
}

/**
 * Computes the height of a list item depending on its attributes.
 * It allows to be able to center vertically elements in the item.
 */
@Composable
private fun computeRequiredHeight(
    hasIcon: Boolean,
    iconType: OdsListItemIconType?,
    hasOverlineText: Boolean,
    hasText: Boolean,
    hasSecondaryText: Boolean,
    singleLineSecondaryText: Boolean
): Dp {
    val wideImage = iconType == OdsListItemIconType.WideImage
    val heightRes = when {
        // single-line
        !hasOverlineText && (!hasSecondaryText || !hasText) -> when {
            hasIcon && !wideImage -> R.dimen.list_single_line_with_icon_item_height
            wideImage -> R.dimen.list_single_line_with_wide_image_item_height
            else -> R.dimen.list_single_line_item_height
        }
        // three-lines
        hasOverlineText && hasSecondaryText -> R.dimen.list_three_line_item_height
        // two-lines
        hasOverlineText || (hasSecondaryText && singleLineSecondaryText) -> if (hasIcon || wideImage) R.dimen.list_two_line_with_icon_item_height else R.dimen.list_two_line_item_height
        // three-lines
        else -> R.dimen.list_three_line_item_height
    }

    return dimensionResource(id = heightRes)
}

//region OdsListItem Icon

/**
 * Displays an icon in a list item.
 *
 * This method throws an exception if no icon type has been specified on the [OdsListItem] modifier using the [Modifier.iconType] method.
 *
 * @param painter Painter of the icon
 * @param contentDescription Content description of the icon
 * @param tint Icon color. Has not effect if icon type is different from `OdsListItemIconType.Icon`
 */
@Composable
@OdsComponentApi
fun OdsListItemIconScope.OdsListItemIcon(painter: Painter, contentDescription: String? = null, tint: Color = OdsTheme.colors.onSurface) {
    when (iconType) {
        OdsListItemIconType.Icon -> {
            Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center) {
                Icon(painter = painter, contentDescription = contentDescription, tint = tint)
            }
        }
        OdsListItemIconType.CircularImage -> {
            OdsImageCircleShape(painter = painter, contentDescription = contentDescription)
        }
        OdsListItemIconType.SquareImage -> {
            Image(
                painter = painter,
                contentDescription = contentDescription,
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.list_square_image_size))
                    .clip(MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop
            )
        }
        OdsListItemIconType.WideImage -> {
            Image(
                painter = painter,
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(dimensionResource(id = R.dimen.list_wide_image_height))
                    .width(dimensionResource(id = R.dimen.list_wide_image_width))
            )
        }
        null -> throw Exception("OdsListItemIcon(Painter, String?) method has been called without specifying an icon type. Please specify an icon type by calling the Modifier.iconType(OdsListItemIcon) method on the OdsList modifier.")
    }
}

/**
 * An [OdsListItemIconScope] provides a scope for the icon of [OdsListItem].
 *
 * @param iconType The icon type
 */
data class OdsListItemIconScope(val iconType: OdsListItemIconType?)

/**
 * Represents the various types of icon that can be displayed in an [OdsListItem].
 */
enum class OdsListItemIconType {

    /** A standard icon. */
    Icon,

    /** An image cropped into a circle. */
    CircularImage,

    /** An image cropped into a square. */
    SquareImage,

    /** An image cropped into a rectangle. */
    WideImage
}

/**
 * Specifies the icon type to display in an [OdsListItem].
 *
 * @param iconType The icon type
 */
fun Modifier.iconType(iconType: OdsListItemIconType): Modifier {
    return then(object : OdsListItemIconTypeModifier {
        override val iconType: OdsListItemIconType
            get() = iconType
    })
}

/**
 * A modifier that allows to configure the icon type in an [OdsListItem].
 */
private interface OdsListItemIconTypeModifier : Modifier.Element {
    val iconType: OdsListItemIconType
}

//endregion

//region OdsListItem Trailing

private fun Modifier.trailing(trailing: OdsListItemTrailing): Modifier = when (trailing) {
    is OdsCheckboxTrailing -> then(toggleable(
        value = trailing.checked.value,
        role = Role.Checkbox,
        enabled = trailing.enabled,
        onValueChange = { trailing.onCheckedChange() }
    ))
    is OdsRadioButtonTrailing<*> -> then(selectable(
        selected = trailing.selected,
        role = Role.RadioButton,
        enabled = trailing.enabled,
        onClick = {
            trailing.onClick()
        }
    ))
    is OdsSwitchTrailing -> then(toggleable(
        value = trailing.checked.value,
        role = Role.Switch,
        enabled = trailing.enabled,
        onValueChange = { trailing.onCheckedChange() }
    ))
    else -> this
}

sealed class OdsListItemTrailing
class OdsCheckboxTrailing(val checked: MutableState<Boolean>, val enabled: Boolean = true, onCheckedChange: () -> Unit = {}) : OdsListItemTrailing() {
    val onCheckedChange: () -> Unit = {
        checked.value = !checked.value
        onCheckedChange()
    }
}

class OdsSwitchTrailing(val checked: MutableState<Boolean>, val enabled: Boolean = true, onCheckedChange: () -> Unit = {}) : OdsListItemTrailing() {
    val onCheckedChange: () -> Unit = {
        checked.value = !checked.value
        onCheckedChange()
    }
}

class OdsRadioButtonTrailing<T>(val selectedRadio: MutableState<T>, val currentRadio: T, val enabled: Boolean = true, onClick: () -> Unit = {}) :
    OdsListItemTrailing() {
    val selected: Boolean
        get() = selectedRadio.value == currentRadio
    val onClick: () -> Unit = {
        selectedRadio.value = currentRadio
        onClick()
    }
}

class OdsIconTrailing(val painter: Painter, val contentDescription: String?, val modifier: Modifier = Modifier) : OdsListItemTrailing()
class OdsCaptionTrailing(val text: String) : OdsListItemTrailing()

@Composable
private fun OdsListItemTrailing(trailing: OdsListItemTrailing) {
    when (trailing) {
        is OdsCheckboxTrailing -> {
            OdsCheckbox(checked = trailing.checked.value, onCheckedChange = { trailing.onCheckedChange() }, enabled = trailing.enabled)
        }
        is OdsRadioButtonTrailing<*> -> {
            OdsRadioButton(selected = trailing.selected, onClick = { trailing.onClick() }, enabled = trailing.enabled)
        }
        is OdsSwitchTrailing -> {
            OdsSwitch(checked = trailing.checked.value, onCheckedChange = { trailing.onCheckedChange() }, enabled = trailing.enabled)
        }
        is OdsIconTrailing -> {
            with(trailing) {
                Icon(
                    modifier = modifier.clip(RoundedCornerShape(12.dp)),
                    painter = painter,
                    tint = OdsTheme.colors.onSurface,
                    contentDescription = contentDescription
                )
            }
        }
        is OdsCaptionTrailing -> {
            OdsTextCaption(text = trailing.text)
        }
    }
}

//endregion

//region OdsListItem Divider

/**
 * Displays a divider at the bottom of an [OdsListItem].
 *
 * @param startIndent The start indent of the divider
 */
fun Modifier.divider(startIndent: Dp? = null): Modifier {
    return then(object : OdsListItemDividerModifier {
        override val startIndent: Dp?
            get() = startIndent
    })
}

@Composable
private fun OdsListItemIconType.getDividerStartIndent(): Dp {
    return when (this) {
        OdsListItemIconType.Icon,
        OdsListItemIconType.CircularImage -> dimensionResource(id = R.dimen.avatar_size) + dimensionResource(id = R.dimen.spacing_m).times(2)
        OdsListItemIconType.SquareImage -> dimensionResource(id = R.dimen.list_square_image_size) + dimensionResource(id = R.dimen.spacing_m).times(2)
        OdsListItemIconType.WideImage -> dimensionResource(id = R.dimen.list_wide_image_width) + dimensionResource(id = R.dimen.spacing_m)
    }
}

/**
 * A modifier that allows to display a divider at the bottom of an [OdsListItem].
 */
private interface OdsListItemDividerModifier : Modifier.Element {
    val startIndent: Dp?
}

//endregion

@Composable
private fun getTrailingPreview(parameter: OdsListItemPreviewParameter): @Composable (() -> Unit)? {
    val checkedState = remember { mutableStateOf(false) }
    val selectedRadio = remember { mutableStateOf(0) }
    val trailing = when (parameter.previewTrailingType) {
        OdsCheckboxTrailing::class -> OdsCheckboxTrailing(checked = checkedState, enabled = true)
        OdsSwitchTrailing::class -> OdsSwitchTrailing(checked = checkedState)
        OdsRadioButtonTrailing::class -> OdsRadioButtonTrailing(selectedRadio = selectedRadio, currentRadio = 0)
        OdsIconTrailing::class -> OdsIconTrailing(
            painter = painterResource(id = android.R.drawable.ic_dialog_info),
            contentDescription = null
        )
        OdsCaptionTrailing::class -> OdsCaptionTrailing(text = "caption")
        else -> null
    }

    return trailing?.let { { OdsListItemTrailing(trailing = trailing) } }
}

@UiModePreviews.Default
@Composable
private fun PreviewOdsListItem(@PreviewParameter(OdsListItemPreviewParameterProvider::class) parameter: OdsListItemPreviewParameter) = Preview {
    with(parameter) {
        val painter = when (iconType) {
            OdsListItemIconType.Icon -> rememberVectorPainter(image = Icons.Default.AccountBox)
            OdsListItemIconType.CircularImage,
            OdsListItemIconType.SquareImage,
            OdsListItemIconType.WideImage -> painterResource(R.drawable.placeholder_small)
            null -> null
        }

        if (previewTrailingType == null) {
            OdsListItem(
                modifier = Modifier.let { modifier ->
                    iconType?.let { modifier.iconType(it) }.orElse { modifier }
                },
                text = "Text",
                icon = painter?.let { { OdsListItemIcon(painter = it) } },
                secondaryText = secondaryText,
                singleLineSecondaryText = singleLineSecondaryText
            )
        } else {
            OdsListItem(
                modifier = Modifier.let { modifier ->
                    iconType?.let { modifier.iconType(it) }.orElse { modifier }
                },
                text = "Text",
                icon = painter?.let { { OdsListItemIcon(painter = it) } },
                secondaryText = secondaryText,
                singleLineSecondaryText = singleLineSecondaryText,
                trailing = getTrailingPreview(parameter = this)
            )
        }
    }
}

internal data class OdsListItemPreviewParameter(
    val secondaryText: String?,
    val singleLineSecondaryText: Boolean,
    val iconType: OdsListItemIconType?,
    val previewTrailingType: KClass<out OdsListItemTrailing>?
)

private class OdsListItemPreviewParameterProvider : BasicPreviewParameterProvider<OdsListItemPreviewParameter>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<OdsListItemPreviewParameter>
    get() {
        val shortSecondaryText = "Secondary text"
        val longSecondaryText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor."

        return listOf(
            OdsListItemPreviewParameter(null, true, null, null),
            OdsListItemPreviewParameter(longSecondaryText, true, null, OdsCheckboxTrailing::class),
            OdsListItemPreviewParameter(shortSecondaryText, true, OdsListItemIconType.Icon, OdsIconTrailing::class),
            OdsListItemPreviewParameter(longSecondaryText, false, OdsListItemIconType.SquareImage, OdsSwitchTrailing::class),
            OdsListItemPreviewParameter(longSecondaryText, false, OdsListItemIconType.WideImage, OdsCaptionTrailing::class),
            OdsListItemPreviewParameter(shortSecondaryText, true, OdsListItemIconType.CircularImage, OdsRadioButtonTrailing::class)
        )
    }