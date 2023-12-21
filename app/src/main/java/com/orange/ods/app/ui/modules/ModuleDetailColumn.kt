/*
 * Software Name: Orange Design System
 * SPDX-FileCopyrightText: Copyright (c) Orange SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT licence,
 * the text of which is available at https://opensource.org/license/MIT/
 * or see the "LICENSE" file for more details.
 *
 * Software description: Android library of reusable graphical components
 */

package com.orange.ods.app.ui.modules

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.R
import com.orange.ods.app.ui.utilities.DrawableManager
import com.orange.ods.app.ui.utilities.composable.DetailScreenHeader
import com.orange.ods.app.ui.utilities.composable.Subtitle
import com.orange.ods.compose.component.button.OdsButton

@Composable
fun ModuleDetailColumn(
    module: Module,
    onViewDemoButtonClick: () -> Unit,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(bottom = dimensionResource(id = R.dimen.screen_vertical_margin))
    ) {
        DetailScreenHeader(
            imageRes = DrawableManager.getDrawableResIdForCurrentTheme(resId = module.imageRes),
            imageAlignment = module.imageAlignment,
            descriptionRes = module.descriptionRes
        )

        Column {
            Subtitle(textRes = com.orange.ods.app.R.string.module_customize, horizontalPadding = true)
            content()
            OdsButton(
                modifier = Modifier
                    .padding(all = dimensionResource(id = R.dimen.spacing_m))
                    .fillMaxWidth(),
                style = OdsButton.Style.Primary,
                text = stringResource(id = com.orange.ods.app.R.string.module_view_demo),
                onClick = onViewDemoButtonClick
            )
        }
    }
}