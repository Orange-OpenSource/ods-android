/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.ui.components.utilities

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.compose.component.button.OdsButton
import com.orange.ods.compose.component.button.OdsButtonStyle
import com.orange.ods.compose.text.OdsTextBody1
import com.orange.ods.app.R

@Composable
fun ComponentLaunchContentColumn(@StringRes textRes: Int, @StringRes buttonLabelRes: Int, onButtonClick: () -> Unit) {
    Column(modifier = Modifier.padding(top = dimensionResource(id = R.dimen.screen_vertical_margin), bottom = dimensionResource(id = R.dimen.spacing_s))) {
        OdsTextBody1(
            modifier = Modifier
                .padding(horizontal = dimensionResource(id = R.dimen.screen_horizontal_margin)),
            text = stringResource(id = textRes)
        )

        OdsButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = dimensionResource(R.dimen.screen_horizontal_margin))
                .padding(top = dimensionResource(R.dimen.spacing_m)),
            text = stringResource(id = buttonLabelRes),
            style = OdsButtonStyle.Primary,
            onClick = onButtonClick
        )
    }
}