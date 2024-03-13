/*
 * Software Name: Orange Design System
 * SPDX-FileCopyrightText: Copyright (c) Orange SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT license,
 * the text of which is available at https://opensource.org/license/MIT/
 * or see the "LICENSE" file for more details.
 *
 * Software description: Android library of reusable graphical components 
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
