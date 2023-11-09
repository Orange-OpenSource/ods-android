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

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
import com.orange.ods.compose.component.OdsComposable
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
 * @param title Title displayed into the card.
 * @param image [OdsCard.Image] displayed into the card.
 * @param modifier [Modifier] applied to the layout of the card.
 * @param subtitle Subtitle displayed into the card.
 * @param text Text displayed into the card.
 * @param firstButton First [OdsCard.Button] displayed into the card.
 * @param secondButton Second [OdsCard.Button] displayed into the card.
 * @param imagePosition Position of the image within the card, it can be set to [OdsHorizontalCardImagePosition.Start] or [OdsHorizontalCardImagePosition.End]. [OdsHorizontalCardImagePosition.Start] by default.
 * @param divider Controls the divider display. If `true`, it will be displayed between the card content and the action buttons.
 * @param onClick Callback invoked on card click.
 */
@Composable
@OdsComposable
fun OdsHorizontalCard(
    title: String,
    image: OdsCard.Image,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    text: String? = null,
    firstButton: OdsCard.Button? = null,
    secondButton: OdsCard.Button? = null,
    imagePosition: OdsHorizontalCardImagePosition = OdsHorizontalCardImagePosition.Start,
    divider: Boolean = true,
    onClick: (() -> Unit)? = null,
) {
    OdsCard(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick
    ) {
        ConstraintLayout {
            val (
                imageRef,
                titleRef,
                subtitleRef,
                textRef,
                chainBottomSpacerRef, // A 0 dp spacer located at the bottom of the vertical chain composed of title, subtitle and text. Without this spacer, when text is gone, bottom margin of the chain is not respected
                dividerRef,
                firstButtonRef,
                secondButtonRef
            ) = createRefs()

            val imageSize = dimensionResource(R.dimen.card_horizontal_image_size)
            val smallSpacing = dimensionResource(id = R.dimen.spacing_s)
            val mediumSpacing = dimensionResource(id = R.dimen.spacing_m)

            image.Content(
                modifier = Modifier
                    .constrainAs(imageRef) {
                        top.linkTo(parent.top)
                        bottom.linkTo(dividerRef.top)
                        when (imagePosition) {
                            OdsHorizontalCardImagePosition.Start -> start.linkTo(parent.start)
                            OdsHorizontalCardImagePosition.End -> end.linkTo(parent.end)
                        }
                        width = Dimension.value(imageSize)
                        height = Dimension.fillToConstraints.atLeast(imageSize)
                    }
            )

            val chainRef = createVerticalChain(titleRef, subtitleRef, textRef, chainBottomSpacerRef, chainStyle = ChainStyle.Packed)
            constrain(chainRef) {
                top.linkTo(parent.top, margin = mediumSpacing)
                bottom.linkTo(imageRef.bottom, margin = mediumSpacing)
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
                    top.linkTo(imageRef.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    visibility = if (divider && (firstButton != null || secondButton != null)) Visibility.Visible else Visibility.Gone
                }
            )

            Box(modifier = Modifier.constrainAs(firstButtonRef) {
                top.linkTo(dividerRef.bottom)
                start.linkTo(parent.start, margin = smallSpacing)
                visibility = if (firstButton != null) Visibility.Visible else Visibility.Gone
            }) {
                firstButton?.Content()
            }

            Box(
                modifier = Modifier.constrainAs(secondButtonRef) {
                    top.linkTo(dividerRef.bottom)
                    start.linkTo(firstButtonRef.end, margin = smallSpacing, goneMargin = smallSpacing)
                    visibility = if (secondButton != null) Visibility.Visible else Visibility.Gone
                }
            ) {
                secondButton?.Content()
            }
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
            firstButton = parameter.firstButtonText?.let { OdsCard.Button(it) {} },
            secondButton = parameter.secondButtonText?.let { OdsCard.Button(it) {} },
            image = OdsCard.Image(painterResource(id = R.drawable.placeholder), ""),
            imagePosition = parameter.imagePosition,
            divider = parameter.dividerEnabled
        )
    }

internal data class OdsHorizontalCardPreviewParameter(
    val subtitle: String?,
    val imagePosition: OdsHorizontalCardImagePosition,
    val dividerEnabled: Boolean,
    val firstButtonText: String?,
    val secondButtonText: String?
)

private class OdsHorizontalCardPreviewParameterProvider :
    BasicPreviewParameterProvider<OdsHorizontalCardPreviewParameter>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<OdsHorizontalCardPreviewParameter>
    get() {
        val subtitle = "Subtitle"
        val firstButtonText = "First button"
        val secondButtonText = "Second button"

        return listOf(
            OdsHorizontalCardPreviewParameter(subtitle, OdsHorizontalCardImagePosition.Start, true, firstButtonText, secondButtonText),
            OdsHorizontalCardPreviewParameter(subtitle, OdsHorizontalCardImagePosition.End, false, firstButtonText, null),
            OdsHorizontalCardPreviewParameter(subtitle, OdsHorizontalCardImagePosition.Start, true, null, null),
            OdsHorizontalCardPreviewParameter(null, OdsHorizontalCardImagePosition.Start, false, null, secondButtonText)
        )
    }
