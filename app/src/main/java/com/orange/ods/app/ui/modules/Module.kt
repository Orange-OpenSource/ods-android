/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.ui.modules

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.orange.ods.app.R
import com.orange.ods.app.ui.modules.about.AboutCustomization
import com.orange.ods.app.ui.modules.about.AboutDemoScreen
import com.orange.ods.module.about.AboutModuleConfiguration

val modules = Module::class.sealedSubclasses.mapNotNull { it.objectInstance }

sealed class Module(
    @StringRes val titleRes: Int,
    @DrawableRes val imageRes: Int,
    @StringRes val descriptionRes: Int,
    val customizationContent: @Composable (navigateToModuleDemo: (String) -> Unit) -> Unit,
    val demoScreen: @Composable (configuration: Any?) -> Unit,
    val imageAlignment: Alignment = Alignment.Center,
) {
    companion object {
        const val ImageBackgroundColor = 0xff1b1b1b
    }

    val id: Long = Module::class.sealedSubclasses.indexOf(this::class).toLong()

    object About : Module(
        R.string.module_about,
        R.drawable.il_about,
        R.string.module_about_description,
        customizationContent = { navigateToModuleDemo -> AboutCustomization(navigateToModuleDemo = navigateToModuleDemo) },
        demoScreen = { configuration -> configuration?.let { AboutDemoScreen(it as AboutModuleConfiguration) } },
    )
}