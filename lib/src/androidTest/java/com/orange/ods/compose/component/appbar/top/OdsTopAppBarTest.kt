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

package com.orange.ods.compose.component.appbar.top

import androidx.activity.ComponentActivity
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.orange.ods.compose.component.menu.OdsDropdownMenu
import com.orange.ods.compose.extension.setOdsContent
import com.orange.ods.compose.test.isDropdownMenuItem
import com.orange.ods.compose.test.isDropdownMenuItemDivider
import com.orange.ods.compose.test.isTopAppBarActionButton
import com.orange.ods.compose.test.isTopAppBarNavigationIcon
import com.orange.ods.compose.test.isTopAppBarOverflowMenu
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.kotlin.never

class OdsTopAppBarTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun odsTopAppBarRendersAsExpected() {
        with(composeTestRule) {
            val title = "Title"
            val navigationIcon = OdsTopAppBar.NavigationIcon(Icons.AutoMirrored.Filled.ArrowBack, "Navigation icon") {}
            lateinit var actions: List<OdsTopAppBar.ActionButton>
            val overflowMenuItems = listOf(
                OdsDropdownMenu.Item("Settings") {},
                OdsDropdownMenu.Item("Account") {}
            )

            setOdsContent {
                actions = listOf(
                    OdsTopAppBar.ActionButton(painterResource(id = android.R.drawable.ic_dialog_info), "Info") {},
                    OdsTopAppBar.ActionButton(painterResource(id = android.R.drawable.ic_dialog_email), "Email") {}
                )
                OdsTopAppBar(
                    title = title,
                    navigationIcon = navigationIcon,
                    actions = actions,
                    overflowMenuItems = overflowMenuItems
                )
            }

            onNodeWithText(title).assertIsDisplayed()
            onNode(isTopAppBarNavigationIcon(navigationIcon)).assertIsDisplayed()
            actions.forEach { onNode(isTopAppBarActionButton(it)).assertIsDisplayed() }
            onNode(isTopAppBarOverflowMenu(composeTestRule.activity)).assertIsDisplayed()
            overflowMenuItems.forEach { onNode(isDropdownMenuItem(it)).assertIsNotDisplayed() }
        }
    }

    @Test
    fun odsTopAppBarNavigationIconClickSucceeds() {
        with(composeTestRule) {
            val navigationIcon = OdsTopAppBar.NavigationIcon(Icons.AutoMirrored.Filled.ArrowBack, "Navigation icon", mock())
            setOdsContent {
                OdsTopAppBar(
                    title = "Title",
                    navigationIcon = navigationIcon
                )
            }

            onNode(isTopAppBarNavigationIcon(navigationIcon)).performClick()
            verify(navigationIcon.onClick).invoke()
        }
    }

    @Test
    fun odsTopAppBarActionClickSucceeds() {
        with(composeTestRule) {
            lateinit var actions: List<OdsTopAppBar.ActionButton>

            setOdsContent {
                actions = listOf(
                    OdsTopAppBar.ActionButton(painterResource(id = android.R.drawable.ic_dialog_info), "Info", onClick = mock()),
                    OdsTopAppBar.ActionButton(painterResource(id = android.R.drawable.ic_dialog_email), "Email", onClick = mock())
                )
                OdsTopAppBar(
                    title = "Title",
                    actions = actions
                )
            }

            onNode(isTopAppBarActionButton(actions[1])).performClick()
            verify(actions[0].onClick, never()).invoke()
            verify(actions[1].onClick).invoke()
        }
    }

    @Test
    fun odsTopAppBarActionIsDisabled() {
        with(composeTestRule) {
            lateinit var actions: List<OdsTopAppBar.ActionButton>

            setOdsContent {
                actions = listOf(
                    OdsTopAppBar.ActionButton(painterResource(id = android.R.drawable.ic_dialog_info), "Info", enabled = false) {},
                    OdsTopAppBar.ActionButton(painterResource(id = android.R.drawable.ic_dialog_email), "Email") {}
                )
                OdsTopAppBar(
                    title = "Title",
                    actions = actions
                )
            }

            onNode(isTopAppBarActionButton(actions[0])).assertIsNotEnabled()
            onNode(isTopAppBarActionButton(actions[1])).assertIsEnabled()
        }
    }

    @Test
    fun odsTopAppBarOverflowMenuClickSucceeds() {
        with(composeTestRule) {
            val overflowMenuItems = listOf(
                OdsDropdownMenu.Item("Settings", onClick = mock()),
                OdsDropdownMenu.Item("Account", onClick = mock())
            )

            setOdsContent {
                OdsTopAppBar(
                    title = "Title",
                    overflowMenuItems = overflowMenuItems
                )
            }

            onNode(isTopAppBarOverflowMenu(composeTestRule.activity)).performClick()
            overflowMenuItems.forEach { onNode(isDropdownMenuItem(it)).assertIsDisplayed() }
            onNode(isDropdownMenuItem(overflowMenuItems[1])).performClick()
            verify(overflowMenuItems[0].onClick, never()).invoke()
            verify(overflowMenuItems[1].onClick).invoke()
        }
    }

    @Test
    fun odsTopAppBarOverflowMenuItemIsDisabled() {
        with(composeTestRule) {
            val overflowMenuItems = listOf(
                OdsDropdownMenu.Item("Settings", enabled = false) {},
                OdsDropdownMenu.Item("Account") {}
            )

            setOdsContent {
                OdsTopAppBar(
                    title = "Title",
                    overflowMenuItems = overflowMenuItems
                )
            }

            onNode(isTopAppBarOverflowMenu(composeTestRule.activity)).performClick()
            onNode(isDropdownMenuItem(overflowMenuItems[0])).assertIsNotEnabled()
            onNode(isDropdownMenuItem(overflowMenuItems[1])).assertIsEnabled()
        }
    }

    @Test
    fun odsTopAppBarOverflowMenuItemDividerIsDisplayed() {
        with(composeTestRule) {
            val overflowMenuItems = listOf(
                OdsDropdownMenu.Item("Settings", divider = true) {},
                OdsDropdownMenu.Item("Account") {}
            )

            setOdsContent {
                OdsTopAppBar(
                    title = "Title",
                    overflowMenuItems = overflowMenuItems
                )
            }

            onNode(isTopAppBarOverflowMenu(composeTestRule.activity)).performClick()
            onNode(isDropdownMenuItemDivider(0)).assertIsDisplayed()
            onNode(isDropdownMenuItemDivider(1)).assertIsNotDisplayed()
        }
    }
}
