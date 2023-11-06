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

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.orange.ods.R
import com.orange.ods.compose.component.OdsComposable
import com.orange.ods.compose.component.button.OdsTextButton
import com.orange.ods.compose.component.button.OdsTextButtonStyle
import com.orange.ods.compose.component.content.OdsComponentCircularImage
import com.orange.ods.compose.component.content.OdsComponentContent
import com.orange.ods.compose.component.divider.OdsDivider
import com.orange.ods.compose.component.utilities.BasicPreviewParameterProvider
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.theme.OdsTheme

/**
 * <a href="https://system.design.orange.com/0c1af118d/p/19a040-banners/b/497b77" class="external" target="_blank">ODS banners</a>.
 *
 * @param message Text displayed into the banner.
 * @param firstButton Primary button displayed in the banner.
 * @param modifier [Modifier] applied to the banner layout.
 * @param image Image displayed in the banner in a circle shape.
 * @param secondButton Secondary button displayed into the banner next to the primary one.
 */
@Composable
@OdsComposable
fun OdsBanner(
    message: String,
    firstButton: OdsBannerButton,
    modifier: Modifier = Modifier,
    image: OdsBannerImage? = null,
    secondButton: OdsBannerButton? = null
) {
    val isSingleLineBanner = image == null && secondButton == null
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
                    top = if (isSingleLineBanner) dimensionResource(id = R.dimen.spacing_s) else dimensionResource(id = R.dimen.spacing_l),
                    bottom = dimensionResource(id = R.dimen.spacing_s)
                )
                .padding(horizontal = dimensionResource(id = R.dimen.spacing_m))
        ) {
            image?.Content(modifier = Modifier.padding(end = dimensionResource(id = R.dimen.spacing_m)))
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
                    firstButton.Content(modifier = Modifier.padding(start = dimensionResource(id = R.dimen.spacing_s)))
                }
            }
        }
        if (!isSingleLineBanner) {
            Row(
                modifier = Modifier
                    .padding(bottom = dimensionResource(id = R.dimen.spacing_s))
                    .align(Alignment.End)
            ) {
                firstButton.Content(modifier = Modifier.padding(end = dimensionResource(id = R.dimen.spacing_s)))
                secondButton?.Content(modifier = Modifier.padding(end = dimensionResource(id = R.dimen.spacing_s)))
            }
        }
        OdsDivider()
    }
}

/**
 * A button in an [OdsBanner].
 *
 * @constructor Creates an instance of [OdsBannerButton].
 * @param text Text of the button.
 * @param onClick Will be called when the user clicks the button.
 */
class OdsBannerButton(private val text: String, private val onClick: () -> Unit) : OdsComponentContent<Nothing>() {

    @Composable
    override fun Content(modifier: Modifier) {
        OdsTextButton(text = text, onClick = onClick, modifier = modifier, style = OdsTextButtonStyle.Primary)
    }
}

/**
 * An image in an [OdsBanner].
 */
class OdsBannerImage : OdsComponentCircularImage {

    /**
     * Creates an instance of [OdsBannerImage].
     *
     * @param painter The painter to draw.
     * @param contentDescription The content description associated to this [OdsBannerImage].
     */
    constructor(painter: Painter, contentDescription: String) : super(painter, contentDescription)

    /**
     * Creates an instance of [OdsBannerImage].
     *
     * @param imageVector The image vector to draw.
     * @param contentDescription The content description associated to this [OdsBannerImage].
     */
    constructor(imageVector: ImageVector, contentDescription: String) : super(imageVector, contentDescription)

    /**
     * Creates an instance of [OdsBannerImage].
     *
     * @param bitmap The image bitmap to draw.
     * @param contentDescription The content description associated to this [OdsBannerImage].
     */
    constructor(bitmap: ImageBitmap, contentDescription: String) : super(bitmap, contentDescription)
}

@UiModePreviews.Default
@Composable
private fun PreviewOdsBanner(@PreviewParameter(OdsBannerPreviewParameterProvider::class) parameter: OdsBannerPreviewParameter) =
    Preview {
        with(parameter) {
            OdsBanner(
                message = message,
                firstButton = OdsBannerButton(firstButtonText) {},
                image = imageRes?.let { OdsBannerImage(painterResource(id = it), "") },
                secondButton = secondButtonText?.let { OdsBannerButton(it) {} },
            )
        }
    }

private data class OdsBannerPreviewParameter(
    val message: String,
    val firstButtonText: String,
    val secondButtonText: String? = null,
    val imageRes: Int? = null
)

private class OdsBannerPreviewParameterProvider :
    BasicPreviewParameterProvider<OdsBannerPreviewParameter>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<OdsBannerPreviewParameter>
    get() {
        val imageRes = R.drawable.placeholder
        val shortMessage = "Two lines text string with two actions."
        val longMessage = "Two lines text string with two actions. One to two lines is preferable on mobile and tablet."
        val firstButtonText = "ACTION"
        val secondButtonText = "ACTION"

        return listOf(
            OdsBannerPreviewParameter(longMessage, firstButtonText, secondButtonText, imageRes),
            OdsBannerPreviewParameter(shortMessage, firstButtonText),
            OdsBannerPreviewParameter(longMessage, firstButtonText, secondButtonText),
            OdsBannerPreviewParameter(longMessage, firstButtonText),
            OdsBannerPreviewParameter(shortMessage, firstButtonText, imageRes = imageRes)
        )
    }