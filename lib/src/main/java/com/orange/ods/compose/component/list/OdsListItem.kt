/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.compose.component.list

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
@ExperimentalMaterialApi
fun OdsListItem(
    modifier: Modifier = Modifier,
    text: String
) {
    Row(modifier = modifier) {
        ListItem(
            text = { Text(text) },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@ExperimentalMaterialApi
@Preview
@Composable
fun OdsListItemPreview() {
    OdsListItem(text = "test")
}