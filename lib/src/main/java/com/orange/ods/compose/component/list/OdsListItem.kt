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

import android.content.res.Configuration
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ListItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.orange.ods.R
import com.orange.ods.compose.component.OdsComponentApi
import com.orange.ods.compose.component.control.OdsCheckbox
import com.orange.ods.compose.component.control.OdsSwitch
import com.orange.ods.compose.component.utilities.BasicPreviewParameterProvider
import com.orange.ods.compose.component.utilities.OdsImageCircleShape
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.text.OdsTextSubtitle1
import com.orange.ods.utilities.extension.getElementOfType
import com.orange.ods.utilities.extension.isNotNullOrBlank
import com.orange.ods.utilities.extension.orElse

/**
 * <a href="https://system.design.orange.com/0c1af118d/p/09a804-lists/b/669743" target="_blank">ODS Lists</a>.
 *
 * Lists are continuous, vertical indexes of text or images.
 *
 * To make this [OdsListItem] clickable, use [Modifier.clickable].
 *
 * To specify an icon type, use [Modifier.iconType] on [modifier] and call [OdsListItemIconScope.OdsListItemIcon] in the [icon] lambda.
 *
 * This component can be used to achieve the list item templates existing in the spec. For example:
 * - one-line items
 * @sample androidx.compose.material.samples.OneLineListItems
 * - two-line items
 * @sample androidx.compose.material.samples.TwoLineListItems
 * - three-line items
 * @sample androidx.compose.material.samples.ThreeLineListItems
 *
 * @param modifier Modifier to be applied to the list item
 * @param text The primary text of the list item
 * @param icon The leading supporting visual of the list item
 * @param secondaryText The secondary text of the list item
 * @param singleLineSecondaryText Whether the secondary text is single line
 * @param overlineText The text displayed above the primary text
 * @param trailing The trailing meta text, icon, switch or checkbox
 */
@Composable
@ExperimentalMaterialApi
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
        Divider(startIndent = startIndent)
    }
}

@ExperimentalMaterialApi
@Composable
private fun OdsListItemInternal(
    modifier: Modifier = Modifier,
    listItemScope: OdsListItemIconScope,
    text: String,
    icon: @Composable (OdsListItemIconScope.() -> Unit)? = null,
    secondaryText: String? = null,
    singleLineSecondaryText: Boolean = true,
    overlineText: String? = null,
    trailing: @Composable (() -> Unit)? = null
) {
    val iconType = modifier.getElementOfType<OdsListItemIconTypeModifier>()?.iconType
    val requiredHeight = computeRequiredHeight(
        hasIcon = icon != null,
        iconType = iconType,
        hasOverlineText = overlineText.isNotNullOrBlank(),
        hasText = text.isNotBlank(),
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
            { Text(text = secondaryText, style = MaterialTheme.typography.body2, maxLines = secondaryTextLinesNumber, overflow = TextOverflow.Ellipsis) }
        } else null,
        singleLineSecondaryText = singleLineSecondaryText,
        overlineText = if (overlineText.isNotNullOrBlank()) {
            { Text(text = overlineText, style = MaterialTheme.typography.overline, color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f)) }
        } else null,
        trailing = trailing,
        text = {
            if (text.isNotBlank()) {
                OdsTextSubtitle1(text = text)
            }
        }
    )
}

/**
 * Displays an icon in a list item.
 *
 * This method throws an exception if no icon type has been specified on the [OdsListItem] modifier using the [Modifier.iconType] method.
 *
 * @param painter to draw
 * @param contentDescription Content description of the icon
 */
@Composable
@OdsComponentApi
fun OdsListItemIconScope.OdsListItemIcon(painter: Painter, contentDescription: String? = null) {
    when (iconType) {
        OdsListItemIconType.Icon -> {
            Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center) {
                Icon(painter = painter, contentDescription = contentDescription)
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

/**
 * A modifier that allows to configure the icon type in an [OdsListItem].
 */
private interface OdsListItemIconTypeModifier : Modifier.Element {

    val iconType: OdsListItemIconType
}

/**
 * A modifier that allows to display a divider at the bottom of an [OdsListItem].
 */
private interface OdsListItemDividerModifier : Modifier.Element {

    val startIndent: Dp?
}

@Composable
@ExperimentalMaterialApi
private fun PreviewOdsListItem(parameter: OdsListItemPreviewParameter) = Preview {
    with(parameter) {
        val painter = when (iconType) {
            OdsListItemIconType.Icon -> rememberVectorPainter(image = Icons.Default.AccountBox)
            OdsListItemIconType.CircularImage,
            OdsListItemIconType.SquareImage,
            OdsListItemIconType.WideImage -> painterResource(R.drawable.placeholder_small)
            null -> null
        }

        OdsListItem(
            modifier = Modifier.let { modifier ->
                iconType?.let { modifier.iconType(it) }.orElse { modifier }
            },
            text = "Text",
            icon = painter?.let { { OdsListItemIcon(painter = it) } },
            secondaryText = secondaryText,
            singleLineSecondaryText = singleLineSecondaryText,
            trailing = trailing
        )
    }
}

@Preview(name = "OdsListItem - Light")
@Composable
@ExperimentalMaterialApi
private fun PreviewOdsListItemLight(@PreviewParameter(OdsListItemPreviewParameterProvider::class) parameter: OdsListItemPreviewParameter) {
    PreviewOdsListItem(parameter)
}

@Preview(
    name = "OdsListItem - Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
@ExperimentalMaterialApi
private fun PreviewOdsListItemDark(@PreviewParameter(OdsListItemPreviewParameterProvider::class) parameter: OdsListItemPreviewParameter) {
    PreviewOdsListItem(parameter)
}

internal data class OdsListItemPreviewParameter(
    val secondaryText: String?,
    val singleLineSecondaryText: Boolean,
    val iconType: OdsListItemIconType?,
    val trailing: (@Composable () -> Unit)?
)

internal class OdsListItemPreviewParameterProvider : BasicPreviewParameterProvider<OdsListItemPreviewParameter>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<OdsListItemPreviewParameter>
    get() {
        val secondaryTexts = listOf(null, "Secondary text", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor.")
        val iconTypes = listOf(null, *OdsListItemIconType.values())
        val trailings: List<(@Composable () -> Unit)?> = listOf(
            null,
            {
                var checked by remember { mutableStateOf(true) }
                OdsCheckbox(checked = checked, onCheckedChange = { checked = it })
            },
            {
                var checked by remember { mutableStateOf(true) }
                OdsSwitch(checked = checked, onCheckedChange = { checked = it })
            },
            {
                Icon(
                    modifier = Modifier.clip(RoundedCornerShape(12.dp)),
                    painter = painterResource(id = android.R.drawable.ic_dialog_info),
                    contentDescription = null
                )
            }
        )

        return secondaryTexts.flatMap { secondaryText ->
            val singleLineSecondaryText = secondaryText != secondaryTexts.last()
            iconTypes.flatMap { iconType ->
                trailings.map { trailing ->
                    OdsListItemPreviewParameter(secondaryText, singleLineSecondaryText, iconType, trailing)
                }
            }
        }
    }
