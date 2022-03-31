/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.compose.component.button

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.IconToggleButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.orange.ods.compose.theme.Black900
import com.orange.ods.compose.theme.Orange200
import com.orange.ods.compose.theme.White100
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

class DisabledInteractionSource : MutableInteractionSource {
    override val interactions: Flow<Interaction> = emptyFlow()
    override suspend fun emit(interaction: Interaction) {}
    override fun tryEmit(interaction: Interaction) = true
}

/**
 * <a href="https://system.design.orange.com/0c1af118d/p/06a393-buttons/b/79b091" target="_blank">ODS Buttons</a>.
 *
 * An [IconButton] with two states, for icons that can be toggled 'on' and 'off', such as a
 * bookmark icon, or a navigation icon that opens a drawer.
 *
 * @param checked whether this IconToggleButton is currently checked
 * @param onCheckedChange callback to be invoked when this icon is selected
 * @param iconRes Resource identifier of the icon displayed
 * @param contentDescription Content description associated to the icon
 * @param modifier optional [Modifier] for this IconToggleButton
 * @param enabled enabled whether or not this [IconToggleButton] will handle input events and appear
 * enabled for semantics purposes
 * @param isOnDarkSurface optional allow to force the button display on a dark or light
 * surface. By default the system night mode value is used to know if the button is displayed
 * on dark or light.
 */
@Composable
fun OdsToggleButton(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    @DrawableRes
    iconRes: Int,
    contentDescription: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isOnDarkSurface: Boolean = isSystemInDarkTheme()
) {
    val iconColor = when {
        !checked && isOnDarkSurface -> White100
        !checked && !isOnDarkSurface -> Black900
        else -> Orange200
    }
    IconToggleButton(
        checked = checked,
        onCheckedChange = onCheckedChange,
        modifier = modifier,
        enabled = enabled,
        interactionSource = remember { DisabledInteractionSource() }
    ) {
        val iconTint by animateColorAsState(iconColor)
        val backgroundAlpha by animateFloatAsState(if (checked) 0.12f else 0f)
        Box(
            modifier = Modifier
                .background(color = Orange200.copy(alpha = backgroundAlpha))
                .padding(12.dp)
        ) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = contentDescription,
                tint = iconTint
            )
        }
    }
}