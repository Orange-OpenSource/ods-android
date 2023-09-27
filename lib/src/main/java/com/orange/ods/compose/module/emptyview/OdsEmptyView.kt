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


@Composable
fun OdsEmptyView(
    title: String,
    text: String? = null,
    image: OdsEmptyViewIllustration = OdsEmptyViewIllustration(R.raw.empty_animation),
    button: OdsEmptyViewButton? = null
) {
    Column(
        modifier = Modifier
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
                    .padding(top = dimensionResource(id = R.dimen.spacing_s), bottom = dimensionResource (id = R.dimen.spacing_m))
                    .fillMaxWidth(),
                text = text,
                textAlign = TextAlign.Center,
                style = OdsTheme.typography.subtitle2
            )
        }

        button?.Content()
    }
}

/**
 * A button in an [OdsEmptyView].
 *
 * @constructor Creates an instance of [OdsEmptyViewButton].
 * @param text Text of the button.
 * @param onClick Will be called when the user clicks the button.
 */
class OdsEmptyViewButton(private val text: String, private val onClick: () -> Unit) : OdsComponentContent() {

    @Composable
    override fun Content(modifier: Modifier) {
        OdsOutlinedButton(text = text, onClick = onClick, modifier = modifier)
    }
}

/**
 * An image in an [OdsEmptyView].
 */
class OdsEmptyViewIllustration : OdsComponentImage {

    /**
     * Creates an instance of [OdsEmptyViewIllustration].
     *
     * @param animationRes The animation resource identifier to play.
     */
    constructor(@RawRes animationRes: Int) : super(animationRes, "")

    /**
     * Creates an instance of [OdsEmptyViewIllustration].
     *
     * @param painter The painter to draw.
     * @param contentScale The rule to apply to scale the image in this [OdsEmptyViewIllustration], [ContentScale.Crop] by default.
     */
    constructor(painter: Painter, contentScale: ContentScale = ContentScale.Crop) : super(
        painter,
        "",
        contentScale = contentScale
    )

    /**
     * Creates an instance of [OdsEmptyViewIllustration].
     *
     * @param imageVector The image vector to draw.
     * @param contentScale The rule to apply to scale the image in this [OdsEmptyViewIllustration], [ContentScale.Crop] by default.
     */
    constructor(imageVector: ImageVector, contentScale: ContentScale = ContentScale.Crop) : super(
        imageVector,
        "",
        contentScale = contentScale
    )

    /**
     * Creates an instance of [OdsEmptyViewIllustration].
     *
     * @param bitmap The image bitmap to draw.
     * @param contentDescription The content description associated to this [OdsEmptyViewIllustration].
     * @param contentScale The rule to apply to scale the image in this [OdsEmptyViewIllustration], [ContentScale.Crop] by default.
     */
    constructor(bitmap: ImageBitmap, contentScale: ContentScale = ContentScale.Crop) : super(
        bitmap,
        "",
        contentScale = contentScale
    )
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
    val image: OdsEmptyViewIllustration = OdsEmptyViewIllustration(R.raw.empty_animation),
    val button: OdsEmptyViewButton? = null
)

private class OdsEmptyViewPreviewParameterProvider :
    BasicPreviewParameterProvider<OdsEmptyViewPreviewParameter>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<OdsEmptyViewPreviewParameter>
    get() {
        val title = "Nothing to see here"
        val text = "To add your favourite stations, press the button."
        val button = OdsEmptyViewButton("Add station") {}

        return listOf(
            OdsEmptyViewPreviewParameter(title = title, text = text, button = button),
            OdsEmptyViewPreviewParameter(title = title, text = text),
            OdsEmptyViewPreviewParameter(title = title),
        )
    }