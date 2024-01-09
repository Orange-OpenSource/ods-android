/*
 * Software Name: Orange Design System
 * SPDX-FileCopyrightText: Copyright (c) Orange SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT licence,
 * the text of which is available at https://opensource.org/license/MIT/
 * or see the "LICENSE" file for more details.
 *
 * Software description: Android library of reusable graphical components 
 */

package com.orange.ods.extension

/**
 * Calls the specified function [block] and returns its result if `this` is `null`.
 *
 * @param R The type of the receiver.
 * @param block The function to execute if `this` is `null`.
 * @return `this` if it is not `null`, or the [block] result if `this` is `null`.
 */
inline fun <R> R?.orElse(block: () -> R) = this ?: run(block)

/**
 * Checks if [p1] and [p2] are not `null` and calls the specified function [block] if this is the case.
 *
 * @param T1 The type of the first parameter.
 * @param T2 The type of the second parameter.
 * @param R The return type of [block].
 * @param p1 The first parameter to check.
 * @param p2 The second parameter to check.
 * @param block The function to execute if [p1] and [p2] are not `null`.
 * @return The [block] result if [p1] and [p2] are not `null`, or `null` if [p1] or [p2] is `null`.
 */
inline fun <T1 : Any, T2 : Any, R : Any> ifNotNull(p1: T1?, p2: T2?, block: (T1, T2) -> R?): R? {
    return if (p1 != null && p2 != null) block(p1, p2) else null
}