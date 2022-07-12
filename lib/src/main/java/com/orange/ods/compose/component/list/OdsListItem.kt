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
import com.orange.ods.compose.text.OdsTextSubtitle1
import com.orange.ods.utilities.extension.isNotNullOrBlank

/**
 * <a href="https://system.design.orange.com/0c1af118d/p/09a804-lists/b/669743" target="_blank">ODS Lists</a>.
 *
 * Lists are continuous, vertical indexes of text or images.
 *
 * To make this [OdsListItem] clickable, use [Modifier.clickable].
 *
 * This component can be used to achieve the list item templates existing in the spec. For example:
 * - one-line items
 * @sample androidx.compose.material.samples.OneLineListItems
 * - two-line items
 * @sample androidx.compose.material.samples.TwoLineListItems
 * - three-line items
 * @sample androidx.compose.material.samples.ThreeLineListItems
 *
 * Note: If you want to display a big thumbnail without left margin in your items, please use [OdsListItemWideThumbnail].
 *
 * @param modifier Modifier to be applied to the list item
 * @param text The primary text of the list item
 * @param icon The leading supporting visual of the list item
 * @param isThumbnailIcon Whether the icon is a thumbnail (more space must be allowed in this case)
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
    icon: @Composable (() -> Unit)? = null,
    isThumbnailIcon: Boolean = false,
    secondaryText: String? = null,
    singleLineSecondaryText: Boolean = true,
    overlineText: String? = null,
    trailing: @Composable (() -> Unit)? = null
) {
    val requiredHeight = computeRequiredHeight(
        hasIcon = icon != null,
        isThumbnailIcon = isThumbnailIcon,
        hasOverline = overlineText.isNotNullOrBlank(),
        hasText = text.isNotBlank(),
        hasSecondaryText = secondaryText.isNotNullOrBlank(),
        singleLineSecondaryText = singleLineSecondaryText
    )
    val secondaryTextLinesNumber = if (singleLineSecondaryText || (overlineText != null && secondaryText != null)) 1 else 2
    ListItem(
        modifier = modifier
            .fillMaxWidth()
            .requiredHeight(requiredHeight),
        icon = icon,
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
 * Use this list item to display a wide thumbnail without start padding in the item, otherwise use [OdsListItem].
 *
 * To make this [OdsListItemWideThumbnail] clickable, use [Modifier.clickable].
 *
 * @param modifier Modifier to be applied to the list item
 * @param text The primary text of the list item
 * @param thumbnail The painter of the thumbnail
 * @param thumbnailContentDescription The content description for the given thumbnail
 * @param secondaryText The secondary text of the list item
 * @param singleLineSecondaryText Whether the secondary text is single line
 * @param overlineText The text displayed above the primary text
 * @param trailing The trailing meta text, icon, switch or checkbox
 */
@ExperimentalMaterialApi
@Composable
fun OdsListItemWideThumbnail(
    modifier: Modifier = Modifier,
    text: String,
    thumbnail: Painter,
    thumbnailContentDescription: String? = null,
    secondaryText: String? = null,
    singleLineSecondaryText: Boolean = true,
    overlineText: String? = null,
    trailing: @Composable (() -> Unit)? = null
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        OdsListWideThumbnail(painter = thumbnail, contentDescription = thumbnailContentDescription)
        OdsListItem(
            modifier = Modifier.weight(1f),
            isThumbnailIcon = true,
            text = text,
            secondaryText = secondaryText,
            singleLineSecondaryText = singleLineSecondaryText,
            overlineText = overlineText,
            trailing = trailing
        )
    }
}

/**
 * Displays an icon in a list item centered vertically
 *
 * @param painter to draw
 * @param contentDescription Content description of the icon
 */
@Composable
fun OdsListItemIcon(painter: Painter, contentDescription: String? = null) {
    Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center) {
        Icon(painter = painter, contentDescription = contentDescription)
    }
}

/**
 * Displays a 56x100 thumbnail in a list item
 *
 * @param painter to draw
 * @param contentDescription Content description of the icon
 */
@Composable
fun OdsListWideThumbnail(painter: Painter, contentDescription: String? = null) {
    Image(
        painter = painter,
        contentDescription = contentDescription,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .height(dimensionResource(id = R.dimen.list_wide_thumbnail_height))
            .width(dimensionResource(id = R.dimen.list_wide_thumbnail_width))
    )
}

/**
 * Displays a 56x56 thumbnail in a list item
 *
 * @param painter to draw
 * @param contentDescription Content description of the icon
 */
@Composable
fun OdsListSquaredThumbnail(painter: Painter, contentDescription: String? = null) {
    Image(
        painter = painter,
        contentDescription = contentDescription,
        modifier = Modifier
            .size(dimensionResource(id = R.dimen.list_squared_thumbnail_size))
            .clip(MaterialTheme.shapes.medium),
        contentScale = ContentScale.Crop
    )
}

/**
 * Computes the height of a list item depending on its attributes.
 * It allows to be able to center vertically elements in the item.
 */
@Composable
internal fun computeRequiredHeight(
    hasIcon: Boolean,
    isThumbnailIcon: Boolean,
    hasOverline: Boolean,
    hasText: Boolean,
    hasSecondaryText: Boolean,
    singleLineSecondaryText: Boolean
): Dp {
    val heightRes = when {
        // single-line
        !hasOverline && (!hasSecondaryText || !hasText) -> when {
            hasIcon && !isThumbnailIcon -> R.dimen.list_single_line_with_icon_item_height
            isThumbnailIcon -> R.dimen.list_single_line_with_thumbnail_item_height
            else -> R.dimen.list_single_line_item_height
        }
        // three-lines
        hasOverline && hasSecondaryText -> R.dimen.list_three_line_item_height
        // two-lines
        hasOverline || (hasSecondaryText && singleLineSecondaryText) -> if (hasIcon || isThumbnailIcon) R.dimen.list_two_line_item_with_icon_height else R.dimen.list_two_line_item_height
        // three-lines
        else -> R.dimen.list_three_line_item_height
    }

    return dimensionResource(id = heightRes)
}