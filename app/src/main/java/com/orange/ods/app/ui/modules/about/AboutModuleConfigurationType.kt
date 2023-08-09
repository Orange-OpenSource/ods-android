/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.ui.modules.about

import android.os.Bundle
import androidx.navigation.NavType
import com.google.gson.Gson
import com.orange.ods.app.ui.utilities.compat.BundleCompat
import com.orange.ods.module.about.AboutModuleConfiguration

object AboutModuleConfigurationType : NavType<AboutModuleConfiguration>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): AboutModuleConfiguration? {
        return BundleCompat.getParcelable(bundle, key)
    }

    override fun parseValue(value: String): AboutModuleConfiguration {
        return Gson().fromJson(value, AboutModuleConfiguration::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: AboutModuleConfiguration) {
        bundle.putParcelable(key, value)
    }
}