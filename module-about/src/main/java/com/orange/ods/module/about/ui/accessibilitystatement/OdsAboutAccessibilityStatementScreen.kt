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

package com.orange.ods.module.about.ui.accessibilitystatement

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidViewBinding
import com.orange.ods.module.about.databinding.OdsAboutAccessibilityStatementBinding
import com.orange.ods.module.about.ui.configuration.OdsAboutAccessibilityStatementMenuItem

@Composable
internal fun OdsAboutAccessibilityStatementScreen(accessibilityStatementMenuItem: OdsAboutAccessibilityStatementMenuItem) {
    AndroidViewBinding(
        modifier = Modifier.fillMaxSize(),
        factory = { layoutInflater, parent, attachToParent ->
            OdsAboutAccessibilityStatementBinding.inflate(layoutInflater, parent, attachToParent).apply {
                statementview.accessibilityStatementFilePath = accessibilityStatementMenuItem.xmlFilePath
                statementview.urlAccessibilityDeclaration = accessibilityStatementMenuItem.detailsUrl
            }
        })
}
