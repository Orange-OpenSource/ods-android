/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.components.utilities

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.orange.ods.compose.text.OdsTextSubtitle1
import com.orange.ods.demo.R

@Composable
fun ComponentCountRow(
    title: String,
    count: MutableState<Int>,
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
        IconButton(onClick = { count.value-- }, enabled = count.value > minCount) {
            Icon(painter = painterResource(id = R.drawable.ic_remove), contentDescription = "Remove")
        }
        OdsTextSubtitle1(text = count.value.toString())
        IconButton(onClick = { count.value++ }, enabled = count.value < maxCount) {
            Icon(painter = painterResource(id = R.drawable.ic_add), contentDescription = "Add")
        }
    }
}
