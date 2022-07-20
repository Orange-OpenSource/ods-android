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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
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
    onVariantClick: (Long) -> Unit,
    updateTopBarTitle: (Int) -> Unit
) {
    val component = remember { components.firstOrNull { component -> component.id == componentId } }

    component?.let {
        updateTopBarTitle(component.titleRes)
        component.Detail(onVariantClick = onVariantClick)
    }
}

@ExperimentalMaterialApi
@Composable
fun ComponentDetail(component: Component, bottomBar: @Composable () -> Unit = {}, content: @Composable () -> Unit) {
    Scaffold(bottomBar = bottomBar) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(bottom = dimensionResource(id = R.dimen.spacing_m))
            ) {
                ComponentHeader(
                    imageRes = component.imageRes,
                    imageAlignment = component.imageAlignment,
                    description = component.descriptionRes
                )
                content()
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun ComponentDetailWithVariants(component: Component, onVariantClick: (Long) -> Unit) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(bottom = dimensionResource(id = R.dimen.ods_screen_vertical_margin))
    ) {
        ComponentHeader(
            imageRes = component.imageRes,
            imageAlignment = component.imageAlignment,
            description = component.descriptionRes
        )
        Column(
            modifier = Modifier.padding(top = dimensionResource(id = R.dimen.spacing_m))
        ) {
            component.variants.forEach { variant ->
                OdsListItem(text = stringResource(id = variant.titleRes), modifier = Modifier.clickable {
                    onVariantClick(variant.id)
                })
            }
        }
    }
}