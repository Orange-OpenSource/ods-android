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

/**
 * The content of a component.
 *
 * Subclasses of [OdsComponentContent] should be used instead of composable methods when passing parameters to components.
 * This prevents using generic composable methods that can encapsulate any kind of views and thus helps developers to follow UI guidelines more easily.
 * This also allows to group parameters that are related to the same content inside a component.
 * For instance it is possible to create an `Icon` subclass to replace both `icon: @Composable () -> Unit` and `onIconClick: () -> Unit` parameters with a single `icon: Icon` parameter.
 */
abstract class OdsComponentContent {

    /**
     * The Jetpack Compose UI for this component content.
     * Subclasses must implement this method to provide content.
     */
    @Composable
    internal abstract fun Content()
}
