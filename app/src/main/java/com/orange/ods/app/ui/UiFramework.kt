/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.databinding.ViewDataBinding
import com.orange.ods.app.ui.components.utilities.ViewDataBinding

val LocalUiFramework = staticCompositionLocalOf<MutableState<UiFramework>> { error("CompositionLocal LocalUiFramework not present") }

enum class UiFramework {
    Compose, Xml
}

@Composable
inline fun <reified T : ViewDataBinding> UiFramework(compose: @Composable () -> Unit, noinline xml: T.() -> Unit) {
    val uiFramework = LocalUiFramework.current
    // Reset current UI framework to Compose when displaying the content
    // shouldResetUiFramework is used to avoid calling LaunchedEffect on configuration changes (for instance on device rotation)
    var shouldResetUiFramework by rememberSaveable { mutableStateOf(true) }
    if (shouldResetUiFramework) {
        LaunchedEffect(Unit) {
            shouldResetUiFramework = false
            uiFramework.value = UiFramework.Compose
        }
    }
    when (uiFramework.value) {
        UiFramework.Compose -> compose()
        UiFramework.Xml -> ViewDataBinding(bind = xml)
    }
}
