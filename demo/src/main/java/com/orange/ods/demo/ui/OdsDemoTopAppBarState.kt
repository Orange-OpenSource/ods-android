/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import com.google.accompanist.pager.ExperimentalPagerApi
import com.orange.ods.demo.R

@Composable
@ExperimentalPagerApi
@ExperimentalMaterialApi
fun rememberOdsDemoTopAppBarState(
    topAppBarType: MutableState<OdsDemoTopAppBarType> = rememberSaveable { mutableStateOf(OdsDemoTopAppBarType.Default) },
    topAppBarTitleRes: MutableState<Int> = rememberSaveable { mutableStateOf(R.string.navigation_item_guidelines) }
) =
    remember(topAppBarType, topAppBarTitleRes) {
        OdsDemoTopAppBarState(topAppBarType, topAppBarTitleRes)
    }

class OdsDemoTopAppBarState(
    val topAppBarType: MutableState<OdsDemoTopAppBarType>,
    val topAppBarTitleRes: MutableState<Int>,
) {

    // ----------------------------------------------------------
    // TopAppBar state source of truth
    // ----------------------------------------------------------

    fun updateTopAppBarType(topAppBarType: OdsDemoTopAppBarType) {
        this.topAppBarType.value = topAppBarType
    }

    fun updateTopAppBarTitle(titleRes: Int) {
        topAppBarTitleRes.value = titleRes
    }
}