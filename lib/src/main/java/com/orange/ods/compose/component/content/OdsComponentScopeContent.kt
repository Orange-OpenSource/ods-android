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
 * The content in the scope of a component.
 *
 * This class is an equivalent to [OdsComponentContent] where the `Content` method is called against a specific scope (for instance a [androidx.compose.foundation.layout.RowScope]).
 *
 * @param T The type of the scope.
 */
abstract class OdsComponentScopeContent<T> {

    /**
     * The Jetpack Compose UI for this component content.
     * Subclasses must implement this method to provide content.
     */
    @Composable
    internal abstract fun T.Content()
}
