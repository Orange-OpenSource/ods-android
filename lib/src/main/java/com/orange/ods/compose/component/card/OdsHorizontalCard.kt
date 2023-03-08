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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.SnackbarDefaults.backgroundColor
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
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.Visibility
import androidx.constraintlayout.compose.atLeast
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
 * @param button1Text Optional text of the first button in the card. If not present, button will not be shown. If present, [onButton1Click] need to be handled.
 * @param button2Text Optional text of the second button in the card. If not present, button will not be shown. If present, [onButton2Click] need to be handled.
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
        ConstraintLayout {
            val (
                imageRef,
                titleRef,
                subtitleRef,
                textRef,
                chainBottomSpacerRef, // A 0 dp spacer located at the bottom of the vertical chain composed of title, subtitle and text. Without this spacer, when text is gone, bottom margin of the chain is not respected
                dividerRef,
                button1Ref,
                button2Ref
            ) = createRefs()

            // Divider is not always visible thus we need to add this barrier otherwise margin between divider and text vertical chain is not respected
            val dividerTopBarrier = createTopBarrier(dividerRef)
            val buttonsTopBarrier = createTopBarrier(button1Ref, button2Ref)

            val imageSize = dimensionResource(R.dimen.card_horizontal_image_size)
            val smallSpacing = dimensionResource(id = R.dimen.spacing_s)
            val mediumSpacing = dimensionResource(id = R.dimen.spacing_m)

            Image(
                painter = image,
                contentDescription = imageContentDescription,
                contentScale = imageContentScale,
                modifier = Modifier
                    .let { if (imageBackgroundColor != null) it.background(backgroundColor) else it }
                    .constrainAs(imageRef) {
                        top.linkTo(parent.top)
                        bottom.linkTo(dividerTopBarrier)
                        when (imagePosition) {
                            OdsHorizontalCardImagePosition.Start -> start.linkTo(parent.start)
                            OdsHorizontalCardImagePosition.End -> end.linkTo(parent.end)
                        }
                        width = Dimension.value(imageSize)
                        height = Dimension.fillToConstraints.atLeast(imageSize)
                    },
                alignment = imageAlignment
            )

            val chainRef = createVerticalChain(titleRef, subtitleRef, textRef, chainBottomSpacerRef, chainStyle = ChainStyle.Packed)
            constrain(chainRef) {
                top.linkTo(parent.top, margin = mediumSpacing)
                bottom.linkTo(dividerTopBarrier, margin = mediumSpacing)
            }

            OdsTextH6(
                text = title,
                modifier = Modifier.constrainAs(titleRef) {
                    when (imagePosition) {
                        OdsHorizontalCardImagePosition.Start -> {
                            start.linkTo(imageRef.end, margin = mediumSpacing)
                            end.linkTo(parent.end, margin = mediumSpacing)
                        }
                        OdsHorizontalCardImagePosition.End -> {
                            start.linkTo(parent.start, margin = mediumSpacing)
                            end.linkTo(imageRef.start, margin = mediumSpacing)
                        }
                    }
                    width = Dimension.fillToConstraints
                }
            )

            OdsTextSubtitle2(
                text = subtitle.orEmpty(),
                modifier = Modifier.constrainAs(subtitleRef) {
                    start.linkTo(titleRef.start)
                    end.linkTo(titleRef.end)
                    width = Dimension.fillToConstraints
                    visibility = if (subtitle != null) Visibility.Visible else Visibility.Gone
                }
            )

            Text(
                modifier = Modifier
                    .padding(top = smallSpacing) // For some reason, margins inside a chain are not applied, a workaround is to apply padding before the constraints
                    .constrainAs(textRef) {
                        start.linkTo(titleRef.start)
                        end.linkTo(titleRef.end)
                        width = Dimension.fillToConstraints
                        visibility = if (text != null) Visibility.Visible else Visibility.Gone
                    },
                text = text.orEmpty(),
                style = OdsTheme.typography.body1,
                maxLines = if (subtitle == null) 3 else 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.constrainAs(chainBottomSpacerRef) {})

            OdsDivider(
                modifier = Modifier.constrainAs(dividerRef) {
                    bottom.linkTo(buttonsTopBarrier)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    visibility = if (dividerEnabled && (button1Text != null || button2Text != null)) Visibility.Visible else Visibility.Gone
                }
            )

            OdsTextButton(
                modifier = Modifier.constrainAs(button1Ref) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start, margin = smallSpacing)
                    visibility = if (button1Text != null) Visibility.Visible else Visibility.Gone
                },
                text = button1Text.orEmpty(),
                onClick = { onButton1Click?.invoke() },
                style = OdsTextButtonStyle.Primary
            )

            OdsTextButton(
                modifier = Modifier.constrainAs(button2Ref) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(button1Ref.end, margin = smallSpacing, goneMargin = smallSpacing)
                    visibility = if (button2Text != null) Visibility.Visible else Visibility.Gone
                },
                text = button2Text.orEmpty(),
                onClick = { onButton2Click?.invoke() },
                style = OdsTextButtonStyle.Primary
            )
        }
    }
}

enum class OdsHorizontalCardImagePosition {
    Start, End
}

@UiModePreviews.Default
@Composable
private fun PreviewOdsHorizontalCard(@PreviewParameter(OdsHorizontalCardPreviewParameterProvider::class) parameter: OdsHorizontalCardPreviewParameter) =
    Preview {
        OdsHorizontalCard(
            title = "Title",
            subtitle = parameter.subtitle,
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed non risus. Suspendisse lectus tortor, dignissim sit amet, adipiscing nec, ultricies sed, dolor.",
            button1Text = parameter.button1Text,
            button2Text = parameter.button2Text,
            image = painterResource(id = R.drawable.placeholder),
            dividerEnabled = parameter.dividerEnabled,
            imagePosition = parameter.imagePosition
        )
    }

internal data class OdsHorizontalCardPreviewParameter(
    val subtitle: String?,
    val imagePosition: OdsHorizontalCardImagePosition,
    val dividerEnabled: Boolean,
    val button1Text: String?,
    val button2Text: String?
)

private class OdsHorizontalCardPreviewParameterProvider :
    BasicPreviewParameterProvider<OdsHorizontalCardPreviewParameter>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<OdsHorizontalCardPreviewParameter>
    get() {
        val subtitle = "Subtitle"
        val button1Text = "Button 1"
        val button2Text = "Button 2"

        return listOf(
            OdsHorizontalCardPreviewParameter(subtitle, OdsHorizontalCardImagePosition.Start, true, button1Text, button2Text),
            OdsHorizontalCardPreviewParameter(subtitle, OdsHorizontalCardImagePosition.End, false, button1Text, null),
            OdsHorizontalCardPreviewParameter(subtitle, OdsHorizontalCardImagePosition.Start, true, null, null),
            OdsHorizontalCardPreviewParameter(null, OdsHorizontalCardImagePosition.Start, false, null, button2Text)
        )
    }
