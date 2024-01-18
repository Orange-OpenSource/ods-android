/*
 * Software Name: Orange Design System
 * SPDX-FileCopyrightText: Copyright (c) Orange SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT licence,
 * the text of which is available at https://opensource.org/license/MIT/
 * or see the "LICENSE" file for more details.
 *
 * Software description: Android library of reusable graphical components
 */

package com.orange.ods.compose.component.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
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
import com.orange.ods.compose.text.OdsTextBodyL
import com.orange.ods.compose.text.OdsTextTitleL
import com.orange.ods.compose.text.OdsTextTitleS
import com.orange.ods.extension.orElse

/**
 * <a href="https://system.design.orange.com/0c1af118d/p/272739-cards/b/991690" target="_blank">ODS Card</a>.
 *
 * Cards contain content and actions about a single subject.
 *
 * @param title Title displayed into the card.
 * @param image [OdsCard.Image] displayed into the card.
 * @param modifier [Modifier] applied to the layout of the card.
 * @param thumbnail [OdsCard.Thumbnail] displayed into the card next to the title: avatar, logo or icon.
 * @param subtitle Subtitle displayed into the card.
 * @param text Text displayed into the card.
 * @param firstButton First [OdsCard.Button] displayed into the card.
 * @param secondButton Second [OdsCard.Button] displayed into the card.
 * @param onClick Callback invoked on card click.
 */
@Composable
@OdsComposable
fun OdsVerticalHeaderFirstCard(
    title: String,
    image: OdsCard.Image,
    modifier: Modifier = Modifier,
    thumbnail: OdsCard.Thumbnail? = null,
    subtitle: String? = null,
    text: String? = null,
    firstButton: OdsCard.Button? = null,
    secondButton: OdsCard.Button? = null,
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
                    .height(IntrinsicSize.Min)
                    .padding(all = dimensionResource(id = R.dimen.spacing_m)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                thumbnail?.Content()
                Column(modifier = Modifier.padding(start = thumbnail?.let { dimensionResource(id = R.dimen.spacing_s) }.orElse { 0.dp })) {
                    OdsTextTitleL(text = title)
                    subtitle?.let {
                        OdsTextTitleS(text = it)
                    }
                }
            }
            image.Content(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(R.dimen.card_big_image_height))
            )

            text?.let {
                OdsTextBodyL(
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
        image = OdsCard.Image(painterResource(id = R.drawable.placeholder), ""),
        thumbnail = OdsCard.Thumbnail(painterResource(id = R.drawable.placeholder_small), ""),
        subtitle = "Subtitle",
        text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed non risus. Suspendisse lectus tortor, dignissim sit amet, adipiscing nec, ultricies sed, dolor.",
        firstButton = OdsCard.Button("First button") {},
        secondButton = OdsCard.Button("Second button") {}
    )
}
