/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.ui.modules.emptyscreen

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.app.R
import com.orange.ods.app.ui.components.utilities.clickOnElement
import com.orange.ods.compose.module.emptyscreen.OdsEmptyScreen
import com.orange.ods.extension.orElse

@Composable
fun EmptyScreenDemoScreen(viewModel: EmptyScreenViewModel) {
    val context = LocalContext.current
    val elementClicked = stringResource(id = R.string.module_emptyScreen_button_setup)
    OdsEmptyScreen(
        title = stringResource(id = viewModel.usage.titleRes),
        text = if (viewModel.text) stringResource(id = viewModel.usage.textRes) else null,
        button = if (viewModel.button) {
            OdsEmptyScreen.Button(text = stringResource(id = viewModel.usage.buttonLabelRes)) { clickOnElement(context, elementClicked) }
        } else {
            null
        },
        image = OdsEmptyScreen.Image(painter = painterResource(id = viewModel.usage.illustrationRes.orElse { com.orange.ods.R.drawable.il_yoga_man }))
    )
}