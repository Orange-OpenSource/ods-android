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

package com.orange.ods.compose.component.banner

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.test.assertContentDescriptionEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.orange.ods.R
import com.orange.ods.compose.extension.setOdsContent
import com.orange.ods.compose.test.isBannerButton
import com.orange.ods.compose.test.isBannerImage
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

internal class OdsBannerTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun odsBannerRendersAsExpected() {
        with(composeTestRule) {
            val message = "Message"
            lateinit var image: OdsBanner.Image
            val firstButton = OdsBanner.Button("First button") {}
            val secondButton = OdsBanner.Button("Second button") {}

            setOdsContent {
                image = OdsBanner.Image(painterResource(id = R.drawable.placeholder), "Image")
                OdsBanner(
                    message = message,
                    image = image,
                    firstButton = firstButton,
                    secondButton = secondButton
                )
            }

            onNodeWithText(message).assertIsDisplayed()
            onNode(isBannerImage(image)).apply {
                assertIsDisplayed()
                assertContentDescriptionEquals(image.contentDescription)
            }
            onNode(isBannerButton(firstButton)).assertIsDisplayed()
            onNode(isBannerButton(secondButton)).assertIsDisplayed()
        }
    }

    @Test
    fun odsBannerButtonClickSucceeds() {
        with(composeTestRule) {
            val firstButton = OdsBanner.Button("First button", mock())
            val secondButton = OdsBanner.Button("Second button", mock())

            setOdsContent {
                OdsBanner(
                    message = "Message",
                    firstButton = firstButton,
                    secondButton = secondButton
                )
            }

            onNode(isBannerButton(firstButton)).performClick()
            onNode(isBannerButton(secondButton)).performClick()
            verify(firstButton.onClick).invoke()
            verify(secondButton.onClick).invoke()
        }
    }
}
