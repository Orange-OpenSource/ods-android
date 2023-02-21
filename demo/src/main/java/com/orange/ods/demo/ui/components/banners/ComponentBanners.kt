/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.components.banners

import androidx.compose.foundation.layout.Column
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.compose.component.OdsComponent
import com.orange.ods.compose.component.banner.OdsBanner
import com.orange.ods.compose.component.list.OdsListItem
import com.orange.ods.compose.component.list.OdsSwitchTrailing
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.components.utilities.ComponentCustomizationBottomSheetScaffold
import com.orange.ods.demo.ui.utilities.composable.CodeImplementationColumn
import com.orange.ods.demo.ui.utilities.composable.CommonTechnicalTextColumn
import com.orange.ods.demo.ui.utilities.composable.TechnicalText

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ComponentBanners() {

    val actionIconChecked = rememberSaveable { mutableStateOf(false) }
    val actionOnNewLineChecked = rememberSaveable { mutableStateOf(false) }
    if (!actionOnNewLineChecked.value) {
        actionIconChecked.value = false
    }

    ComponentCustomizationBottomSheetScaffold(
        bottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
        bottomSheetContent = {
            OdsListItem(
                text = stringResource(id = R.string.component_banners_twoButtom),
                trailing = OdsSwitchTrailing(checked = actionOnNewLineChecked)
            )
            OdsListItem(
                text = stringResource(id = R.string.component_banners_image),
                trailing = OdsSwitchTrailing(checked = actionIconChecked, enabled = actionOnNewLineChecked.value)
            )
        }
    ) {
        Column {
            OdsBanner(
                message = if (actionOnNewLineChecked.value) stringResource(id = R.string.component_banners_twoLine) else stringResource(id = R.string.component_banners_oneLine),
                actionLabel = stringResource(id = R.string.component_snackbar_action_label),
                actionOnNewLine = !actionOnNewLineChecked.value,
                image = if (actionIconChecked.value) painterResource(id = com.orange.ods.R.drawable.placeholder) else null
            )

            CodeImplementationColumn {
                CommonTechnicalTextColumn(
                    componentName = OdsComponent.OdsBanner.name
                ) {
                    if (actionOnNewLineChecked.value) TechnicalText(text = " message = \"${stringResource(id = R.string.component_banners_twoLine)}\"")
                    else TechnicalText(text = "Message : \"${stringResource(id = R.string.component_banners_oneLine)}\"")
                    TechnicalText("")
                    if (actionIconChecked.value) TechnicalText(text = " image = painterResource(id = R.drawable.placeholder)")

                }
            }
        }
    }
}

