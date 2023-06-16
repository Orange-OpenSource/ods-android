/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.xml.component.button

import android.content.Context
import android.util.AttributeSet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.orange.ods.compose.component.button.OdsIconToggleButtonsRow
import com.orange.ods.compose.component.button.OdsIconToggleButtonsRowItem
import com.orange.ods.compose.theme.OdsDisplaySurface
import com.orange.ods.xml.component.OdsAbstractComposeView

class OdsIconToggleButtonsRow @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : OdsAbstractComposeView(context, attrs) {

    var iconToggleButtons by mutableStateOf<List<OdsIconToggleButtonsRowItem>?>(null)
    var selectedIndex by mutableStateOf<Int>(0)
    var onSelectedIndexChange by mutableStateOf<(Int) -> Unit>({})
    var displaySurface by mutableStateOf<OdsDisplaySurface>(OdsDisplaySurface.Default)

    @Composable
    override fun OdsContent() {
        iconToggleButtons?.let {
            OdsIconToggleButtonsRow(
                iconToggleButtons = it,
                selectedIndex = selectedIndex,
                onSelectedIndexChange = onSelectedIndexChange,
                displaySurface = displaySurface
            )
        }
    }
}