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
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.databinding.ViewDataBinding
import com.orange.ods.app.ui.components.utilities.ViewDataBinding

val LocalUiFrameworkManager = staticCompositionLocalOf<UiFrameworkManager> { error("CompositionLocal LocalUiFrameworkManager not present") }

enum class UiFramework {
    Compose, Xml
}

interface UiFrameworkManager {

    var uiFramework: UiFramework
}

class UiFrameworkState(uiFramework: MutableState<UiFramework>) : UiFrameworkManager {

    override var uiFramework: UiFramework by uiFramework
}

@Composable
fun rememberUiFrameworkState(
    uiFramework: MutableState<UiFramework> = remember { mutableStateOf(UiFramework.Compose) }
) =
    remember(uiFramework) { UiFrameworkState(uiFramework) }

@Composable
inline fun <reified T : ViewDataBinding> UiFramework(compose: @Composable () -> Unit, noinline xml: T.() -> Unit) {
    val uiFrameworkManager = LocalUiFrameworkManager.current
    // Reset current UI framework to Compose when displaying the content
    LaunchedEffect(Unit) {
        uiFrameworkManager.uiFramework = UiFramework.Compose
    }
    when (uiFrameworkManager.uiFramework) {
        UiFramework.Compose -> compose()
        UiFramework.Xml -> ViewDataBinding(bind = xml)
    }
}
