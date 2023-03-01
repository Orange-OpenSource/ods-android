/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.compose.component.banner

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.orange.ods.R
import com.orange.ods.compose.component.OdsComponentApi
import com.orange.ods.compose.component.button.OdsTextButton
import com.orange.ods.compose.component.button.OdsTextButtonStyle
import com.orange.ods.compose.component.utilities.BasicPreviewParameterProvider
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.theme.OdsTheme
import com.orange.ods.theme.OdsColors

/**
 * <a href="https://system.design.orange.com/0c1af118d/p/19a040-banners/b/497b77" class="external" target="_blank">ODS banners</a>.
 *
 *
 * @param modifier modifiers for the Banner layout.
 * @param message text displayed in the banner.
 * @param image image display in the banner.
 * @param imageContentDescription Optional image content description.
 * @param actionOnNewLine whether or not action should be put on the separate line.
 * @param divider add the line at the end of the banner.
 * @param buttonText Optional text of the second button in the banner. If not present, button will not be shown. If present, [onButton2Click] need to be  handle.
 * @param button1Text principal button in the banner, it displays an [OdsTextButton] with the given [button1Text] as an action of the banner.
 * @param onButtonClick Optional handler for the button click.
 * @param onButton1Click executed on action button2 click.
 */
@Composable
@OdsComponentApi
fun OdsBanner(
    modifier: Modifier = Modifier,
    message: String,
    image: Painter? = null,
    imageContentDescription: String? = null,
    actionOnNewLine: Boolean = false,
    divider: Boolean = true,
    buttonText: String? = null,
    button1Text: String,
    onButtonClick: (() -> Unit)? = null,
    onButton1Click: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
                .padding(top = dimensionResource(id = R.dimen.spacing_m),
                    bottom = dimensionResource(id = R.dimen.spacing_s)),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            if (!actionOnNewLine) {
                image?.let {
                    Image(
                        painter = image,
                        contentDescription = imageContentDescription,
                        contentScale = ContentScale.Crop,// crop the image if it's not a square
                        modifier = Modifier
                            .padding(start = dimensionResource(id = R.dimen.spacing_m))
                            .size(40.dp)
                            .clip(CircleShape)
                    )
                }
            }
            Text(
                modifier = Modifier
                    .padding(
                        start = dimensionResource(id = R.dimen.spacing_m),
                        end = dimensionResource(id = R.dimen.spacing_s)
                    ),
                text = message,
                style = OdsTheme.typography.body2,
                color = OdsTheme.colors.onSurface,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            if (actionOnNewLine) {
                Row(
                    modifier = Modifier
                        .width(250.dp),
                    horizontalArrangement = Arrangement.End,
                ) {
                    OdsTextButton(
                        modifier = Modifier.padding(end = dimensionResource(id = R.dimen.spacing_s)),
                        style = OdsTextButtonStyle.Primary,
                        text = button1Text.uppercase(),
                        onClick = onButton1Click
                    )
                }
            }
        }
        if (!actionOnNewLine) {
            Row(
                modifier = Modifier
                    .padding(bottom = dimensionResource(id = R.dimen.spacing_s))
                    .align(Alignment.End)
            ) {
                OdsTextButton(
                    modifier = Modifier.padding(end = dimensionResource(id = R.dimen.spacing_s)),
                    style = OdsTextButtonStyle.Primary,
                    text = button1Text,
                    onClick = onButton1Click
                )
                buttonText?.let {
                    OdsTextButton(
                        modifier = Modifier.padding(end = dimensionResource(id = R.dimen.spacing_s)),
                        style = OdsTextButtonStyle.Primary,
                        text = buttonText,
                        onClick = { onButtonClick?.invoke() }
                    )
                }
            }
        }
        if (divider) Divider()
    }
}

@UiModePreviews.Default
@Composable
private fun PreviewOdsBanner(@PreviewParameter(OdsBannerPreviewParameterProvider::class) actionOnNewLine: Boolean) = Preview {
    OdsBanner(
        message = "Two lines text string with two actions.", modifier = Modifier,
        button1Text = "Action",
        image = painterResource(id = R.drawable.placeholder),
        actionOnNewLine = actionOnNewLine
    )
}

private class OdsBannerPreviewParameterProvider : BasicPreviewParameterProvider<Boolean>(false, true)