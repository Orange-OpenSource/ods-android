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
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.dimensionResource
import com.orange.ods.demo.R

enum class Spacing(val tokenName: String, @DimenRes val dimenRes: Int) {

    None("spacing - none", R.dimen.spacing_none),
    ExtraSmall("spacing - xs", R.dimen.spacing_xs),
    Small("spacing - s", R.dimen.spacing_s),
    Medium("spacing - m", R.dimen.spacing_m),
    Large("spacing - l", R.dimen.spacing_l),
    ExtraLarge("spacing - xl", R.dimen.spacing_xl),
    ExtraExtraLarge("spacing - 2xl", R.dimen.spacing_2xl);

    @Composable
    fun getDp() = dimensionResource(id = dimenRes)
    
    @Composable
    fun getRatio() = getDp() / Small.getDp()
}
