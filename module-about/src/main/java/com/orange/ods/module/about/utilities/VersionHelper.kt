/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.module.about.utilities

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import androidx.compose.runtime.Composable
import com.orange.ods.extension.ifNotNull
import com.orange.ods.extension.orElse
import com.orange.ods.module.about.R

object VersionHelper {

    /**
     * @return a displayable version: `Version <versionName> (<versionCode>)`
     */
    @Composable
    fun getFromPackageInfo(context: Context): String {
        val packageInfo = ifNotNull(context.packageManager, context.packageName) { packageManager, packageName ->
            PackageManagerCompat.getPackageInfo(packageManager, packageName, 0)
        }
        return packageInfo?.let {
            String.format(context.resources.getString(R.string.ods_about_app_version), packageInfo.versionName, packageInfo.versionCode())
        }.orElse {
            context.resources.getString(R.string.ods_about_app_version)
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