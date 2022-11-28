/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.utilities.compat

import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build

object PackageManagerCompat {

    @Suppress("DEPRECATION")
    fun getPackageInfo(packageManager: PackageManager, packageName: String, value: Int): PackageInfo {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            packageManager.getPackageInfo(packageName, PackageManager.PackageInfoFlags.of(value.toLong()))
        } else {
            packageManager.getPackageInfo(packageName, value)
        }
    }
}
