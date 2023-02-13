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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.orange.ods.R
import com.orange.ods.compose.component.OdsComponentApi
import com.orange.ods.compose.component.button.OdsTextButton
import com.orange.ods.compose.component.button.OdsTextButtonStyle
import com.orange.ods.compose.component.divider.OdsDivider
import com.orange.ods.compose.component.utilities.BasicPreviewParameterProvider
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.text.OdsTextH6
import com.orange.ods.compose.text.OdsTextSubtitle2
import com.orange.ods.compose.theme.OdsTheme

/**
 * <a href="https://system.design.orange.com/0c1af118d/p/272739-cards/b/991690" target="_blank">ODS Card</a>.
 *
 * Cards contain content and actions about a single subject.
 *
 * @param title The title to be displayed in the card.
 * @param image The painter of the card image.
 * @param modifier Modifier to be applied to the layout of the card.
 * @param subtitle Optional subtitle to be displayed in the card.
 * @param text Optional text description to be displayed in the card. It is truncated to fit on 2 lines.
 * @param button1Text Optional text of the first button in the card. If not present, button will not be shown. If present, [onButton1Click] need to be  handle.
 * @param button2Text Optional text of the second button in the card. If not present, button will not be shown. If present, [onButton2Click] need to be  handle.
 * @param imageContentDescription Optional card image content description.
 * @param imageBackgroundColor Optional background color of the card image.
 * @param imageContentScale Optional scale parameter used to determine the aspect ratio scaling to be used
 * if the bounds are a different size from the intrinsic size of the [Painter]
 * @param imageAlignment Optional alignment parameter used to place the [Painter] in the given
 * bounds defined by the width and height.
 * @param imagePosition Position of the image, it can be [OdsHorizontalCardImagePosition.Start] or [OdsHorizontalCardImagePosition.End] in the card. [OdsHorizontalCardImagePosition.Start] by default.
 * @param dividerEnabled If true, a divider is displayed between card content and the action buttons. True by default.
 * @param onCardClick Optional click on the card itself.
 * @param onButton1Click Optional handler for the first button click.
 * @param onButton2Click Optional handler for the second button click.
 */
@Composable
@OdsComponentApi
fun OdsHorizontalCard(
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
    imagePosition: OdsHorizontalCardImagePosition = OdsHorizontalCardImagePosition.Start,
    dividerEnabled: Boolean = true,
    onCardClick: (() -> Unit)? = null,
    onButton1Click: (() -> Unit)? = null,
    onButton2Click: (() -> Unit)? = null
) {
    OdsCard(
        modifier = modifier.fillMaxWidth(),
        onClick = onCardClick
    ) {
        Column {
            Row {
                if (imagePosition == OdsHorizontalCardImagePosition.Start) {
                    HorizontalCardImage(
                        image = image,
                        contentScale = imageContentScale,
                        alignment = imageAlignment,
                        contentDescription = imageContentDescription,
                        backgroundColor = imageBackgroundColor
                    )
                }
                
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(dimensionResource(id = R.dimen.spacing_m))
                ) {
                    OdsTextH6(text = title)
                    subtitle?.let {
                        OdsTextSubtitle2(text = it)
                    }
                    text?.let {
                        Text(
                            modifier = Modifier.padding(
                                top = dimensionResource(id = R.dimen.spacing_s)
                            ),
                            text = it,
                            style = OdsTheme.typography.body1,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }

                if (imagePosition == OdsHorizontalCardImagePosition.End) {
                    HorizontalCardImage(
                        image = image,
                        contentScale = imageContentScale,
                        alignment = imageAlignment,
                        contentDescription = imageContentDescription,
                        backgroundColor = imageBackgroundColor
                    )
                }
            }

            if (dividerEnabled && (button1Text != null || button2Text != null)) {
                OdsDivider()
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

enum class OdsHorizontalCardImagePosition {
    Start, End
}

@Composable
private fun HorizontalCardImage(
    image: Painter,
    contentScale: ContentScale,
    alignment: Alignment,
    contentDescription: String? = null,
    backgroundColor: Color? = null
) {
    Image(
        painter = image,
        contentDescription = contentDescription,
        contentScale = contentScale,
        modifier = Modifier
            .size(dimensionResource(R.dimen.card_horizontal_image_size))
            .let {
                if (backgroundColor != null) it.background(backgroundColor) else it
            },
        alignment = alignment
    )
}

@UiModePreviews.Default
@Composable
private fun PreviewOdsHorizontalCard(@PreviewParameter(OdsHorizontalCardPreviewParameterProvider::class) parameter: OdsHorizontalCardPreviewParameter) =
    Preview {
        OdsHorizontalCard(
            title = "Title",
            subtitle = "Subtitle",
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed non risus. Suspendisse lectus tortor, dignissim sit amet, adipiscing nec, ultricies sed, dolor.",
            button1Text = parameter.button1Text,
            button2Text = parameter.button2Text,
            image = painterResource(id = R.drawable.placeholder),
            dividerEnabled = parameter.dividerEnabled,
            imagePosition = parameter.imagePosition
        )
    }

internal data class OdsHorizontalCardPreviewParameter(
    val imagePosition: OdsHorizontalCardImagePosition,
    val dividerEnabled: Boolean,
    val button1Text: String?,
    val button2Text: String?
)

private class OdsHorizontalCardPreviewParameterProvider :
    BasicPreviewParameterProvider<OdsHorizontalCardPreviewParameter>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<OdsHorizontalCardPreviewParameter>
    get() {
        val button1Text = "Button 1"
        val button2Text = "Button 2"

        return listOf(
            OdsHorizontalCardPreviewParameter(OdsHorizontalCardImagePosition.Start, true, button1Text, button2Text),
            OdsHorizontalCardPreviewParameter(OdsHorizontalCardImagePosition.End, false, button1Text, null),
            OdsHorizontalCardPreviewParameter(OdsHorizontalCardImagePosition.Start, true, null, null)
        )
    }
