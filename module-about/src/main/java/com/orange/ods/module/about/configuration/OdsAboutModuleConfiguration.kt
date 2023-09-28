/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.module.about.configuration

import androidx.annotation.DrawableRes
import androidx.compose.runtime.staticCompositionLocalOf
import com.orange.ods.compose.component.appbar.top.OdsTopAppBarActionButton
import com.orange.ods.compose.component.appbar.top.OdsTopAppBarOverflowMenuActionItem
import com.orange.ods.module.about.R


val LocalOdsAboutModuleConfiguration =
    staticCompositionLocalOf<OdsAboutModuleConfiguration> { error("CompositionLocal LocalOdsAboutModuleConfiguration not present") }

/**
 * About module configuration.
 */
data class OdsAboutModuleConfiguration(
    /**
     * The name of the application displayed on the main screen of the About module.
     */
    val appName: String,

    /**
     * The illustration resource id. It should be a SVG or PNG resource file, placed in res/drawable directory. It allows to customize
     * the displayed image on the main screen of the About module.
     * If not provided, the default Orange illustration will be displayed.
     */
    @DrawableRes
    val appIllustration: Int = R.drawable.il_about,

    /**
     * The application version displayed on the main screen of the About module.
     * If null, no version will be displayed.
     */
    val appVersion: String? = null,

    /**
     * The application description displayed on the main screen of the About module.
     * If null, no description will be displayed.
     */
    val appDescription: String? = null,

    /**
     * The data to be used to share the app on share button click. If null, no share button will be displayed.
     */
    val shareData: OdsAboutShareData? = null,

    /**
     * The action to be launched on feedback button click. If null, no feedback button will be displayed.
     */
    val onFeedbackButtonClick: (() -> Unit)? = null,

    /**
     * The optional actions displayed at the end of the About module TopAppBar.
     */
    val topAppBarActions: List<OdsTopAppBarActionButton> = emptyList(),

    /**
     * The optional actions displayed in the overflow menu of the About module TopAppBar. If the list is empty, the overflow menu icon will not be displayed.
     */
    val topAppBarOverflowMenuActions: List<OdsTopAppBarOverflowMenuActionItem> = emptyList(),

    )

/**
 * Defines the data to be shared by clicking on the about share button.
 *
 * @property title The title display on the sharing bottom sheet
 * @property text The text to share
 */
data class OdsAboutShareData(val title: String, val text: String)