/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.compose.component.navigationdrawer

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.DrawerDefaults
import androidx.compose.material.DrawerState
import androidx.compose.material.DrawerValue
import androidx.compose.material.ModalDrawer
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.contentColorFor
import androidx.compose.material.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.orange.ods.R
import com.orange.ods.compose.component.OdsComponentApi
import com.orange.ods.compose.component.divider.OdsDivider
import com.orange.ods.compose.component.list.OdsListItem
import com.orange.ods.compose.component.list.OdsListItemIcon
import com.orange.ods.compose.component.list.OdsListItemIconType
import com.orange.ods.compose.component.list.iconType
import com.orange.ods.compose.component.utilities.BasicPreviewParameterProvider
import com.orange.ods.compose.component.utilities.OdsImageCircleShape
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.text.OdsTextBody2
import com.orange.ods.compose.theme.OdsTheme

private val DrawerHeaderMaxHeight = 167.dp

/**
 * Navigation drawers provide ergonomic access to destinations in an app.
 *
 * @param drawerHeader content inside the header of the drawer
 * @param drawerContentList content inside the body of the drawer
 * @param modifier to be applied to this drawer
 * @param drawerState state of the drawer
 * @param content content of the rest of the UI
 */
@Composable
@OdsComponentApi
fun OdsModalDrawer(
    drawerHeader: OdsModalDrawerHeader,
    drawerContentList: List<OdsModalDrawerItem>,
    modifier: Modifier = Modifier,
    drawerState: DrawerState = rememberDrawerState(DrawerValue.Closed),
    content: @Composable () -> Unit
) {
    ModalDrawer(
        drawerContent = {
            ModalDrawerHeader(drawerHeader = drawerHeader)
            OdsDivider()
            LazyColumn {
                items(drawerContentList) { item ->
                    ModalDrawerItem(item = item)
                }
            }
        },
        drawerState = drawerState,
        modifier = modifier,
        drawerBackgroundColor = OdsTheme.colors.surface,
        drawerContentColor = contentColorFor(OdsTheme.colors.onSurface),
        scrimColor = DrawerDefaults.scrimColor,
        content = content
    )
}

sealed class OdsModalDrawerItem
class OdsModalDrawerSectionLabel(val label: String) : OdsModalDrawerItem()
object OdsModalDrawerDivider : OdsModalDrawerItem()
class OdsModalDrawerListItem(@DrawableRes val icon: Int?, val text: String) : OdsModalDrawerItem()

@Composable
private fun ModalDrawerItem(item: OdsModalDrawerItem) {
    return when (item) {
        is OdsModalDrawerSectionLabel -> {
            Column {
                OdsDivider()
                OdsTextBody2(
                    modifier = Modifier.padding(top = dimensionResource(id = R.dimen.spacing_m), start = dimensionResource(id = R.dimen.spacing_m)),
                    text = item.label
                )
            }
        }
        is OdsModalDrawerListItem -> {

            OdsListItem(
                modifier = Modifier
                    .iconType(OdsListItemIconType.Icon),
                text = item.text,
                icon = item.icon?.let { { OdsListItemIcon(painterResource(id = it)) } })

        }
        is OdsModalDrawerDivider -> {
            OdsDivider()
        }
    }
}

enum class OdsModalDrawerHeaderImageDisplayType {

    /** A avatar icon. */
    Avatar,

    /** An background image */
    Background,

    None
}

data class OdsModalDrawerHeader(
    var modifier: Modifier = Modifier,
    var title: String,
    var imageDisplayType: OdsModalDrawerHeaderImageDisplayType = OdsModalDrawerHeaderImageDisplayType.None,
    var imageContentDescription: String? = null,
    var image: Painter? = null,
    var subtitle: String? = null
)

