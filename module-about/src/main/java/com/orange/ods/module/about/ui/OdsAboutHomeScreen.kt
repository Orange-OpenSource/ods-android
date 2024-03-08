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

package com.orange.ods.module.about.ui

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.orange.ods.compose.component.button.OdsButton
import com.orange.ods.compose.component.button.OdsTextButton
import com.orange.ods.compose.component.listitem.OdsListItem
import com.orange.ods.compose.component.utilities.OdsPreview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.text.OdsText
import com.orange.ods.module.about.R
import com.orange.ods.module.about.ui.configuration.OdsAboutAccessibilityStatementMenuItemConfiguration
import com.orange.ods.module.about.ui.configuration.OdsAboutConfiguration
import com.orange.ods.module.about.ui.configuration.OdsAboutFileMenuItem
import com.orange.ods.module.about.ui.configuration.OdsAboutShareData
import com.orange.ods.theme.typography.OdsTextStyle

private const val ImageHeight = 249

@Composable
internal fun OdsAboutHomeScreen(configuration: OdsAboutConfiguration, onAboutMenuItemClick: (id: Int) -> Unit) {
    val context = LocalContext.current
    val menuItemById = configuration.menuItemById

    LazyColumn(contentPadding = PaddingValues(bottom = dimensionResource(id = com.orange.ods.R.dimen.screen_vertical_margin))) {
        item {
            Image(
                painter = painterResource(id = configuration.appIllustrationRes),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(ImageHeight.dp),
                contentScale = ContentScale.Crop,
                contentDescription = null,
            )

            Column(
                modifier = Modifier
                    .padding(horizontal = dimensionResource(id = com.orange.ods.R.dimen.spacing_m))
                    .padding(bottom = dimensionResource(id = com.orange.ods.R.dimen.spacing_m))
            ) {
                OdsText(
                    text = configuration.appName,
                    modifier = Modifier.padding(top = dimensionResource(id = com.orange.ods.R.dimen.spacing_m)),
                    style = OdsTextStyle.HeadlineL
                )
                Row(modifier = Modifier.padding(top = dimensionResource(id = com.orange.ods.R.dimen.spacing_s))) {
                    configuration.shareData?.let { shareData ->
                        OdsTextButton(
                            modifier = Modifier.padding(end = dimensionResource(id = com.orange.ods.R.dimen.spacing_s)),
                            text = stringResource(id = R.string.odsAbout_appShare),
                            icon = OdsButton.Icon(painterResource(id = R.drawable.ic_share)),
                            onClick = {
                                val sendIntent = Intent(Intent.ACTION_SEND).apply {
                                    type = "text/plain"
                                    putExtra(Intent.EXTRA_TITLE, shareData.title)
                                    putExtra(Intent.EXTRA_TEXT, shareData.text)
                                }
                                context.startActivity(Intent.createChooser(sendIntent, null))
                            },
                            style = OdsTextButton.Style.Primary
                        )
                    }
                    configuration.onFeedbackButtonClick?.let { feedbackAction ->
                        OdsTextButton(
                            text = stringResource(id = R.string.odsAbout_appFeedback),
                            icon = OdsButton.Icon(painterResource(id = R.drawable.ic_comment)),
                            onClick = feedbackAction,
                            style = OdsTextButton.Style.Primary
                        )
                    }
                }
                configuration.appVersion?.let { appVersion ->
                    OdsText(
                        text = appVersion,
                        modifier = Modifier.padding(top = dimensionResource(id = com.orange.ods.R.dimen.spacing_m)),
                        style = OdsTextStyle.BodyM
                    )
                }
                configuration.appDescription?.let { description ->
                    OdsText(
                        text = description,
                        modifier = Modifier.padding(top = dimensionResource(id = com.orange.ods.R.dimen.spacing_s)),
                        style = OdsTextStyle.BodyL
                    )
                }
            }
        }

        items(menuItemById.entries.toList()) { (id, menuItem) ->
            OdsListItem(
                leadingIcon = when (menuItem.graphicsObject) {
                    is Painter -> OdsListItem.LeadingIcon(OdsListItem.LeadingIcon.Type.Icon, painter = menuItem.graphicsObject, contentDescription = "")
                    is ImageVector -> OdsListItem.LeadingIcon(OdsListItem.LeadingIcon.Type.Icon, imageVector = menuItem.graphicsObject, contentDescription = "")
                    is ImageBitmap -> OdsListItem.LeadingIcon(OdsListItem.LeadingIcon.Type.Icon, bitmap = menuItem.graphicsObject, contentDescription = "")
                    else -> null
                },
                text = menuItem.text,
                secondaryText = menuItem.secondaryText,
                onClick = {
                    onAboutMenuItemClick(id)
                }
            )
        }
    }
}

@UiModePreviews.Default
@Composable
private fun PreviewOdsAboutHomeScreen() {
    val configuration = OdsAboutConfiguration(
        appName = "App name",
        privacyPolicyMenuItemFile = OdsAboutFileMenuItem.File(0, OdsAboutFileMenuItem.File.Format.Html),
        termsOfServiceMenuItemFile = OdsAboutFileMenuItem.File(0, OdsAboutFileMenuItem.File.Format.Html),
        accessibilityStatementMenuItemConfiguration = OdsAboutAccessibilityStatementMenuItemConfiguration(""),
        appVersion = "Version 1.0",
        appDescription = "Here is the description of the current application.",
        shareData = OdsAboutShareData("Title", "Text to display for sharing."),
        onFeedbackButtonClick = {}
    )

    OdsPreview {
        OdsAboutHomeScreen(configuration = configuration, onAboutMenuItemClick = {})
    }
}