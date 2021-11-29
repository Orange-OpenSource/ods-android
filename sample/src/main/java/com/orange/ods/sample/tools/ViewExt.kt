/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.sample.tools

import android.view.View
import android.widget.Checkable

internal fun View.enabledState() = post {
    isEnabled = true
    invalidate()
}

internal fun View.disabledState() = post {
    isEnabled = false
    invalidate()
}

internal fun View.pressedState() = post {
    isPressed = true
    invalidate()
}

internal fun View.focusedState() = post {
    requestFocus()
}

internal fun View.hoveredState() = post {
    isHovered = true
    invalidate()
}

internal fun View.draggedState() = post {
    invalidate()
}

internal fun View.checkedState() = post {

    (this as? Checkable)?.isChecked = true
    invalidate()
}