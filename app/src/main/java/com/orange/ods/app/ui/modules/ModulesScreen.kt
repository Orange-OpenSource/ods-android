/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.ui.modules

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
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
import com.orange.ods.app.R
import com.orange.ods.app.ui.LocalMainTopAppBarManager
import com.orange.ods.app.ui.utilities.DrawableManager
import com.orange.ods.compose.component.card.OdsCardImage
import com.orange.ods.compose.component.card.OdsVerticalImageFirstCard

@Composable
fun ModulesScreen(onModuleClick: (String) -> Unit) {
    val context = LocalContext.current
    LocalMainTopAppBarManager.current.updateTopAppBarTitle(R.string.navigation_item_modules)
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(dimensionResource(id = com.orange.ods.R.dimen.spacing_m)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = com.orange.ods.R.dimen.spacing_m))
    ) {
        modules.sortedBy { context.getString(it.titleRes) }.forEach { module ->
            ModuleCard(module = module, onModuleClick = onModuleClick)
        }
    }
}

@Composable
private fun ColumnScope.ModuleCard(module: Module, onModuleClick: (String) -> Unit) {
    val imageResId = DrawableManager.getDrawableResIdForCurrentTheme(resId = module.imageRes)
    OdsVerticalImageFirstCard(
        title = stringResource(id = module.titleRes),
        image = OdsCardImage(
            painterResource(id = imageResId),
            "",
            module.imageAlignment,
            ContentScale.Fit,
            Color(Module.ImageBackgroundColor)
        ),
        onClick = { onModuleClick(module.route) }
    )
}