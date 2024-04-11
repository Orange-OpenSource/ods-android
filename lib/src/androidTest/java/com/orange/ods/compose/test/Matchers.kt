/*
 * Software Name: Orange Design System
 * SPDX-FileCopyrightText: Copyright (c) Orange SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT license,
 * the text of which is available at https://opensource.org/license/MIT/
 * or see the "LICENSE" file for more details.
 *
 * Software description: Android library of reusable graphical components
 */

package com.orange.ods.compose.test

import android.content.Context
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import com.orange.ods.R
import com.orange.ods.compose.component.appbar.top.OdsTopAppBar
import com.orange.ods.compose.component.banner.OdsBanner
import com.orange.ods.compose.component.content.OdsComponentIcon
import com.orange.ods.compose.component.content.OdsComponentImage
import com.orange.ods.compose.component.menu.OdsDropdownMenu
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible

internal fun isTopAppBarNavigationIcon(navigationIcon: OdsTopAppBar.NavigationIcon) = isComponentIcon(navigationIcon)

internal fun isTopAppBarActionButton(action: OdsTopAppBar.ActionButton) = isComponentIcon(action)

internal fun isTopAppBarOverflowMenu(context: Context) = hasContentDescription(context.getString(R.string.ods_topAppBar_overflowMenu_labelA11y))

internal fun isBannerButton(button: OdsBanner.Button) = hasText(button.text.uppercase())

internal fun isBannerImage(image: OdsBanner.Image) = isComponentImage(image)

internal fun isDropdownMenuItem(item: OdsDropdownMenu.Item) = hasText(item.text)

internal fun isDropdownMenuItemDivider(itemIndex: Int) = hasTestTag("OdsDropdownMenu.Item.$itemIndex.Divider")

private fun isComponentIcon(icon: OdsComponentIcon<*>) = hasContentDescription(icon.getPrivateProperty("contentDescription"))

private fun isComponentImage(image: OdsComponentImage<*>) = hasContentDescription(image.getPrivateProperty("contentDescription"))

@Suppress("UNCHECKED_CAST")
private inline fun <reified T : Any, R> T.getPrivateProperty(name: String): R {
    return T::class.memberProperties
        .first { it.name == name }
        .apply { isAccessible = true }
        .get(this) as R
}
