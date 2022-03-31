/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import kotlin.properties.Delegates

class OdsApplication : Application() {

    companion object {
        var instance: OdsApplication by Delegates.notNull()
            private set
    }

    var isDarkModeEnabled = mutableStateOf(false)

    override fun onCreate() {
        super.onCreate()

        instance = this
    }
}