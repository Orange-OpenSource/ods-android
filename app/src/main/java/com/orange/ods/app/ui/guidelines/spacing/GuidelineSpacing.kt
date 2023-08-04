/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.ui.guidelines.spacing

import androidx.annotation.DimenRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.dimensionResource

enum class Spacing(val tokenName: String, @DimenRes val dimenRes: Int) {

    None("spacing - none", com.orange.ods.R.dimen.spacing_none),
    ExtraSmall("spacing - xs", com.orange.ods.R.dimen.spacing_xs),
    Small("spacing - s", com.orange.ods.R.dimen.spacing_s),
    Medium("spacing - m", com.orange.ods.R.dimen.spacing_m),
    Large("spacing - l", com.orange.ods.R.dimen.spacing_l),
    ExtraLarge("spacing - xl", com.orange.ods.R.dimen.spacing_xl),
    ExtraExtraLarge("spacing - 2xl", com.orange.ods.R.dimen.spacing_2xl);

    @Composable
    fun getDp() = dimensionResource(id = dimenRes)

    @Composable
    fun getRatio() = getDp() / Small.getDp()
}
