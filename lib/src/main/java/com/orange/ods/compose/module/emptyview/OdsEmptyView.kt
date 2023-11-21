/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.compose.module.emptyview

import androidx.annotation.RawRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.orange.ods.R
import com.orange.ods.compose.component.button.OdsOutlinedButton
import com.orange.ods.compose.component.content.OdsComponentContent
import com.orange.ods.compose.component.content.OdsComponentImage
import com.orange.ods.compose.component.utilities.BasicPreviewParameterProvider
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.theme.OdsTheme

/**
 * Display a full screen empty view with a centered image followed by a title and an optional text. A button can also be added.
 *
 * @param title The title of the screen displayed below the image. For example "File is missing".
 * @param modifier [Modifier] applied to the composable.
 * @param text Text displayed below the title.
 * @param image Image displayed centered in the composable.
 * @param button The button to add below the text.
 */
@Composable
fun OdsEmptyView(
    title: String,
    modifier: Modifier = Modifier,
    text: String? = null,
    image: OdsEmptyView.Illustration = OdsEmptyView.Illustration(R.raw.empty_animation),
    button: OdsEmptyView.Button? = null
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = dimensionResource(id = R.dimen.spacing_m), vertical = dimensionResource(id = R.dimen.spacing_s)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        image.Content()

        Text(
            modifier = Modifier
                .padding(top = dimensionResource(id = R.dimen.spacing_m))
                .fillMaxWidth(),
            text = title,
            textAlign = TextAlign.Center,
            style = OdsTheme.typography.h6
        )

        text?.let {
            Text(
                modifier = Modifier
                    .padding(top = dimensionResource(id = R.dimen.spacing_s))
                    .fillMaxWidth(),
                text = text,
                textAlign = TextAlign.Center,
                style = OdsTheme.typography.subtitle2
            )
        }

        button?.Content(modifier = Modifier.padding(top = dimensionResource(id = R.dimen.spacing_m)))
    }
}

object OdsEmptyView {
    /**
     * A button in an [OdsEmptyView].
     *
     * @constructor Creates an instance of [OdsEmptyView.Button].
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
     * An image in an [OdsEmptyView].
     */
    class Illustration : OdsComponentImage<Nothing> {

        /**
         * Creates an instance of [OdsEmptyView.Illustration].
         *
         * @param animationRes The animation resource identifier to play.
         */
        constructor(@RawRes animationRes: Int) : super(animationRes, "")

        /**
         * Creates an instance of [OdsEmptyView.Illustration].
         *
         * @param painter The painter to draw.
         * @param contentScale The rule to apply to scale the image in this [OdsEmptyView.Illustration], [ContentScale.Crop] by default.
         */
        constructor(painter: Painter, contentScale: ContentScale = ContentScale.Crop) : super(
            painter,
            "",
            contentScale = contentScale
        )

        /**
         * Creates an instance of [OdsEmptyView.Illustration].
         *
         * @param imageVector The image vector to draw.
         * @param contentScale The rule to apply to scale the image in this [OdsEmptyView.Illustration], [ContentScale.Crop] by default.
         */
        constructor(imageVector: ImageVector, contentScale: ContentScale = ContentScale.Crop) : super(
            imageVector,
            "",
            contentScale = contentScale
        )

        /**
         * Creates an instance of [OdsEmptyView.Illustration].
         *
         * @param bitmap The image bitmap to draw.
         * @param contentScale The rule to apply to scale the image in this [OdsEmptyView.Illustration], [ContentScale.Crop] by default.
         */
        constructor(bitmap: ImageBitmap, contentScale: ContentScale = ContentScale.Crop) : super(
            bitmap,
            "",
            contentScale = contentScale
        )
    }
}

@UiModePreviews.Default
@Composable
private fun PreviewOdsEmptyView(@PreviewParameter(OdsEmptyViewPreviewParameterProvider::class) parameter: OdsEmptyViewPreviewParameter) =
    Preview {
        with(parameter) {
            OdsEmptyView(title = title, text = text, image = image, button = button)
        }
    }

private data class OdsEmptyViewPreviewParameter(
    val title: String,
    val text: String? = null,
    val image: OdsEmptyView.Illustration = OdsEmptyView.Illustration(R.raw.empty_animation),
    val button: OdsEmptyView.Button? = null
)

private class OdsEmptyViewPreviewParameterProvider :
    BasicPreviewParameterProvider<OdsEmptyViewPreviewParameter>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<OdsEmptyViewPreviewParameter>
    get() {
        val title = "Nothing to see here"
        val text = "To add your favourite stations, press the button."
        val button = OdsEmptyView.Button("Add station") {}

        return listOf(
            OdsEmptyViewPreviewParameter(title = title, text = text, button = button),
            OdsEmptyViewPreviewParameter(title = title, text = text),
            OdsEmptyViewPreviewParameter(title = title),
        )
    }