@Composable
private fun ModalDrawerHeader(
    drawerHeader: OdsModalDrawerHeader
) {
    if (drawerHeader.imageDisplayType == OdsModalDrawerHeaderImageDisplayType.Background) {
        Box(
            modifier = drawerHeader.modifier
                .fillMaxWidth()
        ) {
            drawerHeader.image?.let { backgroundPainter ->
                Image(
                    painter = backgroundPainter,
                    contentDescription = drawerHeader.imageContentDescription,
                    contentScale = ContentScale.Crop,
                    modifier = drawerHeader.modifier
                        .fillMaxWidth()
                        .height(DrawerHeaderMaxHeight)
                )
            }
            Surface(
                color = Color.Black.copy(alpha = 0.8f),
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .fillMaxWidth()
            ) {
                OdsHeaderText(headerContent = drawerHeader, color = Color.White)
            }
        }
    }

    if (drawerHeader.imageDisplayType == OdsModalDrawerHeaderImageDisplayType.Avatar) {
        Column(
            modifier = drawerHeader.modifier
                .fillMaxWidth()
                .height(DrawerHeaderMaxHeight),
            verticalArrangement = Arrangement.Bottom
        ) {
            drawerHeader.image?.let {
                OdsImageCircleShape(
                    painter = it,
                    Modifier.padding(start = dimensionResource(id = R.dimen.spacing_m), bottom = dimensionResource(id = R.dimen.spacing_m))
                )
            }
            OdsHeaderText(headerContent = drawerHeader, color = OdsTheme.colors.onSurface)
        }
    }
    if (drawerHeader.imageDisplayType == OdsModalDrawerHeaderImageDisplayType.None) OdsHeaderText(
        headerContent = drawerHeader,
        color = OdsTheme.colors.onSurface
    )
}

@Composable
private fun OdsHeaderText(headerContent: OdsModalDrawerHeader, color: Color) {
    Column(
        modifier = Modifier
            .padding(start = dimensionResource(id = R.dimen.spacing_m))
            .height(
                if (headerContent.subtitle != null) dimensionResource(id = R.dimen.list_two_line_with_icon_item_height)
                else dimensionResource(id = R.dimen.list_single_line_item_height)
            ),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = headerContent.title, color = color, style = OdsTheme.typography.h6)
        headerContent.subtitle?.let { Text(text = it, color = color, style = OdsTheme.typography.body2) }
    }
}

@UiModePreviews.Default
@Composable
private fun PreviewOdsModalDrawer(@PreviewParameter(OdsModalDrawerPreviewParameterProvider::class) parameter: OdsModalDrawerPreviewParameter) =
    Preview {
        OdsModalDrawer(
            drawerState = rememberDrawerState(DrawerValue.Open),
            content = {},
            drawerHeader = OdsModalDrawerHeader(
                title = parameter.title,
                subtitle = parameter.subtitle,
                imageDisplayType = parameter.imageDisplayType,
                image = parameter.image?.let { painterResource(id = it) },
            ),
            drawerContentList = parameter.list
        )
    }

private data class OdsModalDrawerPreviewParameter(
    val image: Int?,
    val title: String,
    val imageDisplayType: OdsModalDrawerHeaderImageDisplayType,
    val subtitle: String?,
    val list: List<OdsModalDrawerItem>
)

private class OdsModalDrawerPreviewParameterProvider :
    BasicPreviewParameterProvider<OdsModalDrawerPreviewParameter>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<OdsModalDrawerPreviewParameter>
    get() {
        val title = "Headline 6"
        val imageDisplayType = OdsModalDrawerHeaderImageDisplayType.None
        val image = R.drawable.placeholder
        val icon = R.drawable.ic_check
        val subtitle = "Body 2"
        val list = listOf(
            OdsModalDrawerListItem(icon, "label1"),
            OdsModalDrawerDivider,
            OdsModalDrawerListItem(icon, "label2"),
            OdsModalDrawerSectionLabel("Label"),
            OdsModalDrawerListItem(icon, "label3")
        )

        return listOf(
            OdsModalDrawerPreviewParameter(
                image = image,
                title = title,
                imageDisplayType = OdsModalDrawerHeaderImageDisplayType.Background,
                subtitle = subtitle,
                list
            ),
            OdsModalDrawerPreviewParameter(image = image, title, imageDisplayType = OdsModalDrawerHeaderImageDisplayType.Background, subtitle = null, list),
            OdsModalDrawerPreviewParameter(
                image = image,
                title = title,
                imageDisplayType = OdsModalDrawerHeaderImageDisplayType.Avatar,
                subtitle = subtitle,
                list
            ),
            OdsModalDrawerPreviewParameter(image = image, title = title, imageDisplayType = OdsModalDrawerHeaderImageDisplayType.Avatar, subtitle = null, list),
            OdsModalDrawerPreviewParameter(
                image = image, title = title, imageDisplayType = imageDisplayType, subtitle = null, list = emptyList()
            ),
            OdsModalDrawerPreviewParameter(
                image = image, title = title, imageDisplayType = imageDisplayType, subtitle = subtitle, list
            )
        )
    }