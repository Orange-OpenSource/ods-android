/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.guidelines.spacings

import androidx.annotation.DimenRes
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.orange.ods.demo.R

enum class Spacing(
    val tokenName: String,
    val dp: Dp,
    @DimenRes val dimenRes: Int
) {
    None("spacing - none", 0.dp, R.dimen.spacing_none),
    ExtraSmall("spacing - xs", 4.dp, R.dimen.spacing_xs),
    Small("spacing - s", 8.dp, R.dimen.spacing_s),
    Medium("spacing - m", 16.dp, R.dimen.spacing_m),
    Large("spacing - l", 24.dp, R.dimen.spacing_l),
    ExtraLarge("spacing - xl", 32.dp, R.dimen.spacing_xl),
    ExtraExtraLarge("spacing - 2xl", 40.dp, R.dimen.spacing_2xl);

    val ratio: Float
        get() = dp / Small.dp
}
