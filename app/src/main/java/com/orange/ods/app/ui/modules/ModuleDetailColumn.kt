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

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.orange.ods.app.R
import com.orange.ods.app.ui.utilities.DrawableManager
import com.orange.ods.app.ui.utilities.composable.DetailScreenHeader
import com.orange.ods.app.ui.utilities.composable.Subtitle

@Composable
fun ModuleDetailColumn(
    module: Module,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(bottom = dimensionResource(id = com.orange.ods.R.dimen.screen_vertical_margin))
    ) {
        DetailScreenHeader(
            imageRes = DrawableManager.getDrawableResIdForCurrentTheme(resId = module.imageRes),
            imageAlignment = module.imageAlignment,
            descriptionRes = module.descriptionRes
        )

        Column(modifier = Modifier.padding(dimensionResource(id = com.orange.ods.R.dimen.screen_horizontal_margin))) {
            Subtitle(textRes = R.string.module_customize)
            content()
        }
    }
}