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
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
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
 * @param imageRes The drawable resource of the card image.
 * @param imageContentDescription Optional card image content description.
 * @param onCardClick Optional click on the card itself.
 *
 */
@Composable
fun OdsCardSmall(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String? = null,
    @DrawableRes
    imageRes: Int,
    imageContentDescription: String? = null,
    onCardClick: (() -> Unit)? = null,
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
                    .height(dimensionResource(R.dimen.ods_card_small_image_width)),

                )
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .semantics(mergeDescendants = true) {}
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.h6
                )
                subtitle?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.subtitle2
                    )
                }
            }
        }
    }
}