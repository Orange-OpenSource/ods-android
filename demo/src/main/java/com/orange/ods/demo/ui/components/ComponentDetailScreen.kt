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
import com.orange.ods.demo.ui.components.bottomnavigation.ComponentBottomNavigation
import com.orange.ods.demo.ui.components.buttons.ComponentButtons
import com.orange.ods.demo.ui.components.progress.ComponentProgress
import com.orange.ods.demo.ui.components.utilities.ComponentHeader

@ExperimentalMaterialApi
@Composable
fun ComponentDetailScreen(
    componentId: Long,
    onSubComponentClick: (Long) -> Unit,
    updateTopBarTitle: (Int) -> Unit
) {
    val component = remember { components.firstOrNull { component -> component.id == componentId } }

    component?.let {
        updateTopBarTitle(component.titleRes)
        when (component) {
            Component.BottomNavigation -> ComponentBottomNavigation()
            Component.Buttons -> ComponentButtons()
            Component.Progress -> ComponentProgress()
            Component.Cards, Component.Controls, Component.Lists -> SubComponents(component = component, onSubComponentClick = onSubComponentClick)
        }
    }
}

@ExperimentalMaterialApi
@Composable
private fun SubComponents(component: Component, onSubComponentClick: (Long) -> Unit) {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
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