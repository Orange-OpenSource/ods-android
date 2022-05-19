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
import com.google.accompanist.pager.PagerState
import com.orange.ods.demo.ui.components.cards.SubComponentCard
import com.orange.ods.demo.ui.components.lists.SubComponentList
import com.orange.ods.demo.ui.components.tabs.SubComponentTab
import com.orange.ods.demo.ui.components.tabs.TabItem
import com.orange.ods.demo.ui.components.tabs.TabsCustomizationState
import com.orange.ods.demo.ui.components.textfields.SubComponentTextField

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun SubComponentDetailScreen(
    subComponentId: Long,
    updateTopBarTitle: (Int) -> Unit,
    updateTopAppBarTabs: (List<TabItem>, PagerState?, TabsCustomizationState.TabIconType) -> Unit
) {
    val component = remember { components.firstOrNull { component -> component.subComponents.any { subComponent -> subComponent.id == subComponentId } } }
    val subComponent = remember { components.flatMap { it.subComponents }.firstOrNull { it.id == subComponentId } }

    subComponent?.let {
        updateTopBarTitle(subComponent.titleRes)
        when (component) {
            Component.Cards -> SubComponentCard(subComponent = subComponent)
            Component.Lists -> SubComponentList(subComponent = subComponent)
            Component.TextFields -> SubComponentTextField(subComponent = subComponent)
            Component.Tabs -> SubComponentTab(subComponent, updateTopAppBarTabs)
            else -> {}
        }
    }
}
