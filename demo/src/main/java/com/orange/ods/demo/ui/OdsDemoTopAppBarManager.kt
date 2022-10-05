/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui

import androidx.compose.runtime.staticCompositionLocalOf

interface OdsDemoTopAppBarManager {

    fun updateTopAppBar(topAppBarConfiguration: TopAppBarConfiguration)

    fun updateTopAppBarTitle(titleRes: Int)
}

val LocalTopAppBarManager = staticCompositionLocalOf<OdsDemoTopAppBarManager> { error("CompositionLocal LocalTopAppBarManager not present") }
