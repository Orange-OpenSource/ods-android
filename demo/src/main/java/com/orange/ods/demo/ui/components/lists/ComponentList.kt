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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.compose.component.list.OdsListItem
import com.orange.ods.compose.component.list.OdsListItemIcon
import com.orange.ods.compose.component.list.OdsListItemWideThumbnail
import com.orange.ods.compose.component.list.OdsListSquaredThumbnail
import com.orange.ods.compose.component.utilities.OdsImageCircleShape
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.components.Variant

enum class ListIconType {
    None, Default, Avatar, SmallImage, WideImage
}

@ExperimentalMaterialApi
@Composable
fun ComponentList(variant: Variant) {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        VariantListContent(variant)
    }
}

@ExperimentalMaterialApi
@Composable
private fun VariantListContent(variant: Variant) {
    when (variant) {
        Variant.ListsOneLine -> ListOneLineContent()
        Variant.ListsTwoLines -> ListTwoLinesContent()
        Variant.ListsThreeLines -> ListThreeLinesContent()
        else -> {}
    }
}

@ExperimentalMaterialApi
@Composable
fun List(
    size: Int,
    text: String? = stringResource(id = R.string.component_element_title),
    secondaryText: String? = null,
    singleLineSecondaryText: Boolean = true,
    iconType: ListIconType = ListIconType.None,
    trailing: (Int) -> (@Composable () -> Unit)?
) {
    Spacer(modifier = Modifier.padding(top = dimensionResource(R.dimen.spacing_s)))

    val modifier = Modifier.clickable {}
    repeat(size) { index ->
        if (iconType == ListIconType.WideImage) {
            OdsListItemWideThumbnail(
                modifier = modifier,
                text = text.orEmpty(),
                secondaryText = secondaryText,
                singleLineSecondaryText = singleLineSecondaryText,
                thumbnail = painterResource(id = R.drawable.placeholder),
                trailing = trailing(index)
            )
        } else {
            val icon: @Composable (() -> Unit)? = when (iconType) {
                ListIconType.None -> null
                ListIconType.Default -> { -> OdsListItemIcon(painter = painterResource(id = R.drawable.ic_address_book)) }
                ListIconType.Avatar -> { -> OdsImageCircleShape(painter = painterResource(id = R.drawable.placeholder)) }
                ListIconType.SmallImage -> { -> OdsListSquaredThumbnail(painter = painterResource(id = R.drawable.placeholder)) }
                ListIconType.WideImage -> { -> OdsListSquaredThumbnail(painter = painterResource(id = R.drawable.placeholder)) }
            }
            OdsListItem(
                modifier = modifier,
                text = text.orEmpty(),
                secondaryText = secondaryText,
                singleLineSecondaryText = singleLineSecondaryText,
                icon = icon,
                trailing = trailing(index)
            )
        }

        val startIndent = when (iconType) {
            ListIconType.None -> dimensionResource(id = R.dimen.spacing_m)
            ListIconType.Default,
            ListIconType.Avatar -> dimensionResource(id = R.dimen.avatar_size) + dimensionResource(id = R.dimen.spacing_m).times(2)
            ListIconType.SmallImage -> dimensionResource(id = R.dimen.list_squared_thumbnail_size) + dimensionResource(id = R.dimen.spacing_m).times(2)
            ListIconType.WideImage -> dimensionResource(id = R.dimen.list_wide_thumbnail_width) + dimensionResource(id = R.dimen.spacing_m)
        }
        Divider(startIndent = startIndent)
    }
}
