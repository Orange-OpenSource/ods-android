/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.utilities.extension

/**
 * Calls the specified function [block] and returns its result if `this` is `null`.
 *
 * @param R The type of the receiver.
 * @param block The function to execute if `this` is `null`.
 * @return `this` if it is not `null`, or the [block] result if `this` is `null`.
 */
inline fun <R> R?.orElse(block: () -> R) = this ?: run(block)