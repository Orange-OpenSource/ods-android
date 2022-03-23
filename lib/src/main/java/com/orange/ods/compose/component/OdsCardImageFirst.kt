/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.compose.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.orange.ods.R

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
                    .padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 16.dp,
                        bottom = 8.dp
                    )
                    .semantics(mergeDescendants = true) {}
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.subtitle1
                )
                subtitle?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.subtitle2
                    )
                }
                text?.let {
                    Text(
                        modifier = Modifier.padding(
                            top = 8.dp
                        ),
                        text = it,
                        style = MaterialTheme.typography.body1
                    )
                }
            }
            Row(
                modifier = Modifier.padding(start = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                button1Text?.let {
                    TextButton(
                        onClick = { onButton1Click?.invoke() },
                    ) {
                        Text(
                            text = it.uppercase()
                        )
                    }
                }
                button2Text?.let {
                    TextButton(
                        onClick = { onButton2Click?.invoke() },
                    ) {
                        Text(
                            text = it.uppercase()
                        )
                    }
                }
            }
        }
    }
}