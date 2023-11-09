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
 * The content builder in the scope of a component.
 *
 * This class is an equivalent to [OdsComponentBuilder] where the `Content` method is called against a specific scope (for instance a [androidx.compose.foundation.layout.RowScope]).
 *
 * @param T The type of the scope.
 */
abstract class OdsComponentScopeBuilder<T, S> where S : OdsComponentBuilder.ExtraParameters {

    /**
     * The extra parameters.
     */
    protected lateinit var extraParameters: S

    /**
     * The Jetpack Compose UI content for this component builder.
     *
     * Calls `Content(Modifier)` with the default `Modifier`.
     */
    @Composable
    internal fun T.Content() = Content(modifier = Modifier)

    /**
     * The Jetpack Compose UI content for this component builder.
     *
     * Calls `Content(Modifier, T)` with the default `Modifier`.
     *
     * @param extraParameters Extra parameters used to layout the component.
     */
    @Composable
    internal fun T.Content(extraParameters: S) = Content(modifier = Modifier, extraParameters = extraParameters)

    /**
     * The Jetpack Compose UI content for this component builder.
     *
     * @param modifier [Modifier] applied to the content.
     * @param extraParameters Extra parameters used to layout the component.
     */
    @Composable
    internal fun T.Content(modifier: Modifier, extraParameters: S) {
        this@OdsComponentScopeBuilder.extraParameters = extraParameters
        Content(modifier = modifier)
    }

    /**
     * The Jetpack Compose UI content for this component builder.
     * Subclasses must implement this method to provide content.
     *
     * @param modifier [Modifier] applied to the content.
     */
    @Composable
    internal abstract fun T.Content(modifier: Modifier)
}
