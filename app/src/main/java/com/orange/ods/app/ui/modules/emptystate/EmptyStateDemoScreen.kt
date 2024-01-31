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

package com.orange.ods.app.ui.modules.emptystate

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.app.R
import com.orange.ods.app.ui.components.utilities.clickOnElement
import com.orange.ods.compose.module.emptystate.OdsEmptyStateContent
import com.orange.ods.extension.orElse

@Composable
fun EmptyStateDemoScreen(viewModel: EmptyStateViewModel) {
    val context = LocalContext.current
    val elementClicked = stringResource(id = R.string.module_emptyState_button_setup)
    OdsEmptyStateContent(
        title = stringResource(id = viewModel.usage.titleRes),
        text = if (viewModel.text) stringResource(id = viewModel.usage.textRes) else null,
        button = if (viewModel.button) {
            OdsEmptyStateContent.Button(text = stringResource(id = viewModel.usage.buttonLabelRes)) { clickOnElement(context, elementClicked) }
        } else {
            null
        },
        image = OdsEmptyStateContent.Image(painter = painterResource(id = viewModel.usage.illustrationRes.orElse { com.orange.ods.R.drawable.il_yoga_man }))
    )
}