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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import com.orange.ods.R
import com.orange.ods.compose.component.OdsComposable
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.text.OdsTextBodyL
import com.orange.ods.compose.text.OdsTextTitleL
import com.orange.ods.compose.text.OdsTextTitleS

/**
 * <a href="https://system.design.orange.com/0c1af118d/p/272739-cards/b/991690" target="_blank">ODS Card</a>.
 *
 * Cards contain content and actions about a single subject.
 *
 * @param title Title displayed into the card.
 * @param image [OdsCard.Image] displayed into the card.
 * @param modifier [Modifier] applied to the layout of the card.
 * @param subtitle Subtitle displayed into the card.
 * @param text Text displayed into the card.
 * @param firstButton First [OdsCard.Button] displayed into the card.
 * @param secondButton Second [OdsCard.Button] displayed into the card.
 * @param onClick Callback invoked on card click.
 */
@Composable
@OdsComposable
fun OdsVerticalImageFirstCard(
    title: String,
    image: OdsCard.Image,
    modifier: Modifier = Modifier,
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
            image.Content(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(R.dimen.card_big_image_height))
            )
            Column(
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.spacing_m))
            ) {
                OdsTextTitleL(text = title)
                subtitle?.let {
                    OdsTextTitleS(text = it)
                }
                text?.let {
                    OdsTextBodyL(
                        modifier = Modifier.padding(
                            top = dimensionResource(id = R.dimen.spacing_s)
                        ),
                        text = it
                    )
                }
            }
            Row(
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.spacing_s)),
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
private fun PreviewOdsVerticalImageFirstCard() = Preview {
    OdsVerticalImageFirstCard(
        title = "Title",
        subtitle = "Subtitle",
        text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed non risus. Suspendisse lectus tortor, dignissim sit amet, adipiscing nec, ultricies sed, dolor.",
        firstButton = OdsCard.Button("First button") {},
        secondButton = OdsCard.Button("Second button") {},
        image = OdsCard.Image(painterResource(id = R.drawable.placeholder), "")
    )
}
