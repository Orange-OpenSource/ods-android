/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.ui.components.utilities

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.LiveRegionMode
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.liveRegion
import androidx.compose.ui.semantics.semantics
import com.orange.ods.app.R
import com.orange.ods.compose.component.button.OdsIconButton
import com.orange.ods.compose.component.button.OdsIconButtonIcon
import com.orange.ods.compose.text.OdsTextSubtitle1

@Composable
fun ComponentCountRow(
    title: String,
    count: MutableState<Int>,
    minusIconContentDescription: String,
    plusIconContentDescription: String,
    modifier: Modifier = Modifier,
    minCount: Int = 1,
    maxCount: Int = Int.MAX_VALUE
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        OdsTextSubtitle1(modifier = Modifier.weight(1f), text = title)
        OdsIconButton(
            onClick = { count.value-- },
            icon = OdsIconButtonIcon(painterResource(id = R.drawable.ic_remove), minusIconContentDescription),
            enabled = count.value > minCount
        )

        OdsTextSubtitle1(text = count.value.toString(), modifier = Modifier.semantics {
            this.contentDescription = count.value.toString()
            liveRegion = LiveRegionMode.Polite
        })
        OdsIconButton(
            onClick = { count.value++ },
            icon = OdsIconButtonIcon(painterResource(id = R.drawable.ic_add), plusIconContentDescription),
            enabled = count.value < maxCount
        )
    }
}
