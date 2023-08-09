/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.ui.utilities.compat

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import java.io.Serializable

object BundleCompat {

    inline fun <reified T : Parcelable> getParcelable(bundle: Bundle?, key: String): T? = getParcelable(bundle, key, T::class.java)

    fun <T : Parcelable> getParcelable(bundle: Bundle?, key: String, clazz: Class<T>): T? = when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> bundle?.getParcelable(key, clazz)
        else -> @Suppress("DEPRECATION") bundle?.getParcelable(key)
    }

    inline fun <reified T : Parcelable> getParcelableArrayList(bundle: Bundle?, key: String): ArrayList<T>? = when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> bundle?.getParcelableArrayList(key, T::class.java)
        else -> @Suppress("DEPRECATION") bundle?.getParcelableArrayList(key)
    }

    inline fun <reified T : Serializable> getSerializable(bundle: Bundle?, key: String): T? = when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> bundle?.getSerializable(key, T::class.java)
        else -> @Suppress("DEPRECATION") bundle?.getSerializable(key) as? T
    }
}