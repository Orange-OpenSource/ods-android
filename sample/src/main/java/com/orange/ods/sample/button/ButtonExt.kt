package com.orange.ods.sample.button

import android.widget.Button

internal fun Button.enabledState() = post {
    isEnabled = true
    invalidate()
}

internal fun Button.disabledState() = post {
    isEnabled = false
    invalidate()
}

internal fun Button.pressedState() = post {
    isPressed = true
    invalidate()
}

internal fun Button.focusedState() = post {
    requestFocus()
}

internal fun Button.hoveredState() = post {
    isHovered = true
    invalidate()
}

internal fun Button.draggedState() = post {
    invalidate()
}