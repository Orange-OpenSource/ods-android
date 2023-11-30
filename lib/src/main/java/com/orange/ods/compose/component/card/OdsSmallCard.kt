/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.compose.component.card

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.orange.ods.R
import com.orange.ods.compose.component.OdsComposable
import com.orange.ods.compose.component.utilities.BasicPreviewParameterProvider
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.theme.OdsTheme

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
        Column {
            image.Content(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(R.dimen.card_small_image_width))
            )
            Column(
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.spacing_m))
            ) {
                SmallCardText(text = title, style = OdsTheme.typography.h6, isClickableCard = onClick != null)
                subtitle?.let {
                    SmallCardText(text = it, style = OdsTheme.typography.subtitle2, isClickableCard = onClick != null)
                }
            }
        }
    }
}

@Composable
private fun SmallCardText(text: String, style: TextStyle, isClickableCard: Boolean) {
    Text(
        text = text,
        style = style,
        maxLines = if (isClickableCard) 1 else Int.MAX_VALUE,
        overflow = if (isClickableCard) TextOverflow.Ellipsis else TextOverflow.Clip
    )
}

@UiModePreviews.Default
@Composable
private fun PreviewOdsSmallCard(@PreviewParameter(OdsSmallCardPreviewParameterProvider::class) parameter: OdsSmallCardPreviewParameter) = Preview {
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
    get() {
        val shortTitle = "Title"
        val longTitle = "Here is a long title that don't fit"
        val shortSubtitle = "Subtitle"
        val longSubtitle = "Here is a very very very long subtitle"

        return listOf(
            OdsSmallCardPreviewParameter(shortTitle, shortSubtitle),
            OdsSmallCardPreviewParameter(longTitle, longSubtitle),
            OdsSmallCardPreviewParameter(longTitle, longSubtitle, null),
        )
    }