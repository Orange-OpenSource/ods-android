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

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import com.orange.ods.R
import com.orange.ods.compose.component.OdsComposable
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.theme.OdsTheme

/**
 * <a href="https://system.design.orange.com/0c1af118d/p/272739-cards/b/991690" target="_blank">ODS Card</a>.
 *
 * Cards contain content and actions about a single subject.
 *
 * @param title The title to be displayed in the card.
 * @param image The painter of the card image.
 * @param modifier Modifier to be applied to the layout of the card.
 * @param subtitle Optional subtitle to be displayed in the card.
 * @param imageContentDescription Optional card image content description.
 * @param imageBackgroundColor Optional background color of the card image.
 * @param imageContentScale The content scale of the card image.
 * @param imageAlignment The alignment of the card image.
 * @param onCardClick Optional click on the card itself.
 *
 */
@Composable
@OdsComposable
fun OdsSmallCard(
    title: String,
    image: Painter,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    imageContentDescription: String? = null,
    imageBackgroundColor: Color? = null,
    imageContentScale: ContentScale = ContentScale.Crop,
    imageAlignment: Alignment = Alignment.Center,
    onCardClick: (() -> Unit)? = null
) {
    OdsCard(
        modifier = modifier,
        onClick = onCardClick
    ) {
        Column {
            Image(
                painter = image,
                contentDescription = imageContentDescription,
                contentScale = imageContentScale,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(R.dimen.card_small_image_width))
                    .let {
                        if (imageBackgroundColor != null) it.background(imageBackgroundColor) else it
                    },
                alignment = imageAlignment
            )
            Column(
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.spacing_m))
            ) {
                Text(
                    text = title,
                    style = OdsTheme.typography.h6,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                subtitle?.let {
                    Text(
                        text = it,
                        style = OdsTheme.typography.subtitle2
                    )
                }
            }
        }
    }
}

@UiModePreviews.Default
@Composable
private fun PreviewOdsSmallCard() = Preview {
    OdsSmallCard(
        title = "Title",
        subtitle = "Subtitle",
        image = painterResource(id = R.drawable.placeholder)
    )
}
