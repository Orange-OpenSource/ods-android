/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.components.lists

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.compose.component.control.OdsCheckbox
import com.orange.ods.compose.component.list.OdsListItem
import com.orange.ods.compose.component.list.OdsListItemIcon
import com.orange.ods.compose.component.list.OdsListItemWideThumbnail
import com.orange.ods.compose.component.list.OdsListSquaredThumbnail
import com.orange.ods.compose.component.other.OdsImageCircleShape
import com.orange.ods.demo.R

@ExperimentalMaterialApi
@Composable
fun ComponentsListsOneLineScreen() {
    var item1Checked by remember { mutableStateOf(false) }
    var item2Checked by remember { mutableStateOf(false) }
    var item3Checked by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        OdsListItem(
            modifier = Modifier.clickable { },
            text = stringResource(id = R.string.component_element_text)
        )
        Divider()
        OdsListItem(
            modifier = Modifier.clickable { },
            text = stringResource(id = R.string.component_element_text),
            trailing = { OdsCheckbox(checked = item1Checked, onCheckedChange = { item1Checked = it }) }
        )
        Divider()
        OdsListItem(
            modifier = Modifier.clickable { },
            text = stringResource(id = R.string.component_element_text),
            icon = { OdsListItemIcon(iconRes = R.drawable.ic_heart) },
            trailing = { OdsCheckbox(checked = item2Checked, onCheckedChange = { item2Checked = it }) }
        )
        Divider()
        OdsListItem(
            modifier = Modifier.clickable { },
            text = stringResource(id = R.string.component_element_text),
            icon = { OdsImageCircleShape(painter = painterResource(id = R.drawable.placeholder)) }
        )
        Divider()
        OdsListItem(
            modifier = Modifier.clickable { },
            text = stringResource(id = R.string.component_element_text),
            icon = { OdsListSquaredThumbnail(thumbnailRes = R.drawable.placeholder) },
            isThumbnailIcon = true
        )
        Divider()
        OdsListItemWideThumbnail(
            modifier = Modifier.clickable { },
            text = stringResource(id = R.string.component_element_text),
            thumbnail = painterResource(id = R.drawable.placeholder),
            trailing = { OdsCheckbox(checked = item3Checked, onCheckedChange = { item3Checked = it }) }
        )
        Divider()
    }
}