/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.app.R
import com.orange.ods.app.ui.utilities.DrawableManager
import com.orange.ods.app.ui.utilities.composable.DetailScreenHeader
import com.orange.ods.compose.component.list.OdsListItem
import com.orange.ods.compose.component.list.OdsListItemIconBuilder
import com.orange.ods.compose.component.list.OdsListItemIconType

@Composable
fun ComponentDetailScreen(
    component: Component,
    onVariantClick: (Long) -> Unit,
    onDemoClick: () -> Unit
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(bottom = dimensionResource(id = com.orange.ods.R.dimen.screen_vertical_margin))
    ) {
        DetailScreenHeader(
            imageRes = DrawableManager.getDrawableResIdForCurrentTheme(resId = component.imageRes),
            imageAlignment = component.imageAlignment,
            descriptionRes = component.descriptionRes
        )
        Column(
            modifier = Modifier.padding(top = dimensionResource(id = com.orange.ods.R.dimen.spacing_m))
        ) {
            if (component.variants.isEmpty()) {
                ComponentDetailLinkItem(
                    label = context.getString(R.string.component_demo, context.getString(component.titleRes)),
                    composableName = component.composableName
                ) { onDemoClick() }
            } else {
                component.variants.forEach { variant ->
                    ComponentDetailLinkItem(label = stringResource(id = variant.titleRes), composableName = variant.composableName) {
                        onVariantClick(variant.id)
                    }
                }
            }

        }
    }
}

@Composable
private fun ComponentDetailLinkItem(label: String, composableName: String?, onClick: () -> Unit) {
    OdsListItem(
        icon = OdsListItemIconBuilder(OdsListItemIconType.Icon, painterResource(id = R.drawable.ic_play_outline), ""),
        text = label,
        secondaryText = composableName,
        onClick = onClick
    )
}