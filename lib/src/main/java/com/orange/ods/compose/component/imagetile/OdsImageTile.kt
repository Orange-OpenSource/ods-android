/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.compose.component.imagetile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import com.orange.ods.compose.component.button.OdsIconButtonIcon
import com.orange.ods.compose.component.button.OdsIconToggleButton
import com.orange.ods.compose.component.content.OdsComponentImage
import com.orange.ods.compose.component.utilities.BasicPreviewParameterProvider
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.theme.OdsDisplaySurface
import com.orange.ods.compose.theme.OdsTheme
import com.orange.ods.extension.orElse


/**
 * An image tile contains primary information which is an image. It can also contain secondary information such as text or action. Tiles have no more than two
 * actions. They are usually used in grids.
 *
 * @param image [OdsImageTileImage] displayed into the tile
 * @param legendAreaDisplayType Controls how the title and the icon are displayed relatively to the image. If set to [OdsImageTileLegendAreaDisplayType.None],
 * no legend area will be displayed.
 * @param modifier [Modifier] applied to the image tile
 * @param title Title displayed into the tile. It is linked to the image and displayed according to the [legendAreaDisplayType] value.
 * @param icon [OdsImageTileIconToggleButton] displayed next to the title
 * @param onClick Callback invoked on tile click
 */
@Composable
@OdsComposable
fun OdsImageTile(
    image: OdsImageTileImage,
    legendAreaDisplayType: OdsImageTileLegendAreaDisplayType,
    modifier: Modifier = Modifier,
    title: String? = null,
    icon: OdsImageTileIconToggleButton? = null,
    onClick: (() -> Unit)? = null,
) {
    Box(modifier = modifier.run {
        onClick?.let { clickable { onClick() } }.orElse { this }
    }) {
        when (legendAreaDisplayType) {
            OdsImageTileLegendAreaDisplayType.Overlay -> {
                image.Content(modifier = Modifier.fillMaxSize())
                title?.let {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .background(color = Color.Black.copy(alpha = 0.38f))
                            .align(Alignment.BottomStart)
                            .height(dimensionResource(id = R.dimen.list_single_line_item_height))
                    ) {
                        OdsImageTileLegendArea(
                            text = it,
                            color = Color.White,
                            displaySurface = OdsDisplaySurface.Dark,
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = dimensionResource(id = R.dimen.spacing_m), end = dimensionResource(id = R.dimen.spacing_m)),
                            icon = icon
                        )
                    }
                }
            }

            OdsImageTileLegendAreaDisplayType.Below ->
                Column(verticalArrangement = Arrangement.Center) {
                    image.Content(
                        modifier = Modifier
                            .weight(1.0f)
                            .fillMaxWidth()
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .height(dimensionResource(id = R.dimen.image_tile_legend_area_height))
                    ) {
                        title?.let {
                            OdsImageTileLegendArea(
                                text = it,
                                color = OdsTheme.colors.onSurface,
                                displaySurface = OdsDisplaySurface.Default,
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(end = dimensionResource(id = R.dimen.spacing_m)),
                                icon = icon
                            )
                        }
                    }
                }

            OdsImageTileLegendAreaDisplayType.None ->
                image.Content(modifier = Modifier.fillMaxSize())
        }
    }
}

/**
 * An image in an [OdsImageTile].
 */
class OdsImageTileImage : OdsComponentImage<Nothing> {

    /**
     * Creates an instance of [OdsImageTileImage].
     *
     * @param painter The painter to draw.
     * @param contentDescription The content description associated to this [OdsImageTileImage].
     * @param contentScale The rule to apply to scale the image in this [OdsImageTileImage], [ContentScale.Crop] by default.
     */
    constructor(painter: Painter, contentDescription: String, contentScale: ContentScale = ContentScale.Crop) : super(
        painter,
        contentDescription,
        contentScale = contentScale
    )

