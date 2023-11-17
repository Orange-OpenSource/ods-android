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
import com.orange.ods.compose.text.OdsTextBody1
import com.orange.ods.compose.text.OdsTextH6
import com.orange.ods.compose.text.OdsTextSubtitle2

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
                OdsTextH6(text = title)
                subtitle?.let {
                    OdsTextSubtitle2(text = it)
                }
                text?.let {
                    OdsTextBody1(
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
