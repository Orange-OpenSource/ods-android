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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.compose.component.card.OdsSmallCard
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.LocalMainTopAppBarManager
import com.orange.ods.demo.ui.utilities.DrawableManager
import com.orange.ods.utilities.extension.orElse

@Composable
fun ComponentsScreen(onComponentClick: (Long) -> Unit) {
    val context = LocalContext.current
    LocalMainTopAppBarManager.current.updateTopAppBarTitle(R.string.navigation_item_components)
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(dimensionResource(id = R.dimen.spacing_m)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacing_m))
    ) {
        components.sortedBy { context.getString(it.titleRes) }.chunked(2).forEach { rowCards ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacing_m)),
            ) {
                ComponentCard(component = rowCards[0], onComponentClick)
                if (rowCards.size == 2) {
                    ComponentCard(component = rowCards[1], onComponentClick)
                } else {
                    Box(modifier = Modifier.weight(0.5f)) {}
                }
            }
        }
    }
}

@Composable
private fun RowScope.ComponentCard(component: Component, onComponentClick: (Long) -> Unit) {
    val smallImageResId = component.smallImageRes?.let { DrawableManager.getDrawableResIdForCurrentTheme(resId = it) }
    val imageResId = DrawableManager.getDrawableResIdForCurrentTheme(resId = component.imageRes)
    OdsSmallCard(
        modifier = Modifier.weight(0.5f),
        title = stringResource(id = component.titleRes),
        image = painterResource(id = smallImageResId.orElse { imageResId }),
        imageBackgroundColor = Color(Component.ImageBackgroundColor),
        imageContentScale = ContentScale.Fit,
        imageAlignment = component.imageAlignment,
        onCardClick = {
            onComponentClick(component.id)
        },
    )
}
