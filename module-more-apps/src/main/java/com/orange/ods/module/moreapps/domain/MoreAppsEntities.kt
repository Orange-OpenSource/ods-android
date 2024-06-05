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

package com.orange.ods.module.moreapps.domain

import android.util.DisplayMetrics

internal interface MoreAppsItem

internal data class AppsSection(
    val name: String?,
    val items: List<MoreAppsItem?>
) : MoreAppsItem

internal data class AppsList(
    val items: List<MoreAppsItem?>
) : MoreAppsItem

internal class App(
    val type: String,
    val name: String?,
    val description: String?,
    val url: String?,
    val iconUrlByDensity: Map<Density, String>?,
) : MoreAppsItem

internal enum class Density {
    Mdpi, Hdpi, Xhdpi, Xxhdpi, Xxxhdpi;

    companion object {
        fun fromDisplayMetrics(displayMetrics: DisplayMetrics) = when {
            displayMetrics.densityDpi >= DisplayMetrics.DENSITY_XXXHIGH -> Xxxhdpi
            displayMetrics.densityDpi >= DisplayMetrics.DENSITY_XHIGH -> Xxhdpi
            displayMetrics.densityDpi >= DisplayMetrics.DENSITY_HIGH -> Xhdpi
            displayMetrics.densityDpi >= DisplayMetrics.DENSITY_HIGH -> Hdpi
            else -> Mdpi
        }
    }
}