/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.compose.component.content

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * The content in the scope of a component.
 *
 * This class is an equivalent to [OdsComponentContent] where the `Content` method is called against a specific scope (for instance a [androidx.compose.foundation.layout.RowScope]).
 *
 * @param T The type of the scope.
 */
abstract class OdsComponentScopeContent<T, S> where S : OdsComponentContent.ExtraParameters {

    /**
     * The extra parameters.
     */
    protected lateinit var extraParameters: S

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
        this@OdsComponentScopeContent.extraParameters = extraParameters
        Content(modifier = modifier)
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
