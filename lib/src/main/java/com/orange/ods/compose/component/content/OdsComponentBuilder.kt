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
 * The content builder of a component.
 *
 * Subclasses of [OdsComponentBuilder] should be used instead of composable methods when passing parameters to components.
 * This prevents using generic composable methods that can encapsulate any kind of views and thus helps developers to follow UI guidelines more easily.
 * This also allows to group parameters that are related to the same content inside a component.
 * For instance it is possible to create an `Icon` subclass to replace both `icon: @Composable () -> Unit` and `onIconClick: () -> Unit` parameters with a single `icon: IconBuilder` parameter.
 *
 * @param T Type of extra parameters.
 */
abstract class OdsComponentBuilder<T> where T : OdsComponentBuilder.ExtraParameters {

    /**
     * Extra parameters that can be passed to the `Content` method when other parameters than those provided by the user are needed to layout the component.
     */
    abstract class ExtraParameters

    /**
     * The extra parameters.
     */
    protected lateinit var extraParameters: T

    /**
     * The Jetpack Compose UI content for this component builder.
     *
     * Calls `Content(Modifier)` with the default `Modifier`.
     */
    @Composable
    internal fun Content() = Content(modifier = Modifier)

    /**
     * The Jetpack Compose UI content for this component builder.
     *
     * Calls `Content(Modifier, T)` with the default `Modifier`.
     *
     * @param extraParameters Extra parameters used to layout the component.
     */
    @Composable
    internal fun Content(extraParameters: T) = Content(modifier = Modifier, extraParameters = extraParameters)

    /**
     * The Jetpack Compose UI content for this component builder.
     *
     * @param modifier [Modifier] applied to the content.
     * @param extraParameters Extra parameters used to layout the component.
     */
    @Composable
    internal fun Content(modifier: Modifier, extraParameters: T) {
        this.extraParameters = extraParameters
        Content(modifier = modifier)
    }

    /**
     * The Jetpack Compose UI content for this component builder.
     * Subclasses must implement this method to provide content.
     *
     * @param modifier [Modifier] applied to the content.
     */
    // TODO: Set this method internal once OdsSearchTopAppBar is developed
    @Composable
    abstract fun Content(modifier: Modifier)
}
