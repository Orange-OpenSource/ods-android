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

package com.orange.ods.theme.spacing

import androidx.annotation.DimenRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp

/**
 * Represents an Orange Design System spacing.
 */
sealed class OdsSpacing {

    /** The spacing value in [Dp]. */
    @get:Composable
    abstract val dp: Dp

    /**
     * An [OdsSpacing] resource value.
     */
    data class Resource(@DimenRes val id: Int) : OdsSpacing() {

        override val dp: Dp
            @Composable
            get() = dimensionResource(id = id)
    }

    /**
     * An [OdsSpacing] raw value.
     */
    data class Value(@get:Composable override val dp: Dp) : OdsSpacing()
}
