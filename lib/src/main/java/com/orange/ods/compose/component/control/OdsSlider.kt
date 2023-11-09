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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.orange.ods.R
import com.orange.ods.compose.component.OdsComposable
import com.orange.ods.compose.component.content.OdsComponentIcon
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
 * @param value Current value of the slider. If outside of `valueRange` provided, value will be coerced to this range.
 * @param onValueChange Callback invoked on slider value change. `value` should be updated here.
 * @param modifier [Modifier] applied to the slider.
 * @param enabled Controls the enabled state of the slider. If `false`, the user cannot interact with it.
 * @param valueRange Range of values that the slider can take. Given [value] will be coerced to this range.
 * @param steps If greater than `0`, specifies the amounts of discrete values, evenly distributed between across the whole value range. If `0`, slider will
 * behave as a continuous slider and allow to choose any value from the range specified. Must not be negative.
 * @param onValueChangeFinished Callback invoked when value change has ended. This callback shouldn't be used to update
 * the slider value (use [onValueChange] for that), but rather to know when the user has completed selecting a new value by ending a drag or a click.
 * @param startIcon [OdsSlider.Icon] displayed at the start of the slider.
 * @param endIcon [OdsSlider.Icon] displayed at the end of the slider.
 */
@Composable
@OdsComposable
fun OdsSlider(
    value: Float,
    onValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    steps: Int = 0,
    onValueChangeFinished: (() -> Unit)? = null,
    startIcon: OdsSlider.Icon? = null,
    endIcon: OdsSlider.Icon? = null
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacing_m))
    ) {
        startIcon?.Content()
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
        endIcon?.Content()
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
 * @param value Current value of the slider. If outside of `valueRange` provided, value will be coerced to this range.
 * @param onValueChange Callback invoked on slider value change. `value` should be updated here.
 * @param modifier [Modifier] applied to the slider.
 * @param enabled Controls the enabled state of the slider. If `false`, the user cannot interact with it.
 * @param valueRange Range of values that the slider can take. Given [value] will be coerced to this range.
 * @param steps If greater than `0`, specifies the amounts of discrete values, evenly distributed between across the whole value range. If `0`, slider will
 * behave as a continuous slider and allow to choose any value from the range specified. Must not be negative.
 * @param onValueChangeFinished Callback invoked when value change has ended. This callback shouldn't be used to update
 * the slider value (use [onValueChange] for that), but rather to know when the user has completed selecting a new value by ending a drag or a click.
 * @param startIcon [OdsSlider.Icon] displayed at the start of the slider.
 * @param endIcon [OdsSlider.Icon] displayed at the end of the slider.
 */
@Composable
@OdsComposable
fun OdsSliderLockups(
    value: Float,
    onValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    steps: Int = 0,
    onValueChangeFinished: (() -> Unit)? = null,
    startIcon: OdsSlider.Icon? = null,
    endIcon: OdsSlider.Icon? = null
) {
    val labelMinWidth = 32.dp
    val sideIconBottomPadding = 12.dp

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacing_xs))
    ) {
        startIcon?.Content(
            modifier = Modifier
                .align(alignment = Alignment.Bottom)
                .padding(bottom = sideIconBottomPadding)
        )
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
        endIcon?.Content(
            modifier = Modifier
                .align(alignment = Alignment.Bottom)
                .padding(bottom = sideIconBottomPadding),
        )
    }
}

/**
 * Contains classes to build an [com.orange.ods.compose.component.control.OdsSlider] or an [com.orange.ods.compose.component.control.OdsSliderLockups].
 */
class OdsSlider {

    /**
     * An icon in an [OdsSlider] or an [OdsSliderLockups].
     */
    class Icon : OdsComponentIcon<Nothing> {

        /**
         * Creates an instance of [OdsSlider.Icon].
         *
         * @param painter Painter of the icon.
         * @param contentDescription The content description associated to this [OdsSlider.Icon].
         */
        constructor(painter: Painter, contentDescription: String) : super(painter, contentDescription)

        /**
         * Creates an instance of [OdsSlider.Icon].
         *
         * @param imageVector Image vector of the icon.
         * @param contentDescription The content description associated to this [OdsSlider.Icon].
         */
        constructor(imageVector: ImageVector, contentDescription: String) : super(imageVector, contentDescription)

        /**
         * Creates an instance of [OdsSlider.Icon].
         *
         * @param bitmap Image bitmap of the icon.
         * @param contentDescription The content description associated to this [OdsSlider.Icon].
         */
        constructor(bitmap: ImageBitmap, contentDescription: String) : super(bitmap, contentDescription)

        override val tint: Color?
            @Composable
            get() = OdsTheme.colors.onSurface
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
        startIcon = if (withIcons) OdsSlider.Icon(painterResource(id = R.drawable.ic_crosset_out_eye), "") else null,
        endIcon = if (withIcons) OdsSlider.Icon(painterResource(id = R.drawable.ic_eye), "") else null,
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
        startIcon = if (withIcons) OdsSlider.Icon(painterResource(id = R.drawable.ic_crosset_out_eye), "") else null,
        endIcon = if (withIcons) OdsSlider.Icon(painterResource(id = R.drawable.ic_eye), "") else null,
    )
}

private class OdsSliderPreviewParameterProvider : BasicPreviewParameterProvider<Boolean>(false, true)
