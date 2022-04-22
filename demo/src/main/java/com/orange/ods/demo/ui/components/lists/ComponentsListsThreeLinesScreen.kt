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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.compose.component.list.OdsListItem
import com.orange.ods.compose.component.list.OdsListItemWideThumbnail
import com.orange.ods.compose.component.list.OdsListSquaredThumbnail
import com.orange.ods.compose.component.other.OdsImageCircleShape
import com.orange.ods.demo.R

@ExperimentalMaterialApi
@Composable
fun ComponentsListsThreeLinesScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(
                vertical = dimensionResource(id = R.dimen.ods_screen_vertical_margin)
            )
    ) {
        OdsListItem(
            modifier = Modifier.clickable { },
            text = stringResource(id = R.string.component_element_text),
            secondaryText = stringResource(id = R.string.component_element_secondary_text_value),
            singleLineSecondaryText = false,
            trailing = { Text(stringResource(id = R.string.component_element_caption)) }
        )
        Divider()
        OdsListItem(
            modifier = Modifier.clickable { },
            text = stringResource(id = R.string.component_element_text),
            overlineText = stringResource(id = R.string.component_element_overline),
            secondaryText = stringResource(id = R.string.component_element_secondary_text_value)
        )
        Divider()
        OdsListItem(
            modifier = Modifier.clickable { },
            text = stringResource(id = R.string.component_element_text),
            secondaryText = stringResource(id = R.string.component_element_secondary_text_value),
            singleLineSecondaryText = false,
            icon = { Icon(painter = painterResource(id = R.drawable.ic_heart), contentDescription = null) }
        )
        Divider()
        OdsListItem(
            modifier = Modifier.clickable { },
            text = stringResource(id = R.string.component_element_text),
            secondaryText = stringResource(id = R.string.component_element_secondary_text_value),
            singleLineSecondaryText = false,
            icon = { OdsImageCircleShape(painter = painterResource(id = R.drawable.placeholder), contentDescription = null) },
            trailing = { Text(text = stringResource(id = R.string.component_element_caption)) }
        )
        Divider()
        OdsListItem(
            modifier = Modifier.clickable { },
            text = stringResource(id = R.string.component_element_text),
            secondaryText = stringResource(id = R.string.component_element_secondary_text_value),
            singleLineSecondaryText = false,
            icon = { OdsListSquaredThumbnail(painter = painterResource(id = R.drawable.placeholder)) }
        )
        Divider()
        OdsListItemWideThumbnail(
            modifier = Modifier.clickable { },
            text = stringResource(id = R.string.component_element_text),
            secondaryText = stringResource(id = R.string.component_element_secondary_text_value),
            singleLineSecondaryText = false,
            thumbnail = painterResource(id = R.drawable.placeholder),
            trailing = { Text(text = stringResource(id = R.string.component_element_caption)) }
        )
        Divider()
    }
}