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

val modules = Module::class.sealedSubclasses.mapNotNull { it.objectInstance }

sealed class Module(
    @StringRes val titleRes: Int,
    @DrawableRes val imageRes: Int,
    @StringRes val descriptionRes: Int,
    val route: String,
    val demoScreen: @Composable (upPress: () -> Unit) -> Unit,
    val imageAlignment: Alignment = Alignment.Center,
) {
    val id: Long = Module::class.sealedSubclasses.indexOf(this::class).toLong()

    data object About : Module(
        R.string.module_about,
        R.drawable.il_about,
        R.string.module_about_description,
        route = ModuleDemoDestinations.AboutCustomizationRoute,
        demoScreen = { _ -> },
    )
}