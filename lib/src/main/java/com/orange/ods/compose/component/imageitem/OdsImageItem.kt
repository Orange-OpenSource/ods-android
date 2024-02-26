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

package com.orange.ods.compose.component.imageitem

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.orange.ods.R
import com.orange.ods.compose.component.OdsComposable
import com.orange.ods.compose.component.button.OdsIconButton
import com.orange.ods.compose.component.button.OdsIconToggleButton
import com.orange.ods.compose.component.content.OdsComponentImage
import com.orange.ods.compose.component.utilities.BasicPreviewParameterProvider
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.text.OdsText
import com.orange.ods.compose.theme.OdsDisplaySurface
import com.orange.ods.compose.theme.OdsTheme
import com.orange.ods.extension.orElse
import com.orange.ods.theme.typography.OdsTextStyle


/**
 * An image item contains primary information which is an image. It can also contain secondary information such as text or action. Image items have no more than two
 * actions. They are usually used in grids.
 *
 * @param image [OdsImageItem.Image] displayed into the item.
 * @param legendAreaDisplayType Controls how the title and the icon are displayed relatively to the image. If set to [OdsImageItem.LegendAreaDisplayType.None],
 * no legend area will be displayed.
 * @param modifier [Modifier] applied to the image item.
 * @param title Title displayed into the image item. It is linked to the image and displayed according to the [legendAreaDisplayType] value.
 * @param icon [OdsImageItem.IconToggleButton] displayed next to the title.
 * @param onClick Callback invoked on image item click.
 */
@Composable
@OdsComposable
fun OdsImageItem(
    image: OdsImageItem.Image,
    legendAreaDisplayType: OdsImageItem.LegendAreaDisplayType,
    modifier: Modifier = Modifier,
    title: String? = null,
    icon: OdsImageItem.IconToggleButton? = null,
    onClick: (() -> Unit)? = null,
) {
    Box(modifier = modifier.run {
        onClick?.let { clickable { onClick() } }.orElse { this }
    }) {
        when (legendAreaDisplayType) {
            OdsImageItem.LegendAreaDisplayType.Overlay -> {
                image.Content(modifier = Modifier.fillMaxSize())
                title?.let {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .background(color = Color.Black.copy(alpha = 0.38f))
                            .align(Alignment.BottomStart)
                            .height(IntrinsicSize.Min)
                    ) {
                        OdsImageItemLegendArea(
                            text = it,
                            color = Color.White,
                            displaySurface = OdsDisplaySurface.Dark,
                            textModifier = Modifier
                                .weight(1f)
                                .padding(all = dimensionResource(id = R.dimen.spacing_m)),
                            icon = icon
                        )
                    }
                }
            }

            OdsImageItem.LegendAreaDisplayType.Below ->
                Column(verticalArrangement = Arrangement.Center) {
                    image.Content(
                        modifier = Modifier
                            .weight(1.0f)
                            .fillMaxWidth()
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .height(IntrinsicSize.Min)
                    ) {
                        title?.let {
                            OdsImageItemLegendArea(
                                text = it,
                                color = OdsTheme.colors.onSurface,
                                displaySurface = OdsDisplaySurface.Default,
                                textModifier = Modifier
                                    .weight(1f)
                                    .padding(vertical = dimensionResource(id = R.dimen.spacing_m))
                                    .padding(end = dimensionResource(id = R.dimen.spacing_m)),
                                icon = icon
                            )
                        }
                    }
                }

            OdsImageItem.LegendAreaDisplayType.None ->
                image.Content(modifier = Modifier.fillMaxSize())
        }
    }
}

/**
 * Contains classes to build an [com.orange.ods.compose.component.imageitem.OdsImageItem].
 */
object OdsImageItem {

    enum class LegendAreaDisplayType {
        Below, Overlay, None
    }

    /**
     * An image in an [OdsImageItem].
     */
    class Image : OdsComponentImage<Nothing> {

        /**
         * Creates an instance of [OdsImageItem.Image].
         *
         * @param painter The painter to draw.
         * @param contentDescription The content description associated to this [OdsImageItem.Image].
         * @param contentScale The rule to apply to scale the image in this [OdsImageItem.Image], [ContentScale.Crop] by default.
         */
        constructor(painter: Painter, contentDescription: String, contentScale: ContentScale = ContentScale.Crop) : super(
            painter,
            contentDescription,
            contentScale = contentScale
        )

