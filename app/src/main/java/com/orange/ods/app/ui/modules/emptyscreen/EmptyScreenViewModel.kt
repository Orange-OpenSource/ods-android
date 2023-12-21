/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.ui.modules.emptyscreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class EmptyScreenViewModel: ViewModel() {
    var text by mutableStateOf(false)
    var button by mutableStateOf(false)
    var usage by mutableStateOf(EmptyScreenUsage.FirstUse)
}