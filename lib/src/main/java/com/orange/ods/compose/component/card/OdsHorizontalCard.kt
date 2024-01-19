/*
 * Software Name: Orange Design System
 * SPDX-FileCopyrightText: Copyright (c) Orange SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT licence,
 * the text of which is available at https://opensource.org/license/MIT/
 * or see the "LICENSE" file for more details.
 *
 * Software description: Android library of reusable graphical components 
 */

package com.orange.ods.compose.component.card

import androidx.compose.foundation.layout.ExperimentalLayoutApi
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
import com.orange.ods.compose.text.OdsText
import com.orange.ods.compose.theme.OdsTheme
import com.orange.ods.theme.typography.OdsTextStyle

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
 * @param imagePosition Position of the image within the card, it can be set to [OdsCard.Image.Position.Start] or [OdsCard.Image.Position.End]. [OdsCard.Image.Position.Start] by default.
 * @param divider Controls the divider display. If `true`, it will be displayed between the card content and the action buttons.
 * @param onClick Callback invoked on card click.
 */
@OptIn(ExperimentalLayoutApi::class)
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
    imagePosition: OdsCard.Image.Position = OdsCard.Image.Position.Start,
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
                buttonsRef
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
                            OdsCard.Image.Position.Start -> start.linkTo(parent.start)
                            OdsCard.Image.Position.End -> end.linkTo(parent.end)
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

            OdsText(
                text = title,
                modifier = Modifier.constrainAs(titleRef) {
                    when (imagePosition) {
                        OdsCard.Image.Position.Start -> {
                            start.linkTo(imageRef.end, margin = mediumSpacing)
                            end.linkTo(parent.end, margin = mediumSpacing)
                        }
                        OdsCard.Image.Position.End -> {
                            start.linkTo(parent.start, margin = mediumSpacing)
                            end.linkTo(imageRef.start, margin = mediumSpacing)
                        }
                    }
                    width = Dimension.fillToConstraints
                },
                style = OdsTextStyle.TitleL
            )

            OdsText(
                text = subtitle.orEmpty(),
                modifier = Modifier.constrainAs(subtitleRef) {
                    start.linkTo(titleRef.start)
                    end.linkTo(titleRef.end)
                    width = Dimension.fillToConstraints
                    visibility = if (subtitle != null) Visibility.Visible else Visibility.Gone
                },
                style = OdsTextStyle.TitleS
            )

            OdsText(
                modifier = Modifier
                    .padding(top = smallSpacing) // For some reason, margins inside a chain are not applied, a workaround is to apply padding before the constraints
                    .constrainAs(textRef) {
                        start.linkTo(titleRef.start)
                        end.linkTo(titleRef.end)
                        width = Dimension.fillToConstraints
                        visibility = if (text != null) Visibility.Visible else Visibility.Gone
                    },
                text = text.orEmpty(),
                style = OdsTextStyle.BodyL,
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

            OdsCardButtonsFlowRow(
                modifier = Modifier.constrainAs(buttonsRef) {
                    top.linkTo(dividerRef.bottom)
                    start.linkTo(parent.start, margin = smallSpacing)
                    visibility = if (firstButton != null || secondButton != null) Visibility.Visible else Visibility.Gone
                },
                firstButton = firstButton,
                secondButton = secondButton
            )
        }
    }
}

@UiModePreviews.Default
@Composable
private fun PreviewOdsHorizontalCard(@PreviewParameter(OdsHorizontalCardPreviewParameterProvider::class) parameter: OdsHorizontalCardPreviewParameter) =
    Preview {
        OdsHorizontalCard(
            title = CardPreview.Title,
            subtitle = parameter.subtitle,
            text = CardPreview.Text,
            firstButton = parameter.firstButtonText?.let { OdsCard.Button(it) {} },
            secondButton = parameter.secondButtonText?.let { OdsCard.Button(it) {} },
            image = OdsCard.Image(painterResource(id = R.drawable.placeholder), ""),
            imagePosition = parameter.imagePosition,
            divider = parameter.dividerEnabled
        )
    }

private data class OdsHorizontalCardPreviewParameter(
    val subtitle: String?,
    val imagePosition: OdsCard.Image.Position,
    val dividerEnabled: Boolean,
    val firstButtonText: String?,
    val secondButtonText: String?
)

private class OdsHorizontalCardPreviewParameterProvider :
    BasicPreviewParameterProvider<OdsHorizontalCardPreviewParameter>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<OdsHorizontalCardPreviewParameter>
    get() = listOf(
        OdsHorizontalCardPreviewParameter(CardPreview.Subtitle, OdsCard.Image.Position.Start, true, CardPreview.FirstButtonText, CardPreview.SecondButtonText),
        OdsHorizontalCardPreviewParameter(
            CardPreview.Subtitle,
            OdsCard.Image.Position.Start,
            true,
            CardPreview.FirstButtonText,
            CardPreview.SecondButtonLongText
        ),
        OdsHorizontalCardPreviewParameter(CardPreview.Subtitle, OdsCard.Image.Position.End, false, CardPreview.FirstButtonText, null),
        OdsHorizontalCardPreviewParameter(CardPreview.Subtitle, OdsCard.Image.Position.Start, true, null, null),
        OdsHorizontalCardPreviewParameter(null, OdsCard.Image.Position.Start, false, null, CardPreview.SecondButtonText)
    )
