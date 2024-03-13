/*
 * Software Name: Orange Design System
 * SPDX-FileCopyrightText: Copyright (c) Orange SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT license,
 * the text of which is available at https://opensource.org/license/MIT/
 * or see the "LICENSE" file for more details.
 *
 * Software description: Android library of reusable graphical components 
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
        }
    )
}
