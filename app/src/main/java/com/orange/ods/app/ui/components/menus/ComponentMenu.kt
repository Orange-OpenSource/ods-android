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

package com.orange.ods.app.ui.components.menus

import androidx.compose.runtime.Composable
import com.orange.ods.app.ui.components.Variant

@Composable
fun ComponentMenu(variant: Variant) {
    when (variant) {
        Variant.DropdownMenu -> MenuDropdown()
        Variant.ExposedDropdownMenu -> MenuExposedDropdown()
        else -> {}
    }
}