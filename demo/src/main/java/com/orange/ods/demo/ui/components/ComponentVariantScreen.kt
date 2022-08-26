/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.components

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.google.accompanist.pager.ExperimentalPagerApi
import com.orange.ods.demo.ui.TopAppBarConfiguration
import com.orange.ods.demo.ui.components.appbars.top.ComponentTopAppBar
import com.orange.ods.demo.ui.components.buttons.ComponentButtons
import com.orange.ods.demo.ui.components.cards.ComponentCard
import com.orange.ods.demo.ui.components.chips.VariantChip
import com.orange.ods.demo.ui.components.chips.VariantChipFilter
import com.orange.ods.demo.ui.components.tabs.ComponentTabs
import com.orange.ods.demo.ui.components.tabs.TabsConfiguration
import com.orange.ods.demo.ui.components.textfields.ComponentTextField

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun ComponentVariantScreen(
    variantId: Long,
    updateTopBarTitle: (Int) -> Unit,
    updateTopAppBar: (TopAppBarConfiguration) -> Unit,
    updateTopAppBarTabs: (TabsConfiguration) -> Unit
) {
    val component = remember { components.firstOrNull { component -> component.variants.any { variant -> variant.id == variantId } } }
    val variant = remember { components.flatMap { it.variants }.firstOrNull { it.id == variantId } }

    variant?.let {
        updateTopBarTitle(variant.titleRes)
        when (component) {
            Component.AppBarsTop -> ComponentTopAppBar(updateTopAppBar)
            Component.Buttons -> ComponentButtons(variant = variant)
            Component.Cards -> ComponentCard(variant = variant)
            Component.Chips -> if (variant == Variant.ChipFilter) VariantChipFilter() else VariantChip()
            Component.TextFields -> ComponentTextField(variant = variant)
            Component.Tabs -> ComponentTabs(variant, updateTopAppBarTabs)
            else -> {}
        }
    }
}
