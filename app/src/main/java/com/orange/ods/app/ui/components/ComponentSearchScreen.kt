/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.orange.ods.compose.component.list.OdsListItem
import com.orange.ods.compose.component.list.OdsListItemIcon
import com.orange.ods.compose.component.list.OdsListItemIconType
import com.orange.ods.compose.component.list.iconType
import com.orange.ods.compose.theme.OdsTheme
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.LocalMainTopAppBarManager
import com.orange.ods.utilities.extension.orElse

@Composable
fun ComponentSearchScreen(state: MutableState<TextFieldValue>) {

    LocalMainTopAppBarManager.current.updateTopAppBarTitle(R.string.navigation_item_search)
    ComponentList(state = state)
}

@Composable
fun SearchView(state: MutableState<TextFieldValue>) {
    TextField(
        value = state.value,
        onValueChange = { value ->
            state.value = value
        },
        placeholder = {
            Text(text = stringResource(id = R.string.component_search), color = Color.Gray, fontSize = 18.sp)
        },
        modifier = Modifier
            .fillMaxWidth(),
        textStyle = TextStyle(color = OdsTheme.colors.onSurface, fontSize = 18.sp),
        trailingIcon = {
            IconButton(
                onClick = {
                    state.value =
                        TextFieldValue("")// Remove text from TextField when you press the 'X' icon
                }
            ) {
                if (state.value != TextFieldValue("")) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "",
                        modifier = Modifier
                            .padding(15.dp)
                            .size(24.dp)
                    )
                }
            }
        },
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            cursorColor = OdsTheme.colors.primary,
            leadingIconColor = OdsTheme.colors.onSurface,
            trailingIconColor = OdsTheme.colors.onSurface,
            backgroundColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun ComponentList(state: MutableState<TextFieldValue>) {

    val searchedText = state.value.text
    val filterComponents = components.filter { component ->
        searchedText.isEmpty() || stringResource(id = component.titleRes).lowercase().contains(searchedText.lowercase())
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        items(filterComponents) { component ->
            val componentImageRes = component.smallImageRes.orElse { component.imageRes }
            OdsListItem(
                text = stringResource(id = component.titleRes),
                secondaryText = null,
                singleLineSecondaryText = false,
                modifier = Modifier.iconType(OdsListItemIconType.SquareImage),
                icon = {
                    OdsListItemIcon(
                        painterResource(id = componentImageRes)
                    )
                },
            )
        }
    }
}
