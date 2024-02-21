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

package com.orange.ods.compose.utilities.extension

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import com.orange.ods.extension.orElse

/**
 * Allow to activate click on an element without any ripple effect
 */
fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier = composed {
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
