/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.ui.components.utilities

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.databinding.ViewDataBinding

@Composable
inline fun <reified T : ViewDataBinding> ViewDataBinding(noinline bind: T.() -> Unit) {
    val inflateMethod = T::class.java.getMethod("inflate", LayoutInflater::class.java, ViewGroup::class.java, Boolean::class.java)
    val layoutInflater = LayoutInflater.from(LocalContext.current)
    val parameters = listOf(layoutInflater, null, false)
    val binding = remember { inflateMethod.invoke(null, *parameters.toTypedArray()) as T }
    AndroidView(
        factory = {
            binding.bind()
            binding.executePendingBindings()
            binding.root
        },
        update = {
            binding.bind()
            binding.executePendingBindings()
        })
}
