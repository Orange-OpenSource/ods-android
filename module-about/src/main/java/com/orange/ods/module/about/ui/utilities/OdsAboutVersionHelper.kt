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

package com.orange.ods.module.about.ui.utilities

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import androidx.compose.runtime.Composable
import com.orange.ods.compose.extension.ifNotNull
import com.orange.ods.compose.extension.orElse
import com.orange.ods.module.about.R

object OdsAboutVersionHelper {

    /**
     * @return a displayable version: `Version <versionName> (<versionCode>)`
     */
    @Composable
    fun getFromPackageInfo(context: Context): String {
        val packageInfo = ifNotNull(context.packageManager, context.packageName) { packageManager, packageName ->
            PackageManagerCompat.getPackageInfo(packageManager, packageName, 0)
        }
        return packageInfo?.let {
            String.format(context.resources.getString(R.string.odsAbout_appVersion), packageInfo.versionName, packageInfo.versionCode())
        }.orElse {
            context.resources.getString(R.string.odsAbout_appVersion)
        }
    }

    private object PackageManagerCompat {
        @Suppress("DEPRECATION")
        fun getPackageInfo(packageManager: PackageManager, packageName: String, value: Int): PackageInfo {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                packageManager.getPackageInfo(packageName, PackageManager.PackageInfoFlags.of(value.toLong()))
            } else {
                packageManager.getPackageInfo(packageName, value)
            }
        }
    }

    @Suppress("DEPRECATION")
    private fun PackageInfo.versionCode(): Long =
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) {
            versionCode.toLong()
        } else {
            longVersionCode
        }
}