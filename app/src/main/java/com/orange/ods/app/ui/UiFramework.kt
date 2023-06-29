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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.databinding.ViewDataBinding
import com.orange.ods.app.R
import com.orange.ods.app.ui.components.utilities.ViewDataBinding

val LocalUiFramework = staticCompositionLocalOf<MutableState<UiFramework>> { error("CompositionLocal LocalUiFramework not present") }

enum class UiFramework(val iconResId: Int, val labelResId: Int) {
    Compose(R.drawable.ic_compose, R.string.code_implementation_compose),
    Xml(R.drawable.ic_xml, R.string.code_implementation_xml)
}

@Composable
inline fun <reified T : ViewDataBinding> UiFramework(compose: @Composable () -> Unit, noinline xml: T.() -> Unit) {
    val uiFramework by LocalUiFramework.current
    when (uiFramework) {
        UiFramework.Compose -> compose()
        UiFramework.Xml -> ViewDataBinding(bind = xml)
    }
}
