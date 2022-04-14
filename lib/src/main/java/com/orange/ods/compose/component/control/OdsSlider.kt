/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.compose.component.control

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.orange.ods.compose.theme.SliderActiveTickColor

/**
 * <a href="https://system.design.orange.com/0c1af118d/p/14638a-selection-controls/b/352c00" class="external" target="_blank">ODS slider</a>.
 *
 * Sliders allow users to make selections from a range of values.
 *
 * Sliders reflect a range of values along a bar, from which users may select a single value.
 * They are ideal for adjusting settings such as volume, brightness, or applying image filters.
 *
 * Use continuous sliders to allow users to make meaningful selections that don’t
 * require a specific value.
 *
 * You can allow the user to choose only between predefined set of values by specifying the amount
 * of steps between min and max values.
 *
 * @param value current value of the Slider. If outside of [valueRange] provided, value will be
 * coerced to this range.
 * @param onValueChange lambda in which value should be updated
 * @param modifier modifiers for the OdsSlider layout
 * @param enabled whether or not component is enabled and can be interacted with or not
 * @param valueRange range of values that Slider value can take. Passed [value] will be coerced to
 * this range
 * @param steps if greater than 0, specifies the amounts of discrete values, evenly distributed
 * between across the whole value range. If 0, slider will behave as a continuous slider and allow
 * to choose any value from the range specified. Must not be negative.
 * @param onValueChangeFinished lambda to be invoked when value change has ended. This callback
 * shouldn't be used to update the slider value (use [onValueChange] for that), but rather to
 * know when the user has completed selecting a new value by ending a drag or a click.
 *  @param leftIconRes Drawable resource for left icon if needed
 *  @param leftIconContentDescription Left icon content description
 *  @param rightIconRes Drawable resource for right icon if needed
 *  @param rightIconContentDescription Right icon content description
 */
@Composable
fun OdsSlider(
    value: Float,
    onValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    steps: Int = 0,
    onValueChangeFinished: (() -> Unit)? = null,
    @DrawableRes
    leftIconRes: Int? = null,
    leftIconContentDescription: String? = null,
    @DrawableRes
    rightIconRes: Int? = null,
    rightIconContentDescription: String? = null,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        leftIconRes?.let {
            Icon(
                painter = painterResource(id = it),
                contentDescription = leftIconContentDescription
            )
        }
        //For the moment we cannot change the height of the slider track (need to check in jetpack compose future versions)
        Slider(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.weight(1F),
            enabled = enabled,
            valueRange = valueRange,
            steps = steps,
            onValueChangeFinished = onValueChangeFinished,
            colors = SliderDefaults.colors(
                activeTickColor = SliderActiveTickColor //Cannot use primary alpha color, it will not be visible, need to use plain color
            )
        )
        rightIconRes?.let {
            Icon(
                painter = painterResource(id = it),
                contentDescription = rightIconContentDescription
            )
        }
    }
}