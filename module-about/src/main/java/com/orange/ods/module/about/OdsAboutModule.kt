/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.module.about

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.orange.ods.module.about.configuration.OdsAboutModuleConfiguration

class OdsAboutModule internal constructor(context: Context, private val viewModel: OdsAboutViewModel) {

    var configuration = getDefaultConfiguration(context)
        set(value) {
            field = value
            viewModel.configureAboutModule(value)
        }

    private fun getDefaultConfiguration(context: Context): OdsAboutModuleConfiguration {
        val appName = with(context.applicationInfo) {
            if (labelRes == 0) nonLocalizedLabel.toString() else context.getString(labelRes)
        }

        return OdsAboutModuleConfiguration(appName)
    }

    init {
        viewModel.configureAboutModule(configuration)
    }
}

@Composable
fun odsAboutModule(context: Context): OdsAboutModule {
    val aboutViewModel = viewModel<OdsAboutViewModel>(context as ViewModelStoreOwner)
    return remember { OdsAboutModule(context, aboutViewModel) }
}
