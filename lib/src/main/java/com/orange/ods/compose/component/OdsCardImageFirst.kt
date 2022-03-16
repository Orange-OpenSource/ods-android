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