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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.orange.ods.compose.component.appbar.top.OdsTopAppBar
import com.orange.ods.compose.component.menu.OdsDropdownMenu
import com.orange.ods.module.about.R

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
     * Note that you can use the provided [com.orange.ods.module.about.utilities.VersionHelper] to display a version using your package information.
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
    val topAppBarActions: List<OdsTopAppBar.ActionButton> = emptyList(),

    /**
     * The optional actions displayed in the overflow menu of the About module TopAppBar. If the list is empty, the overflow menu icon will not be displayed.
     */
    val topAppBarOverflowMenuActions: List<OdsDropdownMenu.Item> = emptyList(),

    /**
     * The custom menu items to be displayed on the about main screen.
     * Note that mandatory items will be added to the provided list:
     *  - Privacy policy (position index 100)
     *  - Term of services (position index 101)
     *  - Accessibility (position index 102)
     */
    val customMenuItems: List<OdsAbout.MenuItem> = emptyList(),

    /**
     * The name of the privacy policy HTML file provided in raw directory.
     * Note that this menu item is always displayed and you MUST provide a file to display privacy policy of your app.
     */
    val privacyPolicyMenuItemFileName: String = "about_privacy_policy",

    /**
     * The name of the terms of service HTML file provided in raw directory.
     * Note that this menu item is always displayed and you MUST provide a file to display the terms of service for your app.
     */
    val termsOfServiceMenuItemFileName: String = "about_terms_of_service"
) {

    internal val menuItemById: Map<Int, OdsAbout.MenuItem>
        @Composable
        get() {
            val mandatoryMenuItems = mandatoryMenuItems(privacyPolicyMenuItemFileName, termsOfServiceMenuItemFileName)
            return remember {
                (customMenuItems + mandatoryMenuItems).sortedBy { it.position }
                    .mapIndexed { index, odsAboutMenuItem -> index to odsAboutMenuItem }.toMap()
            }
        }
}

/**
 * Defines the data to be shared by clicking on the about share button.
 *
 * @property title The title display on the sharing bottom sheet
 * @property text The text to share
 */
data class OdsAboutShareData(val title: String, val text: String)
