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

package com.orange.ods.app.ui.components.utilities

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.LiveRegionMode
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.liveRegion
import androidx.compose.ui.semantics.semantics
import com.orange.ods.app.R
import com.orange.ods.compose.component.button.OdsIconButton
import com.orange.ods.compose.text.OdsTextSubtitle1

@Composable
fun ComponentCountRow(
    title: String,
    count: MutableIntState,
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
            onClick = { count.intValue-- },
            icon = OdsIconButton.Icon(painterResource(id = R.drawable.ic_remove), minusIconContentDescription),
            enabled = count.intValue > minCount
        )

        OdsTextSubtitle1(text = count.intValue.toString(), modifier = Modifier.semantics {
            this.contentDescription = count.intValue.toString()
            liveRegion = LiveRegionMode.Polite
        })
        OdsIconButton(
            onClick = { count.intValue++ },
            icon = OdsIconButton.Icon(painterResource(id = R.drawable.ic_add), plusIconContentDescription),
            enabled = count.intValue < maxCount
        )
    }
}
