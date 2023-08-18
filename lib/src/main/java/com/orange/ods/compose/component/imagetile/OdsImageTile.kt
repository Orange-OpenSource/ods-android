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
 * A image tile contains primary information which is an image. It can also contain secondary information such as text or action. Tiles have no more than two
 * actions. They are usually used in grids.
 *
 * @param image Image display in the [OdsImageTile].
 * @param captionDisplayType Specify how the title and the icon are displayed relatively to the image. If set to [OdsImageTileCaptionDisplayType.None],
 * no caption will be displayed.
 * @param modifier Modifier to be applied to this [OdsImageTile]
 * @param onClick Callback to be invoked on tile click.
 * @param title Title linked to the image. It is displayed according the [captionDisplayType] value.
 * @param iconChecked Specify if icon is currently checked
 * @param onIconCheckedChange Callback to be invoked when this icon is selected
 * @param checkedIcon Icon displayed in front of the [OdsImageTile] when icon is checked
 * @param uncheckedIcon Icon displayed in front of the [OdsImageTile] when icon is unchecked
 */
@Composable
@OdsComposable
fun OdsImageTile(
    image: OdsImageTileImage,
    captionDisplayType: OdsImageTileCaptionDisplayType,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    title: String? = null,
    iconChecked: Boolean = false,
    onIconCheckedChange: (Boolean) -> Unit = {},
    checkedIcon: OdsIconButtonIcon? = null,
    uncheckedIcon: OdsIconButtonIcon? = null,
) {
    Box(modifier = modifier.let { m ->
        onClick?.let { m.clickable { onClick() } }.orElse { m }
    }) {
        when (captionDisplayType) {
            OdsImageTileCaptionDisplayType.Overlay -> {
                image.Content(modifier = Modifier.fillMaxSize())
                title?.let {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .background(color = Color.Black.copy(alpha = 0.38f))
                            .align(Alignment.BottomStart)
                            .height(dimensionResource(id = R.dimen.list_single_line_item_height))
                    ) {
                        OdsImageTileCaption(
                            text = it,
                            iconChecked = iconChecked,
                            color = Color.White,
                            onIconCheckedChange = onIconCheckedChange,
                            uncheckedIcon = uncheckedIcon,
                            checkedIcon = checkedIcon,
                            displaySurface = OdsDisplaySurface.Dark,
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = dimensionResource(id = R.dimen.spacing_m), end = dimensionResource(id = R.dimen.spacing_m)),
                        )
                    }
                }
            }

            OdsImageTileCaptionDisplayType.Below ->
                Column(verticalArrangement = Arrangement.Center) {
                    image.Content(
                        modifier = Modifier
                            .weight(1.0f)
                            .fillMaxWidth()
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .height(dimensionResource(id = R.dimen.image_item_title_height))
                    ) {
                        title?.let {
                            OdsImageTileCaption(
                                text = it,
                                iconChecked = iconChecked,
                                color = OdsTheme.colors.onSurface,
                                onIconCheckedChange = onIconCheckedChange,
                                uncheckedIcon = uncheckedIcon,
                                checkedIcon = checkedIcon,
                                displaySurface = OdsDisplaySurface.Default,
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(end = dimensionResource(id = R.dimen.spacing_m)),
                            )
                        }
                    }
                }

            OdsImageTileCaptionDisplayType.None ->
                image.Content(modifier = Modifier.fillMaxSize())
        }
    }
}

/**
 * An image in an [OdsImageTile].
 */
class OdsImageTileImage : OdsComponentImage {

    /**
     * Creates an instance of [OdsImageTileImage].
     *
     * @param painter The painter to draw.
     * @param contentDescription The content description associated to this [OdsImageTileImage].
     * @param contentScale The rule to apply to scale the image in this [OdsImageTileImage], [ContentScale.Crop] by default.
     */
    constructor(painter: Painter, contentDescription: String, contentScale: ContentScale = ContentScale.Crop) : super(
        painter as Any,
        contentDescription,
        contentScale
    )