    /**
     * Creates an instance of [OdsImageTileImage].
     *
     * @param imageVector The image vector to draw.
     * @param contentDescription The content description associated to this [OdsImageTileImage].
     * @param contentScale The rule to apply to scale the image in this [OdsImageTileImage], [ContentScale.Crop] by default.
     */
    constructor(imageVector: ImageVector, contentDescription: String, contentScale: ContentScale = ContentScale.Crop) : super(
        imageVector,
        contentDescription,
        contentScale = contentScale
    )

    /**
     * Creates an instance of [OdsImageTileImage].
     *
     * @param bitmap The image bitmap to draw.
     * @param contentDescription The content description associated to this [OdsImageTileImage].
     * @param contentScale The rule to apply to scale the image in this [OdsImageTileImage], [ContentScale.Crop] by default.
     */
    constructor(bitmap: ImageBitmap, contentDescription: String, contentScale: ContentScale = ContentScale.Crop) : super(
        bitmap,
        contentDescription,
        contentScale = contentScale
    )
}

/**
 * An icon toggle button in an [OdsImageTile].
 *
 * @param checked Specify if icon is currently checked
 * @param onCheckedChange Callback to be invoked when this icon is selected
 * @param checkedIcon Icon displayed in front of the [OdsImageTile] when icon is checked
 * @param uncheckedIcon Icon displayed in front of the [OdsImageTile] when icon is unchecked
 */
class OdsImageTileIconToggleButton(
    val checked: Boolean,
    val onCheckedChange: (Boolean) -> Unit,
    val checkedIcon: OdsIconButtonIcon,
    val uncheckedIcon: OdsIconButtonIcon,
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

enum class OdsImageTileLegendAreaDisplayType {
    Below, Overlay, None
}

@Composable
private fun OdsImageTileLegendArea(
    text: String,
    color: Color,
    displaySurface: OdsDisplaySurface,
    icon: OdsImageTileIconToggleButton?,
    modifier: Modifier
) {
    Text(
        text = text,
        color = color,
        modifier = modifier,
        style = OdsTheme.typography.subtitle1,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
    icon?.Content(displaySurface)
}

@UiModePreviews.ImageTile
@Composable
private fun PreviewOdsImageTile(@PreviewParameter(OdsImageTilePreviewParameterProvider::class) parameter: OdsImageTilePreviewParameter) =
    Preview {
        OdsImageTile(
            image = OdsImageTileImage(painterResource(id = parameter.image), ""),
            title = parameter.title,
            legendAreaDisplayType = parameter.type,
            icon = OdsImageTileIconToggleButton(
                uncheckedIcon = OdsIconButtonIcon(painterResource(id = parameter.uncheckedIcon), "click to select"),
                checkedIcon = OdsIconButtonIcon(painterResource(id = parameter.checkedIcon), "click to unselect"),
                checked = parameter.checked,
                onCheckedChange = { parameter.checked },
            )
        )
    }

private data class OdsImageTilePreviewParameter(
    val image: Int,
    val checked: Boolean,
    val title: String,
    val checkedIcon: Int,
    val uncheckedIcon: Int,
    val type: OdsImageTileLegendAreaDisplayType
)

private class OdsImageTilePreviewParameterProvider :
    BasicPreviewParameterProvider<OdsImageTilePreviewParameter>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<OdsImageTilePreviewParameter>
    get() {
        val title = "Subtitle 1"
        val image = R.drawable.placeholder
        val checkedIcon = R.drawable.ic_check
        val uncheckedIcon = R.drawable.ic_check

        return listOf(
            OdsImageTilePreviewParameter(
                image,
                title = title,
                checkedIcon = checkedIcon,
                uncheckedIcon = uncheckedIcon,
                checked = false,
                type = OdsImageTileLegendAreaDisplayType.Below
            ),
            OdsImageTilePreviewParameter(
                image,
                title = title,
                checkedIcon = checkedIcon,
                uncheckedIcon = uncheckedIcon,
                checked = false,
                type = OdsImageTileLegendAreaDisplayType.Overlay
            ),
            OdsImageTilePreviewParameter(
                image,
                title = title,
                checkedIcon = checkedIcon,
                uncheckedIcon = uncheckedIcon,
                checked = true,
                type = OdsImageTileLegendAreaDisplayType.None
            )
        )
    }