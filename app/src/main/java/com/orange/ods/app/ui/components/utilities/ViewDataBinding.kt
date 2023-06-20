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
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.viewinterop.AndroidView
import androidx.databinding.ViewDataBinding
import com.orange.ods.app.R
import com.orange.ods.app.ui.utilities.composable.CodeImplementationColumn
import com.orange.ods.app.ui.utilities.composable.TechnicalText

@Composable
inline fun <reified T : ViewDataBinding> ViewDataBinding(noinline bind: T.() -> Unit) {
    val inflateMethod = T::class.java.getMethod("inflate", LayoutInflater::class.java, ViewGroup::class.java, Boolean::class.java)
    val layoutInflater = LayoutInflater.from(LocalContext.current)
    val parameters = listOf(layoutInflater, null, false)
    val binding = remember { inflateMethod.invoke(null, *parameters.toTypedArray()) as T }
    Column {
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

        CodeImplementationColumn(
            modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.screen_horizontal_margin)),
            xmlAvailable = true,
        ) {
            TechnicalText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.spacing_xs)),
                text = stringResource(id = R.string.soon_available)
            )
        }
    }
}
