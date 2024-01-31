/*
 * Software Name: Orange Design System
 * SPDX-FileCopyrightText: Copyright (c) Orange SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT licence,
 * the text of which is available at https://opensource.org/license/MIT/
 * or see the "LICENSE" file for more details.
 *
 * Software description: Android library of reusable graphical components
 */

package com.orange.ods.app.ui.modules

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.Alignment
import com.orange.ods.app.R

val modules = Module::class.sealedSubclasses.mapNotNull { it.objectInstance }

sealed class Module(
    @StringRes val titleRes: Int,
    @DrawableRes val imageRes: Int,
    @StringRes val descriptionRes: Int,
    val route: String,
    val imageAlignment: Alignment = Alignment.Center,
) {
    val id: Long = Module::class.sealedSubclasses.indexOf(this::class).toLong()

    data object About : Module(
        R.string.module_about,
        R.drawable.il_about,
        R.string.module_about_description,
        route = ModulesNavigation.AboutCustomizationRoute
    )

    data object EmptyState : Module(
        R.string.module_emptyState_title,
        R.drawable.il_empty_state,
        R.string.module_emptyState_description,
        route = ModulesNavigation.EmptyStateCustomizationRoute
    )
}