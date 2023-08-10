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

import android.net.Uri
import android.os.Parcelable
import androidx.annotation.DrawableRes
import com.google.gson.Gson
import com.orange.ods.compose.component.appbar.top.OdsTopAppBarActionButton
import com.orange.ods.compose.component.appbar.top.OdsTopAppBarOverflowMenuActionItem
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

/**
 * About module configuration.
 */
@Parcelize
data class AboutModuleConfiguration(
    /**
     * The name of the application displayed on the main screen of the About module.
     */
    val appName: String,

    /**
     * The optional illustration resource id. It should be a SVG or PNG resource file, placed in res/drawable directory. It allows to customize
     * the displayed image on the main screen of the About module.
     * If not provided, the default Orange illustration will be displayed.
     */
    @DrawableRes
    val appIllustration: Int = R.drawable.il_about,

    /**
     * The optional application version displayed on the main screen of the About module.
     */
    val appVersion: String? = null,

    /**
     * The optional application description displayed on the main screen of the About module.
     */
    val appDescription: String? = null,

    //TODO Use another type when specified correctly. This boolean is only used for element visibility.
    val share: Boolean = false,

    //TODO Use another type when specified correctly. This boolean is only used for element visibility.
    val feedback: Boolean = false,

    /**
     * The optional actions displayed at the end of the About module TopAppBar.
     */
    val topAppBarActions: @RawValue List<OdsTopAppBarActionButton> = emptyList(),

    /**
     * The optional actions displayed in the overflow menu of the About module TopAppBar. If the list is empty, the overflow menu icon will not be displayed.
     */
    val topAppBarOverflowMenuActions: @RawValue List<OdsTopAppBarOverflowMenuActionItem> = emptyList(),

    
    ) : Parcelable {

    override fun toString(): String {
        return Uri.encode(Gson().toJson(this))
    }
}