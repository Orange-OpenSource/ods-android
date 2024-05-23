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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.app.ui.utilities.DrawableManager
import com.orange.ods.compose.component.card.OdsCard
import com.orange.ods.compose.component.card.OdsVerticalImageFirstCard
import com.orange.ods.compose.theme.OdsTheme

@Composable
fun ModulesScreen(onModuleClick: (String) -> Unit) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(OdsTheme.spacings.medium),
        verticalArrangement = Arrangement.spacedBy(OdsTheme.spacings.medium)
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
        image = OdsCard.Image(
            painterResource(id = imageResId),
            "",
            module.imageAlignment,
            ContentScale.Crop,
            Color(DrawableManager.ImageBackgroundColor)
        ),
        onClick = { onModuleClick(module.route) }
    )
}