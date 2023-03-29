/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.orange.ods.app.ui.LocalMainTopAppBarManager
import com.orange.ods.app.ui.components.appbars.top.ComponentTopAppBar
import com.orange.ods.app.ui.components.buttons.ComponentButtons
import com.orange.ods.app.ui.components.buttons.icons.ComponentButtonsIcons
import com.orange.ods.app.ui.components.cards.ComponentCard
import com.orange.ods.app.ui.components.chips.Chip
import com.orange.ods.app.ui.components.chips.ChipFilter
import com.orange.ods.app.ui.components.menus.ComponentMenu
import com.orange.ods.app.ui.components.progress.ComponentProgress
import com.orange.ods.app.ui.components.tabs.ComponentTabs
import com.orange.ods.app.ui.components.textfields.ComponentTextField

@Composable
fun ComponentVariantScreen(variantId: Long) {
    val component = remember { components.firstOrNull { component -> component.variants.any { variant -> variant.id == variantId } } }
    val variant = remember { components.flatMap { it.variants }.firstOrNull { it.id == variantId } }

    variant?.let {
        LocalMainTopAppBarManager.current.updateTopAppBarTitle(variant.titleRes)
        when (component) {
            Component.AppBarsTop -> ComponentTopAppBar()
            Component.Buttons -> ComponentButtons(variant = variant)
            Component.ButtonsIcons -> ComponentButtonsIcons(variant = variant)
            Component.Cards -> ComponentCard(variant = variant)
            Component.Chips -> if (variant == Variant.ChipFilter) ChipFilter() else Chip(variant = variant)
            Component.Menus -> ComponentMenu(variant = variant)
            Component.Progress -> ComponentProgress(variant = variant)
            Component.TextFields -> ComponentTextField(variant = variant)
            Component.Tabs -> ComponentTabs(variant)
            else -> {}
        }
    }
}
