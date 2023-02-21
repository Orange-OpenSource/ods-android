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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.orange.ods.R
import com.orange.ods.compose.component.OdsComponentApi
import com.orange.ods.compose.component.button.OdsTextButton
import com.orange.ods.compose.component.button.OdsTextButtonStyle
import com.orange.ods.compose.component.utilities.BasicPreviewParameterProvider
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.text.OdsTextBody2

/**
 * <a href="https://system.design.orange.com/0c1af118d/p/19a040-banners/b/497b77" class="external" target="_blank">ODS banners</a>.
 *
 *
 * @param message text displayed in the banner
 * @param modifier modifiers for the Banner layout
 * @param image image display in the banner
 * @param actionLabel if set, it displays an [OdsTextButton] with the given [actionLabel] as an action of the banner.
 * @param actionOnNewLine whether or not action should be put on the separate line. Recommended
 * for action with long action text
 * @param onActionClick executed on action button click.
 */
@Composable
@OdsComponentApi
fun OdsBanner(
    modifier: Modifier = Modifier,
    message: String,
    image: Painter? = null,
    iconContentDescription: String? = null,
    actionOnNewLine: Boolean = false,
    actionLabel: String,
    onActionClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                top = dimensionResource(id = R.dimen.spacing_s),
                start = dimensionResource(id = R.dimen.spacing_s),
                end = dimensionResource(id = R.dimen.spacing_s)
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            image?.let {
                Image(
                    painter = image,
                    contentDescription = iconContentDescription,
                    contentScale = ContentScale.Crop,// crop the image if it's not a square
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                )
            }
            OdsTextBody2(
                modifier = Modifier
                    .padding(
                        start = dimensionResource(id = R.dimen.spacing_s),
                        top = dimensionResource(id = R.dimen.spacing_s),
                        bottom = dimensionResource(id = R.dimen.spacing_s)
                    ),
                text = message
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
                        text = actionLabel.uppercase(),
                        onClick = onActionClick
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
                    text = actionLabel,
                    onClick = onActionClick
                )
                OdsTextButton(
                    modifier = Modifier.padding(end = dimensionResource(id = R.dimen.spacing_s)),
                    style = OdsTextButtonStyle.Primary,
                    text = actionLabel,
                    onClick = onActionClick
                )
            }
        }
        Divider()
    }
}

@UiModePreviews.Default
@Composable
private fun PreviewOdsBanner(@PreviewParameter(OdsBannerPreviewParameterProvider::class) actionOnNewLine: Boolean) = Preview {
    OdsBanner(
        message = "Two lines text string with two actions.", modifier = Modifier,
        actionLabel = "Action",
        actionOnNewLine = actionOnNewLine
    )
}

private class OdsBannerPreviewParameterProvider : BasicPreviewParameterProvider<Boolean>(false, true)