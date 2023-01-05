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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import com.orange.ods.R
import com.orange.ods.compose.component.OdsComponentApi
import com.orange.ods.compose.component.button.OdsTextButton
import com.orange.ods.compose.component.button.OdsTextButtonStyle
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
 * @param title The title to be displayed in the card.
 * @param image The painter of the card image.
 * @param modifier Modifier to be applied to the layout of the card.
 * @param subtitle Optional subtitle to be displayed in the card.
 * @param text Optional text description to be displayed in the card.
 * @param button1Text Optional text of the first button in the card. If not present, button will not be shown. If present, [onButton1Click] need to be  handle.
 * @param button2Text Optional text of the second button in the card. If not present, button will not be shown. If present, [onButton2Click] need to be  handle.
 * @param imageContentDescription Optional card image content description.
 * @param imageBackgroundColor Optional background color of the card image.
 * @param imageContentScale The content scale of the card image.
 * @param imageAlignment The alignment of the card image.
 * @param onCardClick Optional click on the card itself.
 * @param onButton1Click Optional handler for the first button click.
 * @param onButton2Click Optional handler for the second button click.
 *
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
@OdsComponentApi
fun OdsImageFirstCard(
    title: String,
    image: Painter,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    text: String? = null,
    button1Text: String? = null,
    button2Text: String? = null,
    imageContentDescription: String? = null,
    imageBackgroundColor: Color? = null,
    imageContentScale: ContentScale = ContentScale.Crop,
    imageAlignment: Alignment = Alignment.Center,
    onCardClick: (() -> Unit)? = null,
    onButton1Click: (() -> Unit)? = null,
    onButton2Click: (() -> Unit)? = null
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
                    .height(dimensionResource(R.dimen.card_big_image_width))
                    .let {
                        if (imageBackgroundColor != null) it.background(imageBackgroundColor) else it
                    },
                alignment = imageAlignment
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
                button1Text?.let {
                    OdsTextButton(
                        text = it,
                        onClick = { onButton1Click?.invoke() },
                        style = OdsTextButtonStyle.Primary
                    )
                }
                button2Text?.let {
                    OdsTextButton(
                        text = it,
                        onClick = { onButton2Click?.invoke() },
                        style = OdsTextButtonStyle.Primary
                    )
                }
            }
        }
    }
}

@UiModePreviews.Default
@Composable
private fun PreviewOdsImageFirstCard() = Preview {
    OdsImageFirstCard(
        title = "Title",
        subtitle = "Subtitle",
        text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed non risus. Suspendisse lectus tortor, dignissim sit amet, adipiscing nec, ultricies sed, dolor.",
        button1Text = "Button 1",
        button2Text = "Button 2",
        image = painterResource(id = R.drawable.placeholder)
    )
}
