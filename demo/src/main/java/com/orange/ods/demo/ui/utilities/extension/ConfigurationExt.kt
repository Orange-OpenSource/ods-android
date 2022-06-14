/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.utilities.extension

import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_MASK
import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES

var Configuration.isDarkModeEnabled: Boolean
    get() {
        return uiMode and UI_MODE_NIGHT_MASK == UI_MODE_NIGHT_YES
    }
    set(value) {
        val uiModeNight = if (value) UI_MODE_NIGHT_YES else UI_MODE_NIGHT_NO
        uiMode = (uiMode and UI_MODE_NIGHT_MASK.inv()) or uiModeNight
    }