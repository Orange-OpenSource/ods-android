/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
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