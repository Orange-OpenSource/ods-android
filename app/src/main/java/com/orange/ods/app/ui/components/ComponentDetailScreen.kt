/*
 * Software Name: Orange Design System
 * SPDX-FileCopyrightText: Copyright (c) Orange SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT license,
 * the text of which is available at https://opensource.org/license/MIT/
 * or see the "LICENSE" file for more details.
 *
 * Software description: Android library of reusable graphical components 
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
import com.orange.ods.compose.component.listitem.OdsListItem
import com.orange.ods.compose.theme.OdsTheme

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
            descriptionRes = component.descriptionRes,
            imageRes = DrawableManager.getDrawableResIdForCurrentTheme(resId = component.imageRes),
            imageAlignment = component.imageAlignment,
        )
        Column(
            modifier = Modifier.padding(top = OdsTheme.spacings.medium.dp)
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
        leadingIcon = OdsListItem.LeadingIcon(OdsListItem.LeadingIcon.Type.Icon, painterResource(id = R.drawable.ic_play_outline), ""),
        text = label,
        secondaryText = composableName,
        onClick = onClick
    )
}