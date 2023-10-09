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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.orange.ods.R
import com.orange.ods.compose.component.OdsComposable
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.text.OdsTextBody1
import com.orange.ods.compose.text.OdsTextH6
import com.orange.ods.compose.text.OdsTextSubtitle2
import com.orange.ods.extension.orElse

/**
 * <a href="https://system.design.orange.com/0c1af118d/p/272739-cards/b/991690" target="_blank">ODS Card</a>.
 *
 * Cards contain content and actions about a single subject.
 *
 * @param title Title displayed into the card
 * @param image [OdsCardImage] displayed into the card
 * @param modifier [Modifier] applied to the layout of the card
 * @param thumbnail [OdsCardThumbnail] displayed into the card next to the title: avatar, logo or icon
 * @param subtitle Subtitle displayed into the card
 * @param text Text displayed into the card
 * @param firstButton First [OdsCardButton] displayed into the card
 * @param secondButton Second [OdsCardButton] displayed into the card
 * @param onClick Callback invoked on card click
 */
@Composable
@OdsComposable
fun OdsVerticalHeaderFirstCard(
    title: String,
    image: OdsCardImage,
    modifier: Modifier = Modifier,
    thumbnail: OdsCardThumbnail? = null,
    subtitle: String? = null,
    text: String? = null,
    firstButton: OdsCardButton? = null,
    secondButton: OdsCardButton? = null,
    onClick: (() -> Unit)? = null
) {
    OdsCard(
        modifier = modifier,
        onClick = onClick
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(id = R.dimen.list_two_line_with_icon_item_height))
                    .padding(horizontal = dimensionResource(id = R.dimen.spacing_m)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                thumbnail?.Content()
                Column(modifier = Modifier.padding(start = thumbnail?.let { dimensionResource(id = R.dimen.spacing_s) }.orElse { 0.dp })) {
                    OdsTextH6(text = title)
                    subtitle?.let {
                        OdsTextSubtitle2(text = it)
                    }
                }
            }
            image.Content(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(R.dimen.card_big_image_height))
            )

            text?.let {
                OdsTextBody1(
                    modifier = Modifier
                        .padding(top = dimensionResource(id = R.dimen.spacing_m))
                        .padding(horizontal = dimensionResource(id = R.dimen.spacing_m)),
                    text = it
                )
            }

            if (firstButton != null || secondButton != null || text != null) {
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing_m)))
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = dimensionResource(id = R.dimen.spacing_s)),
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacing_s))
            ) {
                firstButton?.Content()
                secondButton?.Content()
            }
        }
    }
}

@UiModePreviews.Default
@Composable
private fun PreviewOdsVerticalHeaderFirstCard() = Preview {
    OdsVerticalHeaderFirstCard(
        title = "Title",
        image = OdsCardImage(painterResource(id = R.drawable.placeholder), ""),
        thumbnail = OdsCardThumbnail(painterResource(id = R.drawable.placeholder_small), ""),
        subtitle = "Subtitle",
        text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed non risus. Suspendisse lectus tortor, dignissim sit amet, adipiscing nec, ultricies sed, dolor.",
        firstButton = OdsCardButton("First button") {},
        secondButton = OdsCardButton("Second button") {}
    )
}
