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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ListItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import com.orange.ods.R
import com.orange.ods.compose.component.utilities.OdsImageCircleShape
import com.orange.ods.compose.text.OdsTextSubtitle1
import com.orange.ods.utilities.extension.getElementOfType
import com.orange.ods.utilities.extension.isNotNullOrBlank

/**
 * <a href="https://system.design.orange.com/0c1af118d/p/09a804-lists/b/669743" target="_blank">ODS Lists</a>.
 *
 * Lists are continuous, vertical indexes of text or images.
 *
 * To make this [OdsListItem] clickable, use [Modifier.clickable].
 *
 * To specify an icon type, use [Modifier.iconType] on [modifier] and call [OdsListItemScope.OdsListItemIcon] in the [icon] lambda.
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
fun OdsListItem(
    modifier: Modifier = Modifier,
    text: String,
    icon: @Composable (OdsListItemScope.() -> Unit)? = null,
    secondaryText: String? = null,
    singleLineSecondaryText: Boolean = true,
    overlineText: String? = null,
    trailing: @Composable (OdsListItemScope.() -> Unit)? = null
) {
    val iconType = modifier.getElementOfType<OdsListItemIconTypeModifier>()?.iconType
    val listItemScope = OdsListItemScope(iconType)
    if (iconType == OdsListItemIconType.WideImage) {
        Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
            icon?.let { listItemScope.it() }
            OdsListItemInternal(
                modifier = Modifier
                    .weight(1f)
                    .iconType(OdsListItemIconType.WideImage),
                listItemScope = listItemScope,
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
            listItemScope = listItemScope,
            text = text,
            icon = icon,
            secondaryText = secondaryText,
            singleLineSecondaryText = singleLineSecondaryText,
            overlineText = overlineText,
            trailing = trailing
        )
    }
}

@ExperimentalMaterialApi
@Composable
private fun OdsListItemInternal(
    modifier: Modifier = Modifier,
    listItemScope: OdsListItemScope,
    text: String,
    icon: @Composable (OdsListItemScope.() -> Unit)? = null,
    secondaryText: String? = null,
    singleLineSecondaryText: Boolean = true,
    overlineText: String? = null,
    trailing: @Composable (OdsListItemScope.() -> Unit)? = null
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
        trailing = trailing?.let { { listItemScope.it() } },
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
fun OdsListItemScope.OdsListItemIcon(painter: Painter, contentDescription: String? = null) {
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
 * An [OdsListItemScope] provides a scope for the children of [OdsListItem].
 *
 * @param iconType The icon type
 */
data class OdsListItemScope(val iconType: OdsListItemIconType?)

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
