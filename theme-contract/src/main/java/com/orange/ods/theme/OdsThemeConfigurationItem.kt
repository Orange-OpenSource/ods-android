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

package com.orange.ods.theme

object OdsThemeConfigurationItem {

    /**
     * An interface implemented by some items in [OdsThemeConfigurationContract] that enumerate a catalog of properties.
     */
    interface Catalog<T> {

        /**
         * The list of all entries in this catalog.
         */
        val entries: List<T>
    }

    /**
     * An interface implemented by some items in [OdsThemeConfigurationContract] that provide tokens.
     */
    interface TokenProvider<T> where T : Catalog<out OdsToken<*>> {

        /**
         * The tokens.
         */
        val tokens: T
    }
}
