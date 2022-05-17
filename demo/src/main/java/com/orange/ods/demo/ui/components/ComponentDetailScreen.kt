/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.compose.component.list.OdsListItem
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.components.utilities.ComponentHeader

@ExperimentalMaterialApi
@Composable
fun ComponentDetailScreen(
    componentId: Long,
    onSubComponentClick: (Long) -> Unit,
    updateTopBarTitle: (Int) -> Unit,
) {
    val component = remember { components.firstOrNull { component -> component.id == componentId } }

    component?.let {
        updateTopBarTitle(component.titleRes)
        component.Detail(onSubComponentClick = onSubComponentClick)
    }
}

@ExperimentalMaterialApi
@Composable
fun ComponentDetail(component: Component, ComponentContent: @Composable () -> Unit) {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        ComponentHeader(
            imageRes = component.imageRes,
            description = component.descriptionRes
        )
        ComponentContent()
    }
}

@ExperimentalMaterialApi
@Composable
fun ComponentDetailWithSubComponents(component: Component, onSubComponentClick: (Long) -> Unit) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(bottom = dimensionResource(id = R.dimen.ods_screen_vertical_margin))
    ) {
        ComponentHeader(
            imageRes = component.imageRes,
            description = component.descriptionRes
        )
        Column(
            modifier = Modifier.padding(top = dimensionResource(id = R.dimen.ods_spacing_s))
        ) {
            component.subComponents.forEach { subComponent ->
                OdsListItem(text = stringResource(id = subComponent.titleRes), modifier = Modifier.clickable {
                    onSubComponentClick(subComponent.id)
                })
            }
        }
    }
}