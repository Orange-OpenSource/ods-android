package com.orange.ods.sample.tools

import android.view.View

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