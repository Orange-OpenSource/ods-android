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

package com.orange.ods.compose.appbar.top

import app.cash.paparazzi.Paparazzi
import com.android.ide.common.rendering.api.SessionParams
import com.orange.ods.compose.component.appbar.top.PreviewOdsTopAppBar
import org.junit.Rule
import org.junit.Test

internal class OdsTopAppBarTest {

    @get:Rule
    val paparazzi = Paparazzi(renderingMode = SessionParams.RenderingMode.SHRINK, maxPercentDifference = 0.0)

    @Test
    fun takeOdsTopAppBarLightThemeSnapshot() {
        paparazzi.snapshot {
            PreviewOdsTopAppBar(darkThemeEnabled = false)
        }
    }

    @Test
    fun takeOdsTopAppBarDarkThemeSnapshot() {
        paparazzi.snapshot {
            PreviewOdsTopAppBar(darkThemeEnabled = true)
        }
    }
}
