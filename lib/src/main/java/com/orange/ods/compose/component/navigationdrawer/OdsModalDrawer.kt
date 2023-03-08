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

import androidx.compose.material.Divider
import androidx.compose.material.DrawerDefaults
import androidx.compose.material.DrawerState
import androidx.compose.material.DrawerValue
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalDrawer
import androidx.compose.material.contentColorFor
import androidx.compose.material.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.orange.ods.compose.component.OdsComponentApi
import com.orange.ods.compose.component.list.OdsListItem
import com.orange.ods.compose.component.list.OdsListItemIconType
import com.orange.ods.compose.component.list.iconType
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.component.utilities.UiModePreviews

@Composable
@OdsComponentApi
fun OdsModalDrawer(
    modifier: Modifier = Modifier,
    drawerState: DrawerState = rememberDrawerState(DrawerValue.Closed),
    gesturesEnabled: Boolean = true,
    drawerElevation: Dp = DrawerDefaults.Elevation,
    drawerBackgroundColor: Color = MaterialTheme.colors.surface,
    drawerContentColor: Color = contentColorFor(drawerBackgroundColor),
    scrimColor: Color = DrawerDefaults.scrimColor,
    content: @Composable () -> Unit,

    header: @Composable () -> Unit,
    firstList: List<Any>,
    /*imageBackgroundColor: String? = null,
    subtitle: String? = null,
    label: String? = null,
    hasIcon: Boolean? = true,
    secondList: List<Any>? = null

    Mettre ModalDrawer
    Une liste listItems: List<Any> creer un type modalDrawersItems
    des items, section label, divider
    creer des composables
    header: OdsModalDrawerHeader fonction a part entiere utilisé, backgroundImage, titre, sous titre, avatar
    */
) {
    ModalDrawer(
        drawerContent = {
            //header
            Divider()
            firstList.forEach { recipe ->
                OdsListItem(
                    Modifier
                        .iconType(OdsListItemIconType.Icon),
                    //icon = if hasIcon painterResource (id =) else null,
                    text = recipe.toString()
                )
            }
        },
        drawerState = drawerState,
        modifier = modifier,
        gesturesEnabled = gesturesEnabled,
        drawerElevation = drawerElevation,
        drawerBackgroundColor = MaterialTheme.colors.surface,
        drawerContentColor = drawerContentColor,
        scrimColor = scrimColor,
        content = content
    )
}

@Composable
fun OdsModalDrawer(
    modifier: Modifier = Modifier,
    drawerState: DrawerState = rememberDrawerState(DrawerValue.Closed),
    gesturesEnabled: Boolean = true,
    drawerElevation: Dp = DrawerDefaults.Elevation,
    drawerBackgroundColor: Color = MaterialTheme.colors.surface,
    drawerContentColor: Color = contentColorFor(drawerBackgroundColor),
    scrimColor: Color = DrawerDefaults.scrimColor,
    content: @Composable () -> Unit,
    //header: @Composable () -> Unit,
) {
    ModalDrawer(
        drawerContent = {
            //header
            Divider()
        },
        drawerState = drawerState,
        modifier = modifier,
        gesturesEnabled = gesturesEnabled,
        drawerElevation = drawerElevation,
        drawerBackgroundColor = MaterialTheme.colors.surface,
        drawerContentColor = drawerContentColor,
        scrimColor = scrimColor,
        content = content
    )
}

@UiModePreviews.Default
@Composable
private fun PreviewOdsNavigationDrawer() = Preview {

}

//private class OdsNavigationDrawerPreviewParameterProvider : BasicPreviewParameterProvider<Boolean>(false, true)
