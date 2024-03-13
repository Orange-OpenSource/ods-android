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

package com.orange.ods.compose.component.content

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier

/**
 * The content in the scope of a component.
 *
 * This class is an equivalent to [OdsComponentContent] where the `Content` method is called against a specific scope (for instance a [androidx.compose.foundation.layout.RowScope]).
 *
 * @param extraParametersClass The extra parameters class.
 * @param T The type of the scope.
 */
abstract class OdsComponentScopeContent<T, S> internal constructor(private val extraParametersClass: Class<S>) where S : OdsComponentContent.ExtraParameters {

    /**
     * The extra parameters.
     */
    protected val extraParameters: S
        @Composable
        get() = getLocalExtraParameters(extraParametersClass).current

    /**
     * The Jetpack Compose UI for this component content.
     *
     * Calls `Content(Modifier)` with the default `Modifier`.
     */
    @Composable
    internal fun T.Content() = Content(modifier = Modifier)

    /**
     * The Jetpack Compose UI for this component content.
     *
     * Calls `Content(Modifier, T)` with the default `Modifier`.
     *
     * @param extraParameters the extra parameters for this content.
     */
    @Composable
    internal fun T.Content(extraParameters: S) = Content(modifier = Modifier, extraParameters = extraParameters)

    /**
     * The Jetpack Compose UI for this component content.
     *
     * @param modifier the Modifier for this content.
     * @param extraParameters the extra parameters for this content.
     */
    @Composable
    internal fun T.Content(modifier: Modifier, extraParameters: S) {
        CompositionLocalProvider(getLocalExtraParameters(extraParametersClass) provides extraParameters) {
            Content(modifier = modifier)
        }
    }

    /**
     * The Jetpack Compose UI for this component content.
     * Subclasses must implement this method to provide content.
     *
     * @param modifier the Modifier for this content.
     */
    @Composable
    internal abstract fun T.Content(modifier: Modifier)
}