    /**
     * Creates an instance of [OdsImageTileImage].
     *
     * @param imageVector The image vector to draw.
     * @param contentDescription The content description associated to this [OdsImageTileImage].
     * @param contentScale The rule to apply to scale the image in this [OdsImageTileImage], [ContentScale.Crop] by default.
     */
    constructor(imageVector: ImageVector, contentDescription: String, contentScale: ContentScale = ContentScale.Crop) : super(
        imageVector as Any,
        contentDescription,
        contentScale
    )

    /**
     * Creates an instance of [OdsImageTileImage].
     *
     * @param bitmap The image bitmap to draw.
     * @param contentDescription The content description associated to this [OdsImageTileImage].
     * @param contentScale The rule to apply to scale the image in this [OdsImageTileImage], [ContentScale.Crop] by default.
     */
    constructor(bitmap: ImageBitmap, contentDescription: String, contentScale: ContentScale = ContentScale.Crop) : super(
        bitmap as Any,
        contentDescription,
        contentScale
    )
}

enum class OdsImageTileCaptionDisplayType {
    Below, Overlay, None
}

@Composable
private fun OdsImageTileCaption(
    text: String,
    iconChecked: Boolean,
    color: Color,
    onIconCheckedChange: (Boolean) -> Unit,
    uncheckedIcon: OdsIconButtonIcon?,
    checkedIcon: OdsIconButtonIcon?,
    modifier: Modifier,
    displaySurface: OdsDisplaySurface,
) {
    Text(
        text = text,
        color = color,
        modifier = modifier,
        style = OdsTheme.typography.subtitle1,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
    if (uncheckedIcon != null && checkedIcon != null) {
        OdsIconToggleButton(
            checked = iconChecked,
            onCheckedChange = onIconCheckedChange,
            uncheckedIcon = uncheckedIcon,
            checkedIcon = checkedIcon,
            displaySurface = displaySurface
        )
    }
}

@UiModePreviews.ImageTile
@Composable
private fun PreviewOdsImageList(@PreviewParameter(OdsImageListPreviewParameterProvider::class) parameter: OdsImageListPreviewParameter) =
    Preview {
        OdsImageTile(
            image = OdsImageTileImage(painterResource(id = parameter.image), ""),
            uncheckedIcon = OdsIconButtonIcon(painterResource(id = parameter.uncheckedIcon), "click to select"),
            checkedIcon = OdsIconButtonIcon(painterResource(id = parameter.checkedIcon), "click to unselect"),
            title = parameter.title,
            iconChecked = parameter.checked,
            onIconCheckedChange = { parameter.checked },
            captionDisplayType = parameter.type
        )
    }

private data class OdsImageListPreviewParameter(
    val image: Int,
    val checked: Boolean,
    val title: String,
    val checkedIcon: Int,
    val uncheckedIcon: Int,
    val type: OdsImageTileCaptionDisplayType
)

private class OdsImageListPreviewParameterProvider :
    BasicPreviewParameterProvider<OdsImageListPreviewParameter>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<OdsImageListPreviewParameter>
    get() {
        val title = "Subtitle 1"
        val image = R.drawable.placeholder
        val checkedIcon = R.drawable.ic_check
        val uncheckedIcon = R.drawable.ic_check

        return listOf(
            OdsImageListPreviewParameter(
                image,
                title = title,
                checkedIcon = checkedIcon,
                uncheckedIcon = uncheckedIcon,
                checked = false,
                type = OdsImageTileCaptionDisplayType.Below
            ),
            OdsImageListPreviewParameter(
                image,
                title = title,
                checkedIcon = checkedIcon,
                uncheckedIcon = uncheckedIcon,
                checked = false,
                type = OdsImageTileCaptionDisplayType.Overlay
            ),
            OdsImageListPreviewParameter(
                image,
                title = title,
                checkedIcon = checkedIcon,
                uncheckedIcon = uncheckedIcon,
                checked = true,
                type = OdsImageTileCaptionDisplayType.None
            )
        )
    }