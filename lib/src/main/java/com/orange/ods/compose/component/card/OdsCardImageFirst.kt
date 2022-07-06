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

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.semantics
import com.orange.ods.R
import com.orange.ods.compose.component.button.OdsButtonText
import com.orange.ods.compose.component.button.OdsButtonTextStyle
import com.orange.ods.compose.text.OdsTextBody1
import com.orange.ods.compose.text.OdsTextH6
import com.orange.ods.compose.text.OdsTextSubtitle2

/**
 * <a href="https://system.design.orange.com/0c1af118d/p/272739-cards/b/991690" target="_blank">ODS Card</a>.
 *
 * Cards contain content and actions about a single subject.
 *
 * @param modifier Modifier to be applied to the layout of the card.
 * @param title The title to be displayed in the card.
 * @param subtitle Optional subtitle to be displayed in the card.
 * @param text Optional text description to be displayed in the card.
 * @param button1Text Optional text of the first button in the card. If not present, button will not be shown. If present, [onButton1Click] need to be  handle.
 * @param button2Text Optional text of the second button in the card. If not present, button will not be shown. If present, [onButton2Click] need to be  handle.
 * @param imageRes The drawable resource of the card image.
 * @param imageContentDescription Optional card image content description.
 * @param onCardClick Optional click on the card itself.
 * @param onButton1Click Optional handler for the first button click.
 * @param onButton2Click Optional handler for the second button click.
 *
 */
@Composable
fun OdsCardImageFirst(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String? = null,
    text: String? = null,
    button1Text: String? = null,
    button2Text: String? = null,
    @DrawableRes
    imageRes: Int,
    imageContentDescription: String? = null,
    onCardClick: (() -> Unit)? = null,
    onButton1Click: (() -> Unit)? = null,
    onButton2Click: (() -> Unit)? = null
) {
    Card(
        modifier = modifier.clickable {
            onCardClick?.invoke()
        }
    ) {
        Column {
            Image(
                painter = painterResource(imageRes),
                contentDescription = imageContentDescription,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(R.dimen.ods_card_big_image_width)),

                )
            Column(
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.spacing_m))
                    .semantics(mergeDescendants = true) {}
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
                button1Text?.let {
                    OdsButtonText(
                        text = it,
                        onClick = { onButton1Click?.invoke() },
                        style = OdsButtonTextStyle.Primary
                    )
                }
                button2Text?.let {
                    OdsButtonText(
                        text = it,
                        onClick = { onButton2Click?.invoke() },
                        style = OdsButtonTextStyle.Primary
                    )
                }
            }
        }
    }
}