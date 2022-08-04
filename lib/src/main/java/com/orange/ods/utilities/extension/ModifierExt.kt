/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.utilities.extension

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed

/**
 * Allow to activate click on an element without any ripple effect
 */
inline fun Modifier.noRippleClickable(crossinline onClick: () -> Unit): Modifier = composed {
    clickable(indication = null,
        interactionSource = remember { MutableInteractionSource() }) {
        onClick()
    }
}

/**
 * Returns the first [Modifier.Element] of type [T] in the current modifier, or null if an element of type [T] could not be found.
 *
 * @param T The type of the [Modifier.Element].
 * @return The modifier element, or null if it could not be found.
 */
internal inline fun <reified T> Modifier.getElementOfType(): T? where T : Modifier.Element {
    return foldOut(null as T?) { currentElement, foundElement ->
        foundElement.orElse { currentElement as? T }
    }
}
