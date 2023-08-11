/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.compose.component.button

import androidx.compose.runtime.Composable
import com.orange.ods.compose.theme.OdsDisplaySurface
import com.orange.ods.compose.theme.OdsTheme

@Composable
internal fun buttonToggleBackgroundColor(displaySurface: OdsDisplaySurface) = displaySurface.themeColors.primary

@Composable
internal fun buttonToggleBorderColor(displaySurface: OdsDisplaySurface) = displaySurface.themeColors.onSurface.copy(alpha = 0.12f)