        /**
         * Creates an instance of [OdsImageItem.Image].
         *
         * @param imageVector The image vector to draw.
         * @param contentDescription The content description associated to this [OdsImageItem.Image].
         * @param contentScale The rule to apply to scale the image in this [OdsImageItem.Image], [ContentScale.Crop] by default.
         */
        constructor(imageVector: ImageVector, contentDescription: String, contentScale: ContentScale = ContentScale.Crop) : super(
            imageVector,
            contentDescription,
            contentScale = contentScale
        )

        /**
         * Creates an instance of [OdsImageItem.Image].
         *
         * @param bitmap The image bitmap to draw.
         * @param contentDescription The content description associated to this [OdsImageItem.Image].
         * @param contentScale The rule to apply to scale the image in this [OdsImageItem.Image], [ContentScale.Crop] by default.
         */
        constructor(bitmap: ImageBitmap, contentDescription: String, contentScale: ContentScale = ContentScale.Crop) : super(
            bitmap,
            contentDescription,
            contentScale = contentScale
        )
    }

    /**
     * An icon toggle button in an [OdsImageItem].
     *
     * @param checked Specify if icon is currently checked.
     * @param onCheckedChange Callback to be invoked when this icon is selected.
     * @param checkedIcon Icon displayed in front of the [OdsImageItem] when icon is checked.
     * @param uncheckedIcon Icon displayed in front of the [OdsImageItem] when icon is unchecked.
     */
    class IconToggleButton(
        val checked: Boolean,
        val onCheckedChange: (Boolean) -> Unit,
        val checkedIcon: OdsIconButton.Icon,
        val uncheckedIcon: OdsIconButton.Icon,
    ) {
        @Composable
        fun Content(displaySurface: OdsDisplaySurface) {
            OdsIconToggleButton(
                checked = checked,
                onCheckedChange = onCheckedChange,
                uncheckedIcon = uncheckedIcon,
                checkedIcon = checkedIcon,
                displaySurface = displaySurface
            )
        }
    }

}

@Composable
private fun OdsImageItemLegendArea(
    text: String,
    color: Color,
    displaySurface: OdsDisplaySurface,
    icon: OdsImageItem.IconToggleButton?,
    @SuppressLint("ModifierParameter") textModifier: Modifier
) {
    OdsText(
        text = text,
        color = color,
        modifier = textModifier,
        style = OdsTextStyle.TitleM,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
    icon?.Content(displaySurface)
}

@UiModePreviews.ImageItem
@Composable
private fun PreviewOdsImageItem(@PreviewParameter(OdsImageItemPreviewParameterProvider::class) parameter: OdsImageItemPreviewParameter) =
    Preview {
        OdsImageItem(
            image = OdsImageItem.Image(painterResource(id = parameter.image), ""),
            title = parameter.title,
            legendAreaDisplayType = parameter.type,
            icon = OdsImageItem.IconToggleButton(
                uncheckedIcon = OdsIconButton.Icon(painterResource(id = parameter.uncheckedIcon), "click to select"),
                checkedIcon = OdsIconButton.Icon(painterResource(id = parameter.checkedIcon), "click to unselect"),
                checked = parameter.checked,
                onCheckedChange = { parameter.checked },
            )
        )
    }

private data class OdsImageItemPreviewParameter(
    val image: Int,
    val checked: Boolean,
    val title: String,
    val checkedIcon: Int,
    val uncheckedIcon: Int,
    val type: OdsImageItem.LegendAreaDisplayType
)

private class OdsImageItemPreviewParameterProvider :
    BasicPreviewParameterProvider<OdsImageItemPreviewParameter>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<OdsImageItemPreviewParameter>
    get() {
        val title = "Subtitle 1"
        val image = R.drawable.placeholder
        val checkedIcon = R.drawable.ic_check
        val uncheckedIcon = R.drawable.ic_check

        return listOf(
            OdsImageItemPreviewParameter(
                image,
                title = title,
                checkedIcon = checkedIcon,
                uncheckedIcon = uncheckedIcon,
                checked = false,
                type = OdsImageItem.LegendAreaDisplayType.Below
            ),
            OdsImageItemPreviewParameter(
                image,
                title = title,
                checkedIcon = checkedIcon,
                uncheckedIcon = uncheckedIcon,
                checked = false,
                type = OdsImageItem.LegendAreaDisplayType.Overlay
            ),
            OdsImageItemPreviewParameter(
                image,
                title = title,
                checkedIcon = checkedIcon,
                uncheckedIcon = uncheckedIcon,
                checked = true,
                type = OdsImageItem.LegendAreaDisplayType.None
            )
        )
    }