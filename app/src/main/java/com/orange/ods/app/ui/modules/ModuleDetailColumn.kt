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
import com.orange.ods.compose.component.button.OdsButtonStyle

@Composable
fun ModuleDetailColumn(
    module: Module,
    onViewDemoButtonClick: () -> Unit,
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
            Subtitle(textRes = com.orange.ods.app.R.string.module_customize)
            content()

            OdsButton(
                modifier = Modifier
                    .padding(top = dimensionResource(id = R.dimen.spacing_m))
                    .fillMaxWidth(),
                style = OdsButtonStyle.Primary,
                text = stringResource(id = com.orange.ods.app.R.string.module_view_demo),
                onClick = onViewDemoButtonClick
            )
        }
    }
}