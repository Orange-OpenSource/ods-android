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
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
 * @param title Title displayed into the card.
 * @param image [OdsCardImageBuilder] displayed into the card.
 * @param modifier [Modifier] applied to the layout of the card.
 * @param subtitle Subtitle displayed into the card.
 * @param onClick Callback invoked on card click.
 */
@Composable
@OdsComposable
fun OdsSmallCard(
    title: String,
    image: OdsCardImageBuilder,
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
        image = OdsCardImageBuilder(painterResource(id = R.drawable.placeholder), "")
    )
}
