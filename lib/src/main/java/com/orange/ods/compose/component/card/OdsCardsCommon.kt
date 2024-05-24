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

package com.orange.ods.compose.component.card

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.orange.ods.compose.component.button.OdsTextButton
import com.orange.ods.compose.component.content.OdsComponentCircularImage
import com.orange.ods.compose.component.content.OdsComponentContent
import com.orange.ods.compose.component.content.OdsComponentImage
import com.orange.ods.compose.theme.OdsTheme

@Composable
internal fun OdsCard(modifier: Modifier, onClick: (() -> Unit)?, content: @Composable ColumnScope.() -> Unit) {
    if (onClick != null) {
        Card(modifier = modifier.clickable { onClick() }, content = content)
    } else {
        Card(modifier = modifier.semantics(mergeDescendants = true) {}, content = content)
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun OdsCardButtonsFlowRow(modifier: Modifier = Modifier, firstButton: OdsCard.Button? = null, secondButton: OdsCard.Button? = null) {
    FlowRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(OdsTheme.spacings.small.dp),
        verticalArrangement = Arrangement.spacedBy((-6).dp)
    ) {
        firstButton?.Content()
        secondButton?.Content()
    }
}

/**
 * Contains classes to build an [com.orange.ods.compose.component.card.OdsHorizontalCard], an [com.orange.ods.compose.component.card.OdsSmallCard], an [com.orange.ods.compose.component.card.OdsVerticalImageFirstCard] or an [com.orange.ods.compose.component.card.OdsVerticalHeaderFirstCard].
 */
object OdsCard {

    /**
     * A button in an [OdsHorizontalCard], an [OdsVerticalHeaderFirstCard] or an [OdsVerticalImageFirstCard].
     *
     * @constructor Creates an instance of [OdsCard.Button].
     * @param text Text of the button.
     * @param onClick Will be called when the user clicks the button.
     */
    class Button(private val text: String, private val onClick: () -> Unit) : OdsComponentContent<Nothing>(Nothing::class.java) {

        @Composable
        override fun Content(modifier: Modifier) {
            OdsTextButton(text = text, onClick = onClick, modifier = modifier, style = OdsTextButton.Style.Primary)
        }
    }

    /**
     * An image in an [OdsSmallCard], [OdsHorizontalCard], [OdsVerticalHeaderFirstCard] or [OdsVerticalImageFirstCard].
     */
    class Image private constructor(
        val graphicsObject: Any,
        val contentDescription: String,
        val alignment: Alignment = Alignment.Center,
        val contentScale: ContentScale = ContentScale.Crop,
        val backgroundColor: Color? = null
    ) : OdsComponentImage<Nothing>(Nothing::class.java, graphicsObject, contentDescription, alignment, contentScale) {

        /**
         * Creates an instance of [OdsCard.Image].
         *
         * @param painter The painter to draw.
         * @param contentDescription The content description associated to this [OdsCard.Image].
         * @param alignment Alignment parameter used to place the [Painter] in the given
         * bounds defined by the width and height.
         * @param contentScale Scale parameter used to determine the aspect ratio scaling to be used
         * if the bounds are a different size from the intrinsic size of the [Painter]
         * @param backgroundColor Optional background color of the card image.
         */
        constructor(
            painter: Painter,
            contentDescription: String,
            alignment: Alignment = Alignment.Center,
            contentScale: ContentScale = ContentScale.Crop,
            backgroundColor: Color? = null
        ) : this(painter as Any, contentDescription, alignment, contentScale, backgroundColor)

        /**
         * Creates an instance of [OdsCard.Image].
         *
         * @param imageVector The image vector to draw.
         * @param contentDescription The content description associated to this [OdsCard.Image].
         * @param alignment Alignment parameter used to place the [ImageVector] in the given
         * bounds defined by the width and height.
         * @param contentScale Scale parameter used to determine the aspect ratio scaling to be used
         * if the bounds are a different size from the intrinsic size of the [ImageVector]
         * @param backgroundColor Optional background color of the card image.
         */
        constructor(
            imageVector: ImageVector,
            contentDescription: String,
            alignment: Alignment = Alignment.Center,
            contentScale: ContentScale = ContentScale.Crop,
            backgroundColor: Color? = null
        ) : this(imageVector as Any, contentDescription, alignment, contentScale, backgroundColor)

        /**
         * Creates an instance of [OdsCard.Image].
         *
         * @param bitmap The image bitmap to draw.
         * @param contentDescription The content description associated to this [OdsCard.Image].
         * @param alignment Alignment parameter used to place the [ImageBitmap] in the given
         * bounds defined by the width and height.
         * @param contentScale Scale parameter used to determine the aspect ratio scaling to be used
         * if the bounds are a different size from the intrinsic size of the [ImageBitmap]
         * @param backgroundColor Optional background color of the card image.
         */
        constructor(
            bitmap: ImageBitmap,
            contentDescription: String,
            alignment: Alignment = Alignment.Center,
            contentScale: ContentScale = ContentScale.Crop,
            backgroundColor: Color? = null
        ) : this(bitmap as Any, contentDescription, alignment, contentScale, backgroundColor)

        @Composable
        override fun Content(modifier: Modifier) {
            super.Content(modifier = with(modifier) { if (backgroundColor != null) background(backgroundColor) else this })
        }

        enum class Position {
            Start, End
        }
    }

    /**
     * A thumbnail in a card.
     */
    class Thumbnail private constructor(
        val graphicsObject: Any,
        val contentDescription: String
    ) : OdsComponentCircularImage<Nothing>(Nothing::class.java, graphicsObject, contentDescription) {

        /**
         * Creates an instance of [OdsCard.Thumbnail].
         *
         * @param painter The painter to draw.
         * @param contentDescription The content description associated to this [OdsCard.Thumbnail].
         */
        constructor(painter: Painter, contentDescription: String) : this(painter as Any, contentDescription)

        /**
         * Creates an instance of [OdsCard.Thumbnail].
         *
         * @param imageVector The image vector to draw.
         * @param contentDescription The content description associated to this [OdsCard.Thumbnail].
         */
        constructor(imageVector: ImageVector, contentDescription: String) : this(imageVector as Any, contentDescription)

        /**
         * Creates an instance of [OdsCard.Thumbnail].
         *
         * @param bitmap The image bitmap to draw.
         * @param contentDescription The content description associated to this [OdsCard.Thumbnail].
         */
        constructor(bitmap: ImageBitmap, contentDescription: String) : this(bitmap as Any, contentDescription)
    }

}

/**
 * Constants used for card preview
 */
internal object CardPreview {
    const val Title = "Title"
    const val LongTitle = "Here is a long title that don't fit"
    const val Subtitle = "Subtitle"
    const val LongSubtitle = "Here is a very very very long subtitle"
    const val Text =
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed non risus. Suspendisse lectus tortor, dignissim sit amet, adipiscing nec, ultricies sed, dolor."
    const val FirstButtonText = "First button"
    const val SecondButtonText = "Second button"
    const val SecondButtonLongText = "Second button with lonnnnnnng text"
}
