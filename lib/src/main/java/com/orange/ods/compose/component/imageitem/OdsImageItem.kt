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

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.orange.ods.R
import com.orange.ods.compose.component.OdsComponentApi
import com.orange.ods.compose.component.button.OdsIconToggleButton
import com.orange.ods.compose.component.utilities.BasicPreviewParameterProvider
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.theme.OdsDisplaySurface
import com.orange.ods.compose.theme.OdsTheme


/**
 *
 * @param image Image display in the [OdsImageItem].
 * @param iconChecked Specified if icon is currently checked
 * @param onIconCheckedChange Callback to be invoked when this icon is selected
 * @param displayTitle Specified how the title and icon are displayed relative to image
 * @param modifier Modifier to be applied to this [OdsImageItem]
 * @param checkedIcon Icon displayed in front of the [OdsImageItem] when icon is checked
 * @param uncheckedIcon Icon displayed in front of the [OdsImageItem] when icon is unchecked
 * @param imageContentDescription Optional image content description
 * @param iconContentDescription Optional icon content description
 * @param title Text displayed in the image
 */
@Composable
@OdsComponentApi
fun OdsImageItem(
    image: Painter,
    iconChecked: Boolean,
    onIconCheckedChange: (Boolean) -> Unit,
    displayTitle: OdsImageItemTitleType,
    modifier: Modifier = Modifier,
    checkedIcon: Painter? = null,
    uncheckedIcon: Painter? = null,
    iconContentDescription: String? = null,
    imageContentDescription: String? = null,
    title: String? = null,
) {
    Box(modifier = modifier) {
        when (displayTitle) {
            OdsImageItemTitleType.Overlay -> {
                Image(
                    painter = image,
                    contentDescription = imageContentDescription,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                )
                title?.let {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .background(color = Color.Black.copy(alpha = 0.38f))
                            .align(Alignment.BottomStart)
                            .height(dimensionResource(id = R.dimen.list_single_line_item_height))
                    ) {
                        OdsImageItemText(
                            text = it,
                            iconChecked = iconChecked,
                            color = Color.White,
                            onIconCheckedChange = onIconCheckedChange,
                            uncheckedIcon = uncheckedIcon,
                            checkedIcon = checkedIcon,
                            iconContentDescription = iconContentDescription,
                            displaySurface = OdsDisplaySurface.Dark,
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = dimensionResource(id = R.dimen.spacing_m), end = dimensionResource(id = R.dimen.spacing_m)),
                        )
                    }
                }
            }

            OdsImageItemTitleType.Below ->
                Column(
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        modifier = Modifier
                            .weight(1.0f)
                            .fillMaxWidth(),
                        painter = image,
                        contentDescription = imageContentDescription,
                        contentScale = ContentScale.Crop,
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .height(dimensionResource(id = R.dimen.image_item_title_height))
                    ) {
                        title?.let {
                            OdsImageItemText(
                                text = it,
                                iconChecked = iconChecked,
                                color = OdsTheme.colors.onSurface,
                                onIconCheckedChange = onIconCheckedChange,
                                uncheckedIcon = uncheckedIcon,
                                checkedIcon = checkedIcon,
                                iconContentDescription = iconContentDescription,
                                displaySurface = OdsDisplaySurface.Default,
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(end = dimensionResource(id = R.dimen.spacing_m)),
                            )
                        }
                    }
                }

            OdsImageItemTitleType.None ->
                Image(
                    painter = image,
                    contentDescription = imageContentDescription,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                )
        }
    }
}

enum class OdsImageItemTitleType {
    Below, Overlay, None
}

@Composable
private fun OdsImageItemText(
    text: String,
    iconChecked: Boolean,
    color: Color,
    onIconCheckedChange: (Boolean) -> Unit,
    uncheckedIcon: Painter?,
    checkedIcon: Painter?,
    modifier: Modifier,
    displaySurface: OdsDisplaySurface,
    iconContentDescription: String?
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
            uncheckedPainter = uncheckedIcon,
            checkedPainter = checkedIcon,
            iconContentDescription = iconContentDescription,
            displaySurface = displaySurface
        )
    }
}

@UiModePreviews.ImageItem
@Composable
private fun PreviewOdsImageList(@PreviewParameter(OdsImageListPreviewParameterProvider::class) parameter: OdsImageListPreviewParameter) =
    Preview {
        OdsImageItem(
            image = painterResource(id = parameter.image),
            uncheckedIcon = painterResource(id = parameter.uncheckedIcon),
            checkedIcon = painterResource(id = parameter.checkedIcon),
            title = parameter.title,
            iconChecked = parameter.checked,
            iconContentDescription = "",
            onIconCheckedChange = { parameter.checked },
            displayTitle = parameter.type
        )
    }

private data class OdsImageListPreviewParameter(
    val image: Int,
    val checked: Boolean,
    val title: String,
    val checkedIcon: Int,
    val uncheckedIcon: Int,
    val type: OdsImageItemTitleType
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
                type = OdsImageItemTitleType.Below
            ),
            OdsImageListPreviewParameter(
                image,
                title = title,
                checkedIcon = checkedIcon,
                uncheckedIcon = uncheckedIcon,
                checked = false,
                type = OdsImageItemTitleType.Overlay
            ),
            OdsImageListPreviewParameter(
                image,
                title = title,
                checkedIcon = checkedIcon,
                uncheckedIcon = uncheckedIcon,
                checked = true,
                type = OdsImageItemTitleType.None
            )
        )
    }