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
import com.orange.ods.app.ui.components.banners.ComponentBanners
import com.orange.ods.app.ui.components.bottomnavigation.ComponentBottomNavigation
import com.orange.ods.app.ui.components.checkboxes.ComponentCheckboxes
import com.orange.ods.app.ui.components.dialogs.ComponentDialog
import com.orange.ods.app.ui.components.floatingactionbuttons.ComponentFloatingActionButton
import com.orange.ods.app.ui.components.imageitem.ComponentImageItem
import com.orange.ods.app.ui.components.listitem.ComponentListItem
import com.orange.ods.app.ui.components.lists.ComponentLists
import com.orange.ods.app.ui.components.navigationdrawers.ComponentModalDrawers
import com.orange.ods.app.ui.components.radiobuttons.ComponentRadioButtons
import com.orange.ods.app.ui.components.sheets.ComponentSheetsBottom
import com.orange.ods.app.ui.components.sliders.ComponentSliders
import com.orange.ods.app.ui.components.snackbars.ComponentSnackbars
import com.orange.ods.app.ui.components.switches.ComponentSwitches

@Composable
fun ComponentDemoScreen(componentId: Long) {
    val component = remember { components.firstOrNull { it.id == componentId } }

    component?.let {
        LocalMainTopAppBarManager.current.updateTopAppBarTitle(component.titleRes)
        when (component) {
            Component.Banners -> ComponentBanners()
            Component.BottomNavigation -> ComponentBottomNavigation()
            Component.Checkboxes -> ComponentCheckboxes()
            Component.Dialogs -> ComponentDialog()
            Component.FloatingActionButtons -> ComponentFloatingActionButton()
            Component.ImageItem -> ComponentImageItem()
            Component.ListItem -> ComponentListItem()
            Component.Lists -> ComponentLists()
            Component.ModalDrawers -> ComponentModalDrawers()
            Component.RadioButtons -> ComponentRadioButtons()
            Component.SheetsBottom -> ComponentSheetsBottom()
            Component.Sliders -> ComponentSliders()
            Component.Snackbars -> ComponentSnackbars()
            Component.Switches -> ComponentSwitches()
            else -> {}
        }
    }
}
