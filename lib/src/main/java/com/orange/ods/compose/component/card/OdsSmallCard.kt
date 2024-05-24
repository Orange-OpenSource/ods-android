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

package com.orange.ods.compose.component.card

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.orange.ods.R
import com.orange.ods.compose.component.OdsComposable
import com.orange.ods.compose.component.utilities.BasicPreviewParameterProvider
import com.orange.ods.compose.component.utilities.OdsPreview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.text.OdsText
import com.orange.ods.compose.theme.OdsTheme
import com.orange.ods.theme.typography.OdsTextStyle

/**
 * <a href="https://system.design.orange.com/0c1af118d/p/272739-cards/b/991690" target="_blank">ODS Card</a>.
 *
 * Cards contain content and actions about a single subject.
 *
 * @param title Title displayed into the card. If the card is clickable, it will be truncated to fit on one line otherwise it will be displayed entirely for
 * accessibility reasons.
 * @param image [OdsCard.Image] displayed into the card.
 * @param modifier [Modifier] applied to the layout of the card.
 * @param subtitle Subtitle displayed into the card. If the card is clickable, it will be truncated to fit on one line otherwise it will be displayed
 * entirely for accessibility reasons.
 * @param onClick Callback invoked on card click.
 */
@Composable
@OdsComposable
fun OdsSmallCard(
    title: String,
    image: OdsCard.Image,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    onClick: (() -> Unit)? = null
) {
    OdsCard(
        modifier = modifier,
        onClick = onClick
    ) {
        image.Content(
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(R.dimen.card_small_image_width))
        )
        Column(
            modifier = Modifier
                .padding(OdsTheme.spacings.medium.dp)
        ) {
            SmallCardText(text = title, style = OdsTheme.typography.titleLarge, isClickableCard = onClick != null)
            subtitle?.let {
                SmallCardText(text = it, style = OdsTheme.typography.bodyMedium, isClickableCard = onClick != null)
            }
        }
    }
}

@Composable
private fun SmallCardText(text: String, style: OdsTextStyle, isClickableCard: Boolean) {
    OdsText(
        text = text,
        style = style,
        maxLines = if (isClickableCard) 1 else Int.MAX_VALUE,
        overflow = if (isClickableCard) TextOverflow.Ellipsis else TextOverflow.Clip
    )
}

@UiModePreviews.Default
@Composable
private fun PreviewOdsSmallCard(@PreviewParameter(OdsSmallCardPreviewParameterProvider::class) parameter: OdsSmallCardPreviewParameter) = OdsPreview {
    OdsSmallCard(
        modifier = Modifier.width(200.dp),
        title = parameter.title,
        subtitle = parameter.subtitle,
        image = OdsCard.Image(painterResource(id = R.drawable.placeholder), ""),
        onClick = parameter.onClick
    )
}

internal data class OdsSmallCardPreviewParameter(
    val title: String,
    val subtitle: String?,
    val onClick: (() -> Unit)? = {}
)

private class OdsSmallCardPreviewParameterProvider :
    BasicPreviewParameterProvider<OdsSmallCardPreviewParameter>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<OdsSmallCardPreviewParameter>
    get() = listOf(
        OdsSmallCardPreviewParameter(CardPreview.Title, CardPreview.Subtitle),
        OdsSmallCardPreviewParameter(CardPreview.LongTitle, CardPreview.LongSubtitle),
        OdsSmallCardPreviewParameter(CardPreview.LongTitle, CardPreview.LongSubtitle, null),
    )
