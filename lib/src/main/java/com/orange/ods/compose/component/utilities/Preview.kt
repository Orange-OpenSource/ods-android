/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.compose.component.utilities

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import com.orange.ods.compose.theme.OdsMaterialTheme

@Composable
internal fun Preview(content: @Composable () -> Unit) = OdsMaterialTheme {
    Surface(content = content)
}
