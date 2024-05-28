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

package com.orange.ods.compose.component.banner

import app.cash.paparazzi.Paparazzi
import com.android.ide.common.rendering.api.SessionParams
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
internal class OdsBannerTest(private val parameter: OdsBannerPreviewParameter) {

    companion object {

        @JvmStatic
        @Parameterized.Parameters
        internal fun data() = OdsBannerPreviewParameterProvider().values.toList()
    }

    @get:Rule
    val paparazzi = Paparazzi(renderingMode = SessionParams.RenderingMode.SHRINK, maxPercentDifference = 0.0)

    @Test
    fun takeOdsBannerLightThemeSnapshot() {
        paparazzi.snapshot {
            PreviewOdsBanner(
                darkThemeEnabled = false,
                parameter = parameter
            )
        }
    }

    @Test
    fun takeOdsBannerDarkThemeSnapshot() {
        paparazzi.snapshot {
            PreviewOdsBanner(
                darkThemeEnabled = true,
                parameter = parameter
            )
        }
    }
}
