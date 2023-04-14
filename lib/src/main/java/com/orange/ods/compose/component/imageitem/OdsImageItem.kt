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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
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
 * @param image image display in the ImageList.
 * @param checkedIcon specified if icon is currently checkedIcon
 * @param onCheckedChange callback to be invoked when this icon is selected
 * @param modifier to be applied to this ImageList
 * @param imageContentDescription Optional image content description.
 * @param icon Optional icon display in front of the test.
 * @param iconContentDescription Optional icon content description..
 * @param title text display in the image
 */
@Composable
@OdsComponentApi
fun OdsImageItem(
    image: Painter,
    checkedIcon: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    iconContentDescription: String? = null,
    imageContentDescription: String? = null,
    icon: Painter? = null,
    title: String? = null,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Image(
            painter = image,
            contentDescription = imageContentDescription,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )
        title?.let {
            Surface(
                color = Color.Black.copy(alpha = 0.5f),
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .height(dimensionResource(id = R.dimen.list_single_line_item_height))
                ) {
                    Text(
                        text = it,
                        color = Color.White,
                        style = OdsTheme.typography.subtitle1,
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = dimensionResource(id = R.dimen.spacing_m), end = dimensionResource(id = R.dimen.spacing_m)),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    icon?.let {
                        OdsIconToggleButton(
                            checked = checkedIcon,
                            onCheckedChange = onCheckedChange,
                            uncheckedPainter = it,
                            checkedPainter = it,
                            iconContentDescription = iconContentDescription,
                            displaySurface = OdsDisplaySurface.Dark
                        )
                    }
                }
            }
        }
    }
}

@UiModePreviews.Default
@Composable
private fun PreviewOdsImageList(@PreviewParameter(OdsImageListPreviewParameterProvider::class) parameter: OdsImageListPreviewParameter) =
    Preview {
        OdsImageItem(
            image = painterResource(id = parameter.image),
            icon = parameter.icon?.let { painterResource(id = it) },
            title = parameter.title,
            checkedIcon = parameter.checked,
            iconContentDescription = "",
            onCheckedChange = { parameter.checked }
        )
    }

private data class OdsImageListPreviewParameter(
    val image: Int,
    val checked: Boolean,
    val title: String?,
    val icon: Int?
)

private class OdsImageListPreviewParameterProvider :
    BasicPreviewParameterProvider<OdsImageListPreviewParameter>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<OdsImageListPreviewParameter>
    get() {
        val title = "Subtitle 1"
        val image = R.drawable.placeholder
        val icon = R.drawable.ic_check

        return listOf(
            OdsImageListPreviewParameter(image, title = null, icon = icon, checked = false),
            OdsImageListPreviewParameter(image, title = title, icon = null, checked = false),
            OdsImageListPreviewParameter(image, title = title, icon = icon, checked = true)
        )
    }