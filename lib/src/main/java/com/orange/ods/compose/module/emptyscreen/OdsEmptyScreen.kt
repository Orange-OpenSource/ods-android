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

package com.orange.ods.compose.module.emptyscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.orange.ods.R
import com.orange.ods.compose.component.button.OdsOutlinedButton
import com.orange.ods.compose.component.content.OdsComponentContent
import com.orange.ods.compose.component.content.OdsComponentImage
import com.orange.ods.compose.component.utilities.BasicPreviewParameterProvider
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.text.OdsText
import com.orange.ods.theme.typography.OdsTextStyle

/**
 * Display an empty screen with a centered image followed by a title and an optional text. A button can also be added.
 *
 * @param title The title of the screen displayed below the image. For example "File is missing".
 * @param modifier [Modifier] applied to the composable.
 * @param text Text displayed below the title.
 * @param image Image displayed centered in the composable.
 * @param button The button to add below the text.
 */
@Composable
fun OdsEmptyScreen(
    title: String,
    modifier: Modifier = Modifier,
    text: String? = null,
    image: OdsEmptyScreen.Image = OdsEmptyScreen.Image(painter = painterResource(id = R.drawable.il_yoga_man)),
    button: OdsEmptyScreen.Button? = null
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = dimensionResource(id = R.dimen.spacing_m), vertical = dimensionResource(id = R.dimen.spacing_s)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        image.Content(modifier = Modifier.fillMaxWidth())

        OdsText(
            modifier = Modifier
                .padding(top = dimensionResource(id = R.dimen.spacing_m))
                .fillMaxWidth(),
            text = title,
            textAlign = TextAlign.Center,
            style = OdsTextStyle.TitleL
        )

        text?.let {
            OdsText(
                modifier = Modifier
                    .padding(top = dimensionResource(id = R.dimen.spacing_s))
                    .fillMaxWidth(),
                text = text,
                textAlign = TextAlign.Center,
                style = OdsTextStyle.BodyM
            )
        }

        button?.Content(modifier = Modifier.padding(top = dimensionResource(id = R.dimen.spacing_m)))
    }
}

/**
 * Contains classes to build an [com.orange.ods.compose.module.emptyscreen.OdsEmptyScreen].
 */
object OdsEmptyScreen {

    /**
     * A button in an [OdsEmptyScreen].
     *
     * @constructor Creates an instance of [OdsEmptyScreen.Button].
     * @param text Text of the button.
     * @param onClick Callback invoked on button click.
     */
    class Button(private val text: String, private val onClick: () -> Unit) : OdsComponentContent<Nothing>() {

        @Composable
        override fun Content(modifier: Modifier) {
            OdsOutlinedButton(text = text, onClick = onClick, modifier = modifier)
        }
    }

    /**
     * An image in an [OdsEmptyScreen].
     */
    class Image : OdsComponentImage<Nothing> {

        /**
         * Creates an instance of [OdsEmptyScreen.Image].
         *
         * @param painter The painter to draw.
         * @param alignment Alignment parameter used to place the [Painter] in the given bounds defined by the width and height.
         * @param contentScale The rule to apply to scale the image in this [OdsEmptyScreen.Image].
         */
        constructor(painter: Painter, alignment: Alignment = Alignment.Center, contentScale: ContentScale = ContentScale.Fit) : super(
            painter,
            "",
            alignment = alignment,
            contentScale = contentScale
        )

        /**
         * Creates an instance of [OdsEmptyScreen.Image].
         *
         * @param imageVector The image vector to draw.
         * @param alignment Alignment parameter used to place the [ImageVector] in the given bounds defined by the width and height.
         * @param contentScale The rule to apply to scale the image in this [OdsEmptyScreen.Image].
         */
        constructor(imageVector: ImageVector, alignment: Alignment = Alignment.Center, contentScale: ContentScale = ContentScale.Fit) : super(
            imageVector,
            "",
            alignment = alignment,
            contentScale = contentScale
        )

        /**
         * Creates an instance of [OdsEmptyScreen.Image].
         *
         * @param bitmap The image bitmap to draw.
         * @param alignment Alignment parameter used to place the [ImageBitmap] in the given bounds defined by the width and height.
         * @param contentScale The rule to apply to scale the image in this [OdsEmptyScreen.Image].
         */
        constructor(bitmap: ImageBitmap, alignment: Alignment = Alignment.Center, contentScale: ContentScale = ContentScale.Fit) : super(
            bitmap,
            "",
            alignment = alignment,
            contentScale = contentScale
        )
    }
}

@UiModePreviews.Default
@Composable
private fun PreviewOdsEmptyScreen(@PreviewParameter(OdsEmptyScreenPreviewParameterProvider::class) parameter: OdsEmptyScreenPreviewParameter) =
    Preview {
        with(parameter) {
            OdsEmptyScreen(title = title, text = text, button = button)
        }
    }

private data class OdsEmptyScreenPreviewParameter(
    val title: String,
    val text: String? = null,
    val button: OdsEmptyScreen.Button? = null
)

private class OdsEmptyScreenPreviewParameterProvider :
    BasicPreviewParameterProvider<OdsEmptyScreenPreviewParameter>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<OdsEmptyScreenPreviewParameter>
    get() {
        val title = "Nothing to see here"
        val text = "To add your favourite stations, press the button."
        val button = OdsEmptyScreen.Button("Add station") {}

        return listOf(
            OdsEmptyScreenPreviewParameter(title = title, text = text, button = button),
            OdsEmptyScreenPreviewParameter(title = title, text = text),
            OdsEmptyScreenPreviewParameter(title = title),
        )
    }