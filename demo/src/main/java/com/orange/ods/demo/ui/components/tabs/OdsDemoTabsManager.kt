/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.components.tabs

import androidx.compose.runtime.staticCompositionLocalOf

interface OdsDemoTabsManager {

    fun updateTopAppBarTabs(tabsConfiguration: TabsConfiguration)

    fun clearTopAppBarTabs()
}

val LocalTabsManager = staticCompositionLocalOf<OdsDemoTabsManager> { error("CompositionLocal LocalTabsManager not present") }
