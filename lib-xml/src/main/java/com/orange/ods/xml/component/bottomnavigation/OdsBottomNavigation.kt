/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.xml.component.bottomnavigation

import android.content.Context
import android.util.AttributeSet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.orange.ods.compose.component.bottomnavigation.OdsBottomNavigation
import com.orange.ods.compose.component.bottomnavigation.OdsBottomNavigationItemBuilder
import com.orange.ods.xml.component.OdsAbstractComposeView


class OdsBottomNavigation @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : OdsAbstractComposeView(context, attrs) {

    var items by mutableStateOf<List<OdsBottomNavigationItemBuilder>>(emptyList())

    @Composable
    override fun OdsContent() {
        OdsBottomNavigation(items = items)
    }
}
