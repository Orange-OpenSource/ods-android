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

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.DrawerDefaults
import androidx.compose.material.DrawerState
import androidx.compose.material.DrawerValue
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalDrawer
import androidx.compose.material.Surface
import androidx.compose.material.contentColorFor
import androidx.compose.material.rememberDrawerState
import androidx.compose.runtime.Composable
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
import com.orange.ods.compose.component.utilities.BasicPreviewParameterProvider
import com.orange.ods.compose.component.utilities.OdsImageCircleShape
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.text.OdsTextBody2
import com.orange.ods.compose.text.OdsTextH6
import com.orange.ods.compose.text.OdsTextSubtitle2
import com.orange.ods.compose.theme.OdsTheme

@Composable
@OdsComponentApi
fun OdsModalDrawer(
    modifier: Modifier = Modifier,
    drawerState: DrawerState = rememberDrawerState(DrawerValue.Closed),
    content: @Composable () -> Unit,
    headerParametersProvider: OdsModalDrawerHeaderParametersProvider,
    //firstList: List<Any>,

) {
    ModalDrawer(
        drawerContent = {
            OdsModalDrawerHeader(headerContent = headerParametersProvider)
            Divider()
            /*firstList.forEach { recipe ->
                OdsListItem(
                    Modifier
                        .iconType(OdsListItemIconType.Icon),
                    //icon = if hasIcon painterResource (id =) else null,
                    text = recipe.toString()
                )
            }*/
        },
        drawerState = drawerState,
        modifier = modifier,
        drawerBackgroundColor = MaterialTheme.colors.surface,
        scrimColor = DrawerDefaults.scrimColor,
        content = content
    )
}

@Composable
fun OdsModalDrawer(
    modifier: Modifier = Modifier,
    drawerState: DrawerState = rememberDrawerState(DrawerValue.Closed),
    gesturesEnabled: Boolean = true,
    content: @Composable () -> Unit,
    //header: @Composable () -> Unit,
) {
    ModalDrawer(
        drawerContent = {
            Divider()
        },
        drawerState = drawerState,
        modifier = modifier,
        gesturesEnabled = gesturesEnabled,
        drawerElevation = DrawerDefaults.Elevation,
        drawerBackgroundColor = OdsTheme.colors.surface,
        drawerContentColor = contentColorFor(OdsTheme.colors.surface),
        scrimColor = DrawerDefaults.scrimColor,
        content = content
    )
}

sealed class OdsModalDrawerItems
class OdsModalDrawerText(val text: String) : OdsModalDrawerItems()
class OdsModalDrawerList(val list: List<Any>) : OdsModalDrawerItems()



data class OdsModalDrawerHeaderParametersProvider(
    var modifier: Modifier = Modifier,
    var title: String,
    var imageContentDescription: String? = null,
    var backgroundImage: Painter? = null,
    var subtitle: String? = null,
    var avatar: Painter? = null
)

@Composable
internal fun OdsModalDrawerHeader(
    headerContent: OdsModalDrawerHeaderParametersProvider
) {
    Box(
        modifier = if (headerContent.backgroundImage != null || headerContent.avatar != null
        ) {
            headerContent.modifier
                .fillMaxWidth()
                .height(167.dp)
        } else if (headerContent.subtitle != null) {
            headerContent.modifier
                .fillMaxWidth()
                .height(67.dp)
        } else {
            headerContent.modifier
                .fillMaxWidth()
                .height(48.dp)
        }

    ) {
        if (headerContent.backgroundImage == null) {
            Column(
                if (headerContent.avatar != null) {
                    Modifier.padding(start = dimensionResource(id = R.dimen.spacing_m), top = 40.dp)
                } else {
                    Modifier.padding(start = dimensionResource(id = R.dimen.spacing_m), top = 10.dp)
                }
            ) {
                headerContent.avatar?.let {
                    OdsImageCircleShape(painter = it)
                }
                if (headerContent.avatar != null) OdsTextH6(text = headerContent.title, Modifier.padding(top = 30.dp))
                else OdsTextH6(text = headerContent.title)
                headerContent.subtitle?.let {
                    OdsTextSubtitle2(text = it)
                }
            }
        } else {
            headerContent.backgroundImage?.let {
                Image(
                    painter = it,
                    contentDescription = headerContent.imageContentDescription,
                    contentScale = ContentScale.Crop,
                    modifier = headerContent.modifier
                        .fillMaxWidth()
                )
            }
            Surface(
                color = Color.Black.copy(alpha = 0.6f),
                modifier = if (headerContent.subtitle != null) {
                    Modifier
                        .fillMaxSize()
                        .padding(top = 98.dp)
                } else {
                    Modifier
                        .fillMaxSize()
                        .padding(top = 119.dp)
                }
            ) {
                Column(
                    modifier = Modifier
                        .padding(start = dimensionResource(id = R.dimen.spacing_m), top = 12.dp, bottom = 12.dp)
                ) {
                    OdsTextH6(text = headerContent.title)
                    headerContent.subtitle?.let {
                        OdsTextBody2(text = it)
                    }
                }
            }
        }
    }

}

@UiModePreviews.Default
@Composable
private fun PreviewOdsModalDrawer(@PreviewParameter(OdsModalDrawerPreviewParameterProvider::class) parameter: OdsModalDrawerPreviewParameter) =
    Preview {
        OdsModalDrawer(
            drawerState = rememberDrawerState(DrawerValue.Open),
            content = {},
            headerParametersProvider = OdsModalDrawerHeaderParametersProvider(
                title = parameter.title,
                subtitle = parameter.subtitle,
                avatar = parameter.avatar?.let { painterResource(id = it) },
                backgroundImage = parameter.backgroundImage?.let { painterResource(id = it) },
            )
        )
    }

private data class OdsModalDrawerPreviewParameter(
    val backgroundImage: Int?,
    val title: String,
    val avatar: Int?,
    val subtitle: String?
)

private class OdsModalDrawerPreviewParameterProvider :
    BasicPreviewParameterProvider<OdsModalDrawerPreviewParameter>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<OdsModalDrawerPreviewParameter>
    get() {
        val backgroundImage = R.drawable.placeholder_small
        val title = "Headline 6"
        val avatar = R.drawable.ic_check
        val subtitle = "Body 2"
        val noPicture = null

        return listOf(
            OdsModalDrawerPreviewParameter(backgroundImage, title, avatar = noPicture, subtitle = subtitle),
            OdsModalDrawerPreviewParameter(backgroundImage, title, avatar = noPicture, subtitle = noPicture),
            OdsModalDrawerPreviewParameter(backgroundImage = noPicture, title = title, avatar = avatar, subtitle = subtitle),
            OdsModalDrawerPreviewParameter(backgroundImage = noPicture, title = title, avatar = avatar, subtitle = noPicture),
            OdsModalDrawerPreviewParameter(backgroundImage = noPicture, title = title, avatar = noPicture, subtitle = noPicture),
            OdsModalDrawerPreviewParameter(backgroundImage = noPicture, title = title, avatar = noPicture, subtitle = subtitle),
        )
    }