/*
 * Software Name: Orange Design System
 * SPDX-FileCopyrightText: Copyright (c) Orange SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT license,
 * the text of which is available at https://opensource.org/license/MIT/
 * or see the "LICENSE" file for more details.
 *
 * Software description: Android library of reusable graphical components
 */

package com.orange.ods.compose.component.banner

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.orange.ods.R
import com.orange.ods.compose.component.OdsComposable
import com.orange.ods.compose.component.button.OdsTextButton
import com.orange.ods.compose.component.content.OdsComponentCircularImage
import com.orange.ods.compose.component.content.OdsComponentContent
import com.orange.ods.compose.component.divider.OdsDivider
import com.orange.ods.compose.component.utilities.BasicPreviewParameterProvider
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.text.OdsText
import com.orange.ods.compose.theme.OdsTheme
import com.orange.ods.theme.typography.OdsTextStyle

/**
 * <a href="https://system.design.orange.com/0c1af118d/p/19a040-banners/b/497b77" class="external" target="_blank">ODS banners</a>.
 *
 * @param message Text displayed into the banner.
 * @param modifier [Modifier] applied to the banner layout.
 * @param image Image displayed in the banner in a circle shape.
 * @param firstButton Primary button displayed in the banner.
 * @param secondButton Secondary button displayed into the banner next to the primary one.
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
@OdsComposable
fun OdsBanner(
    message: String,
    modifier: Modifier = Modifier,
    image: OdsBanner.Image? = null,
    firstButton: OdsBanner.Button? = null,
    secondButton: OdsBanner.Button? = null
) {
    val buttonCount = when {
        firstButton == null && secondButton == null -> 0
        firstButton != null && secondButton != null -> 2
        else -> 1
    }
    var hasVisualOverflowText by remember(message) { mutableStateOf(false) }
    val isSingleLineBanner = image == null && buttonCount != 2 && !hasVisualOverflowText

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = OdsTheme.colors.surface)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = if (buttonCount == 0 || !isSingleLineBanner) dimensionResource(id = R.dimen.spacing_m) else dimensionResource(id = R.dimen.spacing_s),
                    bottom = when {
                        buttonCount == 0 -> dimensionResource(id = R.dimen.spacing_m)
                        isSingleLineBanner -> dimensionResource(id = R.dimen.spacing_s)
                        else -> 0.dp
                    }
                )
                .padding(horizontal = dimensionResource(id = R.dimen.spacing_m))
        ) {
            image?.Content(modifier = Modifier.padding(end = dimensionResource(id = R.dimen.spacing_m)))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                OdsText(
                    modifier = Modifier.weight(1f),
                    text = message,
                    style = OdsTextStyle.BodyM,
                    color = OdsTheme.colors.onSurface,
                    maxLines = if (hasVisualOverflowText) Int.MAX_VALUE else 2,
                    overflow = TextOverflow.Ellipsis,
                    onTextLayout = { textLayoutResult ->
                        if (textLayoutResult.hasVisualOverflow) {
                            hasVisualOverflowText = true
                        }
                    }
                )
                if (isSingleLineBanner) {
                    firstButton?.Content(modifier = Modifier.padding(start = dimensionResource(id = R.dimen.spacing_xs)))
                    secondButton?.Content(modifier = Modifier.padding(start = dimensionResource(id = R.dimen.spacing_xs)))
                }
            }
        }
        if (!isSingleLineBanner && buttonCount > 0) {
            FlowRow(
                modifier = Modifier
                    .padding(horizontal = dimensionResource(id = R.dimen.spacing_m))
                    .padding(bottom = dimensionResource(id = R.dimen.spacing_xs))
                    .align(Alignment.End),
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacing_s), Alignment.End),
                verticalArrangement = Arrangement.spacedBy((-6).dp)
            ) {
                firstButton?.Content()
                secondButton?.Content()
            }
        }
        OdsDivider()
    }
}

/**
 * Contains classes to build an [com.orange.ods.compose.component.banner.OdsBanner].
 */
object OdsBanner {

    /**
     * A button in an OdsBanner.
     *
     * @constructor Creates an instance of [OdsBanner.Button].
     * @param text Text of the button.
     * @param onClick Will be called when the user clicks the button.
     */
    class Button(private val text: String, private val onClick: () -> Unit) : OdsComponentContent<Nothing>() {

        @Composable
        override fun Content(modifier: Modifier) {
            OdsTextButton(text = text, onClick = onClick, modifier = modifier, style = OdsTextButton.Style.Primary)
        }
    }

    /**
     * An image in an OdsBanner.
     */
    class Image : OdsComponentCircularImage {

        /**
         * Creates an instance of [OdsBanner.Image].
         *
         * @param painter The painter to draw.
         * @param contentDescription The content description associated to this [OdsBanner.Image].
         */
        constructor(painter: Painter, contentDescription: String) : super(painter, contentDescription)

        /**
         * Creates an instance of [OdsBanner.Image].
         *
         * @param imageVector The image vector to draw.
         * @param contentDescription The content description associated to this [OdsBanner.Image].
         */
        constructor(imageVector: ImageVector, contentDescription: String) : super(imageVector, contentDescription)

        /**
         * Creates an instance of [OdsBanner.Image].
         *
         * @param bitmap The image bitmap to draw.
         * @param contentDescription The content description associated to this [OdsBanner.Image].
         */
        constructor(bitmap: ImageBitmap, contentDescription: String) : super(bitmap, contentDescription)
    }
}

@UiModePreviews.Default
@Composable
private fun PreviewOdsBanner(@PreviewParameter(OdsBannerPreviewParameterProvider::class) parameter: OdsBannerPreviewParameter) =
    Preview {
        with(parameter) {
            OdsBanner(
                message = message,
                firstButton = firstButtonText?.let { OdsBanner.Button(it) {} },
                image = imageRes?.let { OdsBanner.Image(painterResource(id = it), "") },
                secondButton = secondButtonText?.let { OdsBanner.Button(it) {} },
            )
        }
    }

private data class OdsBannerPreviewParameter(
    val message: String,
    val firstButtonText: String? = null,
    val secondButtonText: String? = null,
    val imageRes: Int? = null
)

private class OdsBannerPreviewParameterProvider :
    BasicPreviewParameterProvider<OdsBannerPreviewParameter>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<OdsBannerPreviewParameter>
    get() {
        val imageRes = R.drawable.placeholder
        val shortMessage = "Here is a short banner message."
        val longMessage = "Here is a long banner message. One to two lines is preferable on mobile and tablet."
        val firstButtonText = "ACTION 1"
        val firstButtonLongText = "ACTION with a very long label"
        val secondButtonText = "ACTION 2"

        return listOf(
            OdsBannerPreviewParameter(longMessage, firstButtonText, secondButtonText, imageRes),
            OdsBannerPreviewParameter(longMessage, firstButtonLongText, secondButtonText, imageRes),
            OdsBannerPreviewParameter(shortMessage),
            OdsBannerPreviewParameter(shortMessage, firstButtonText),
            OdsBannerPreviewParameter(shortMessage, secondButtonText = secondButtonText),
            OdsBannerPreviewParameter(longMessage, firstButtonText, secondButtonText),
            OdsBannerPreviewParameter(longMessage, firstButtonText),
            OdsBannerPreviewParameter(shortMessage, firstButtonText, imageRes = imageRes),
            OdsBannerPreviewParameter(shortMessage, secondButtonText = secondButtonText, imageRes = imageRes)
        )
    }