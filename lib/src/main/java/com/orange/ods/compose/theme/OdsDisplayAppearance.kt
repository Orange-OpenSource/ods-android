/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.compose.theme

/**
 * Allow to force elements appearance to be displayed on light or dark background.
 *
 * Possible values of [OdsDisplayAppearance].
 */
enum class OdsDisplayAppearance {
    /**
     * The state used to display an element according Android system theme of the device.
     */
    DEFAULT,

    /**
     * The state to force an element to be displayed on a dark background even if the device system is set in light theme.
     */
    ON_DARK,

    /**
     * The state to force an element to be displayed on a light background even if the device system is set in dark theme.
     */
    ON_LIGHT
}