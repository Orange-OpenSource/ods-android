/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.ui.components.imagelists

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.app.R
import com.orange.ods.app.ui.components.utilities.ComponentCustomizationBottomSheetScaffold
import com.orange.ods.compose.component.imagelist.OdsImageList
import com.orange.ods.compose.component.list.OdsListItem
import com.orange.ods.compose.component.list.OdsSwitchTrailing


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ComponentImageLists() {
    val imageListsCustomizationState = rememberImageListsCustomizationState()

    with(imageListsCustomizationState) {
        if (!hasText) {
            sideIcons.value = false
        }
        ComponentCustomizationBottomSheetScaffold(
            bottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
            bottomSheetContent = {
                OdsListItem(
                    text = stringResource(id = R.string.component_element_text),
                    trailing = OdsSwitchTrailing(checked = textDisplayed)
                )
                OdsListItem(
                    text = stringResource(id = R.string.component_element_icon),
                    trailing = OdsSwitchTrailing(checked = sideIcons, enabled = hasText)
                )
            }) {

            OdsImageList(
                image = painterResource(id = R.drawable.placeholder),
                icon = if (hasSideIcons) painterResource(id = R.drawable.ic_heart) else null,
                title = if (hasText) stringResource(id = R.string.component_image_lists) else null
            )

        }
    }
}