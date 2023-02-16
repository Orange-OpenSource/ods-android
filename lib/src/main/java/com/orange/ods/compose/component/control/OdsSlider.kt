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

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.orange.ods.R
import com.orange.ods.compose.component.OdsComponentApi
import com.orange.ods.compose.component.utilities.BasicPreviewParameterProvider
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.theme.OdsTheme

private const val ActiveTickColorAlpha = 0.4f

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
 * @param leftIcon Optional icon displayed on the left of the slider
 * @param leftIconContentDescription Left icon content description
 * @param rightIcon Optional icon displayed on the right of the slider
 * @param rightIconContentDescription Right icon content description
 */
@Composable
@OdsComponentApi
fun OdsSlider(
    value: Float,
    onValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    steps: Int = 0,
    onValueChangeFinished: (() -> Unit)? = null,
    leftIcon: Painter? = null,
    leftIconContentDescription: String? = null,
    rightIcon: Painter? = null,
    rightIconContentDescription: String? = null
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacing_m))
    ) {
        leftIcon?.let { painter ->
            Icon(
                painter = painter,
                contentDescription = leftIconContentDescription
            )
        }
        // For the moment we cannot change the height of the slider track (need to check in jetpack compose future versions)
        Slider(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .weight(1f),
            enabled = enabled,
            valueRange = valueRange,
            steps = steps,
            onValueChangeFinished = onValueChangeFinished,
            colors = SliderDefaults.colors(activeTickColor = OdsTheme.colors.surface.copy(alpha = ActiveTickColorAlpha))
        )
        rightIcon?.let { painter ->
            Icon(
                painter = painter,
                contentDescription = rightIconContentDescription
            )
        }
    }
}

/**
 * <a href="https://system.design.orange.com/0c1af118d/p/14638a-selection-controls/b/352c00" class="external" target="_blank">ODS slider</a>.
 *
 * Sliders allow users to make selections from a range of values.
 *
 * Sliders reflect a range of values along a bar, from which users may select a single value.
 * They are ideal for adjusting settings such as volume, brightness, or applying image filters.
 *
 * Use [OdsSliderLockups] to display the exact value of the slider when sliding.
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
 * @param leftIcon Optional icon displayed on the left of the slider
 * @param leftIconContentDescription Left icon content description
 * @param rightIcon Optional icon displayed on the right of the slider
 * @param rightIconContentDescription Right icon content description
 */
@Composable
@OdsComponentApi
fun OdsSliderLockups(
    value: Float,
    onValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    steps: Int = 0,
    onValueChangeFinished: (() -> Unit)? = null,
    leftIcon: Painter? = null,
    leftIconContentDescription: String? = null,
    rightIcon: Painter? = null,
    rightIconContentDescription: String? = null
) {
    val labelMinWidth = 32.dp
    val sideIconBottomPadding = 12.dp

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacing_xs))
    ) {
        leftIcon?.let { painter ->
            Icon(
                modifier = Modifier
                    .align(alignment = Alignment.Bottom)
                    .padding(bottom = sideIconBottomPadding),
                painter = painter,
                contentDescription = leftIconContentDescription,
            )
        }
        BoxWithConstraints(modifier = modifier.weight(1f)) {
            val offset = getSliderOffset(
                value = value,
                valueRange = valueRange,
                boxWidth = maxWidth,
                labelWidth = labelMinWidth + 4.dp
            )

            if (value > valueRange.start) {
                SliderLabel(
                    label = value.toInt().toString(),
                    minWidth = labelMinWidth,
                    modifier = Modifier.padding(start = offset)
                )
            } else {
                SliderLabel(label = valueRange.start.toInt().toString(), minWidth = labelMinWidth)
            }

            // For the moment we cannot change the height of the slider track (need to check in jetpack compose future versions)
            Slider(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = dimensionResource(id = R.dimen.spacing_l))
                    .padding(horizontal = labelMinWidth / 3),
                enabled = enabled,
                valueRange = valueRange,
                steps = steps,
                onValueChangeFinished = onValueChangeFinished,
            )
        }
        rightIcon?.let { painter ->
            Icon(
                modifier = Modifier
                    .align(alignment = Alignment.Bottom)
                    .padding(bottom = sideIconBottomPadding),
                painter = painter,
                contentDescription = rightIconContentDescription,
            )
        }
    }
}

@Composable
private fun SliderLabel(
    modifier: Modifier = Modifier,
    label: String,
    minWidth: Dp
) {
    Text(
        text = label,
        textAlign = TextAlign.Center,
        color = OdsTheme.colors.onPrimary,
        modifier = modifier
            .background(
                color = OdsTheme.colors.primary,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(4.dp)
            .defaultMinSize(minWidth = minWidth)
    )
}

private fun getSliderOffset(
    value: Float,
    valueRange: ClosedFloatingPointRange<Float>,
    boxWidth: Dp,
    labelWidth: Dp
): Dp {
    val coerced = value.coerceIn(valueRange.start, valueRange.endInclusive)
    val positionFraction = calcFraction(valueRange.start, valueRange.endInclusive, coerced)

    return (boxWidth - labelWidth) * positionFraction
}

/**
 * Calculate the 0..1 fraction that `pos` value represents between `a` and `b`
 * Note: Copied from Slider.kt default implementation
 */
private fun calcFraction(a: Float, b: Float, pos: Float) =
    (if (b - a == 0f) 0f else (pos - a) / (b - a)).coerceIn(0f, 1f)


@UiModePreviews.Default
@Composable
private fun PreviewOdsSlider(@PreviewParameter(OdsSliderPreviewParameterProvider::class) withIcons: Boolean) = Preview {
    val sliderValue = remember { mutableStateOf(0.5f) }

    OdsSlider(
        value = sliderValue.value,
        onValueChange = { sliderValue.value = it },
        steps = 9,
        leftIcon = if (withIcons) painterResource(id = R.drawable.ic_crosset_out_eye) else null,
        rightIcon = if (withIcons) painterResource(id = R.drawable.ic_eye) else null,
    )
}

@UiModePreviews.Default
@Composable
private fun PreviewOdsSliderLockups(@PreviewParameter(OdsSliderPreviewParameterProvider::class) withIcons: Boolean) = Preview {
    var value by remember { mutableStateOf(50.0f) }
    OdsSliderLockups(
        value = value,
        valueRange = 0f..100f,
        onValueChange = { value = it },
        leftIcon = if (withIcons) painterResource(id = R.drawable.ic_crosset_out_eye) else null,
        rightIcon = if (withIcons) painterResource(id = R.drawable.ic_eye) else null,
    )
}

private class OdsSliderPreviewParameterProvider : BasicPreviewParameterProvider<Boolean>(false, true)
