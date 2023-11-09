/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.compose.component.imageitem

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
import com.orange.ods.compose.component.button.OdsIconButtonIconBuilder
import com.orange.ods.compose.component.button.OdsIconToggleButton
import com.orange.ods.compose.component.content.OdsImageBuilder
import com.orange.ods.compose.component.utilities.BasicPreviewParameterProvider
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.theme.OdsDisplaySurface
import com.orange.ods.compose.theme.OdsTheme
import com.orange.ods.extension.orElse


/**
 * An image item contains primary information which is an image. It can also contain secondary information such as text or action. Image items have no more than two
 * actions. They are usually used in grids.
 *
 * @param image [OdsImageItemImageBuilder] displayed into the item.
 * @param legendAreaDisplayType Controls how the title and the icon are displayed relatively to the image. If set to [OdsImageItemLegendAreaDisplayType.None],
 * no legend area will be displayed.
 * @param modifier [Modifier] applied to the image item.
 * @param title Title displayed into the image item. It is linked to the image and displayed according to the [legendAreaDisplayType] value.
 * @param icon [OdsImageItemIconToggleButtonBuilder] displayed next to the title.
 * @param onClick Callback invoked on image item click.
 */
@Composable
@OdsComposable
fun OdsImageItem(
    image: OdsImageItemImageBuilder,
    legendAreaDisplayType: OdsImageItemLegendAreaDisplayType,
    modifier: Modifier = Modifier,
    title: String? = null,
    icon: OdsImageItemIconToggleButtonBuilder? = null,
    onClick: (() -> Unit)? = null,
) {
    Box(modifier = modifier.run {
        onClick?.let { clickable { onClick() } }.orElse { this }
    }) {
        when (legendAreaDisplayType) {
            OdsImageItemLegendAreaDisplayType.Overlay -> {
                image.Content(modifier = Modifier.fillMaxSize())
                title?.let {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .background(color = Color.Black.copy(alpha = 0.38f))
                            .align(Alignment.BottomStart)
                            .height(dimensionResource(id = R.dimen.list_single_line_item_height))
                    ) {
                        OdsImageItemLegendArea(
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

            OdsImageItemLegendAreaDisplayType.Below ->
                Column(verticalArrangement = Arrangement.Center) {
                    image.Content(
                        modifier = Modifier
                            .weight(1.0f)
                            .fillMaxWidth()
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .height(dimensionResource(id = R.dimen.image_item_legend_area_height))
                    ) {
                        title?.let {
                            OdsImageItemLegendArea(
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

            OdsImageItemLegendAreaDisplayType.None ->
                image.Content(modifier = Modifier.fillMaxSize())
        }
    }
}

/**
 * An image in an [OdsImageItem].
 */
class OdsImageItemImageBuilder : OdsImageBuilder<Nothing> {

    /**
     * Creates an instance of [OdsImageItemImageBuilder].
     *
     * @param painter The painter to draw.
     * @param contentDescription The content description associated to this [OdsImageItemImageBuilder].
     * @param contentScale The rule to apply to scale the image in this [OdsImageItemImageBuilder], [ContentScale.Crop] by default.
     */
    constructor(painter: Painter, contentDescription: String, contentScale: ContentScale = ContentScale.Crop) : super(
        painter,
        contentDescription,
        contentScale = contentScale
    )

    /**
     * Creates an instance of [OdsImageItemImageBuilder].
     *
     * @param imageVector The image vector to draw.
     * @param contentDescription The content description associated to this [OdsImageItemImageBuilder].
     * @param contentScale The rule to apply to scale the image in this [OdsImageItemImageBuilder], [ContentScale.Crop] by default.
     */
    constructor(imageVector: ImageVector, contentDescription: String, contentScale: ContentScale = ContentScale.Crop) : super(
        imageVector,
        contentDescription,
        contentScale = contentScale
    )

    /**
     * Creates an instance of [OdsImageItemImageBuilder].
     *
     * @param bitmap The image bitmap to draw.
     * @param contentDescription The content description associated to this [OdsImageItemImageBuilder].
     * @param contentScale The rule to apply to scale the image in this [OdsImageItemImageBuilder], [ContentScale.Crop] by default.
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
class OdsImageItemIconToggleButtonBuilder(
    val checked: Boolean,
    val onCheckedChange: (Boolean) -> Unit,
    val checkedIcon: OdsIconButtonIconBuilder,
    val uncheckedIcon: OdsIconButtonIconBuilder,
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

enum class OdsImageItemLegendAreaDisplayType {
    Below, Overlay, None
}

@Composable
private fun OdsImageItemLegendArea(
    text: String,
    color: Color,
    displaySurface: OdsDisplaySurface,
    icon: OdsImageItemIconToggleButtonBuilder?,
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

@UiModePreviews.ImageItem
@Composable
private fun PreviewOdsImageItem(@PreviewParameter(OdsImageItemPreviewParameterProvider::class) parameter: OdsImageItemPreviewParameter) =
    Preview {
        OdsImageItem(
            image = OdsImageItemImageBuilder(painterResource(id = parameter.image), ""),
            title = parameter.title,
            legendAreaDisplayType = parameter.type,
            icon = OdsImageItemIconToggleButtonBuilder(
                uncheckedIcon = OdsIconButtonIconBuilder(painterResource(id = parameter.uncheckedIcon), "click to select"),
                checkedIcon = OdsIconButtonIconBuilder(painterResource(id = parameter.checkedIcon), "click to unselect"),
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
    val type: OdsImageItemLegendAreaDisplayType
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
                type = OdsImageItemLegendAreaDisplayType.Below
            ),
            OdsImageItemPreviewParameter(
                image,
                title = title,
                checkedIcon = checkedIcon,
                uncheckedIcon = uncheckedIcon,
                checked = false,
                type = OdsImageItemLegendAreaDisplayType.Overlay
            ),
            OdsImageItemPreviewParameter(
                image,
                title = title,
                checkedIcon = checkedIcon,
                uncheckedIcon = uncheckedIcon,
                checked = true,
                type = OdsImageItemLegendAreaDisplayType.None
            )
        )
    }