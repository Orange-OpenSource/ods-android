/*
 * Software Name: Orange Design System
 * SPDX-FileCopyrightText: Copyright (c) Orange SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT licence,
 * the text of which is available at https://opensource.org/license/MIT/
 * or see the "LICENSE" file for more details.
 *
 * Software description: Android library of reusable graphical components 
 */

package com.orange.ods.xml.component.bottomnavigation

import android.content.Context
import android.util.AttributeSet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.orange.ods.compose.component.bottomnavigation.OdsBottomNavigation
import com.orange.ods.xml.component.OdsAbstractComposeView


class OdsBottomNavigation @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : OdsAbstractComposeView(context, attrs) {

    var items by mutableStateOf<List<OdsBottomNavigation.Item>>(emptyList())

    @Composable
    override fun OdsContent() {
        OdsBottomNavigation(items = items)
    }
}
