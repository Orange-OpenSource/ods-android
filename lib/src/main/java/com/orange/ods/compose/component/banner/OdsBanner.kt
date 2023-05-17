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
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import com.orange.ods.compose.component.OdsComposable
import com.orange.ods.compose.component.button.OdsTextButton
import com.orange.ods.compose.component.button.OdsTextButtonStyle
import com.orange.ods.compose.component.divider.OdsDivider
import com.orange.ods.compose.component.utilities.BasicPreviewParameterProvider
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.theme.OdsTheme

/**
 * <a href="https://system.design.orange.com/0c1af118d/p/19a040-banners/b/497b77" class="external" target="_blank">ODS banners</a>.
 *
 *
 * @param message text displayed in the banner.
 * @param button1Text principal button in the banner, it displays an [OdsTextButton] with the given [button1Text] as an action of the banner.
 * @param onButton1Click executed on button1 click.
 * @param modifier modifiers for the Banner layout.
 * @param image image display in the banner.
 * @param imageContentDescription Optional image content description.
 * @param button2Text Optional text of the second button in the banner. If not present, button will not be shown. If present, [onButton2Click] need to be  handle.
 * @param onButton2Click Optional handler for the button2 click.
 */
@Composable
@OdsComposable
fun OdsBanner(
    message: String,
    button1Text: String,
    onButton1Click: () -> Unit,
    modifier: Modifier = Modifier,
    image: Painter? = null,
    imageContentDescription: String? = null,
    button2Text: String? = null,
    onButton2Click: (() -> Unit)? = null
) {
    val isSingleLineBanner = image == null && button2Text == null
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = if (isSingleLineBanner) dimensionResource(id = R.dimen.spacing_s) else dimensionResource(id = R.dimen.spacing_l),
                    bottom = dimensionResource(id = R.dimen.spacing_s)
                )
                .padding(horizontal = dimensionResource(id = R.dimen.spacing_m))
        ) {
            image?.let {
                Image(
                    painter = image,
                    contentDescription = imageContentDescription,
                    contentScale = ContentScale.Crop, // crop the image if it's not a square
                    modifier = Modifier
                        .padding(end = dimensionResource(id = R.dimen.spacing_m))
                        .size(40.dp)
                        .clip(CircleShape),
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = message,
                    style = OdsTheme.typography.body2,
                    color = OdsTheme.colors.onSurface,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                if (isSingleLineBanner) {
                    OdsTextButton(
                        modifier = Modifier
                            .padding(start = dimensionResource(id = R.dimen.spacing_s)),
                        style = OdsTextButtonStyle.Primary,
                        text = button1Text,
                        onClick = onButton1Click
                    )
                }
            }
        }
        if (!isSingleLineBanner) {
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
                button2Text?.let {
                    OdsTextButton(
                        modifier = Modifier.padding(end = dimensionResource(id = R.dimen.spacing_s)),
                        style = OdsTextButtonStyle.Primary,
                        text = button2Text,
                        onClick = { onButton2Click?.invoke() }
                    )
                }
            }
        }
        OdsDivider()
    }
}

@UiModePreviews.Default
@Composable
private fun PreviewOdsBanner(@PreviewParameter(OdsBannerPreviewParameterProvider::class) parameter: OdsBannerPreviewParameter) =
    Preview {
        with(parameter) {
            OdsBanner(
                message = message,
                button1Text = button1Text,
                onButton1Click = {},
                button2Text = button2Text,
                image = imageRes?.let { painterResource(id = it) },
            )
        }
    }

private data class OdsBannerPreviewParameter(
    val message: String,
    val button1Text: String,
    val button2Text: String? = null,
    val imageRes: Int? = null
)

private class OdsBannerPreviewParameterProvider :
    BasicPreviewParameterProvider<OdsBannerPreviewParameter>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<OdsBannerPreviewParameter>
    get() {
        val imageRes = R.drawable.placeholder
        val shortMessage = "Two lines text string with two actions."
        val longMessage = "Two lines text string with two actions. One to two lines is preferable on mobile and tablet."
        val button1Text = "ACTION"
        val button2Text = "ACTION"

        return listOf(
            OdsBannerPreviewParameter(longMessage, button1Text, button2Text, imageRes),
            OdsBannerPreviewParameter(shortMessage, button1Text),
            OdsBannerPreviewParameter(longMessage, button1Text, button2Text),
            OdsBannerPreviewParameter(longMessage, button1Text),
            OdsBannerPreviewParameter(shortMessage, button1Text, imageRes = imageRes)
        )
    }