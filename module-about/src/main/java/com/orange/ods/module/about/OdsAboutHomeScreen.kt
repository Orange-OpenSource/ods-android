/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.module.about

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.orange.ods.compose.component.button.OdsButton
import com.orange.ods.compose.component.button.OdsTextButton
import com.orange.ods.compose.component.listitem.OdsListItem
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.text.OdsTextBody1
import com.orange.ods.compose.text.OdsTextBody2
import com.orange.ods.compose.text.OdsTextH4
import com.orange.ods.module.about.configuration.OdsAboutModuleConfiguration
import com.orange.ods.module.about.configuration.OdsAboutShareData

private const val ImageHeight = 249

@Composable
internal fun OdsAboutHomeScreen(configuration: OdsAboutModuleConfiguration, onAboutMenuItemClick: (id: Int) -> Unit) {
    val context = LocalContext.current
    val menuItemById = configuration.menuItemById

    LazyColumn {
        item {
            Image(
                painter = painterResource(id = configuration.appIllustration),
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
                OdsTextH4(
                    text = configuration.appName,
                    modifier = Modifier.padding(top = dimensionResource(id = com.orange.ods.R.dimen.spacing_m))
                )
                Row(modifier = Modifier.padding(top = dimensionResource(id = com.orange.ods.R.dimen.spacing_s))) {
                    configuration.shareData?.let { shareData ->
                        OdsTextButton(
                            modifier = Modifier.padding(end = dimensionResource(id = com.orange.ods.R.dimen.spacing_s)),
                            text = stringResource(id = R.string.ods_about_app_share),
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
                            text = stringResource(id = R.string.ods_about_app_feedback),
                            icon = OdsButton.Icon(painterResource(id = R.drawable.ic_comment)),
                            onClick = feedbackAction,
                            style = OdsTextButton.Style.Primary
                        )
                    }
                }
                configuration.appVersion?.let { appVersion ->
                    OdsTextBody2(
                        text = appVersion,
                        modifier = Modifier.padding(top = dimensionResource(id = com.orange.ods.R.dimen.spacing_m))
                    )
                }
                configuration.appDescription?.let { description ->
                    OdsTextBody1(
                        text = description,
                        modifier = Modifier.padding(top = dimensionResource(id = com.orange.ods.R.dimen.spacing_s))
                    )
                }
            }
        }

        items(menuItemById.entries.toList()) { (id, menuItem) ->
            OdsListItem(
                leadingIcon = OdsListItem.LeadingIcon(OdsListItem.LeadingIcon.Type.Icon, painter = menuItem.icon, contentDescription = ""),
                text = menuItem.text,
                secondaryText = menuItem.secondaryText,
                onClick = {
                    onAboutMenuItemClick(id)
                }
            )
        }

        item { Spacer(modifier = Modifier.height(dimensionResource(id = com.orange.ods.R.dimen.spacing_s))) }
    }
}

@UiModePreviews.Default
@Composable
fun PreviewOdsAboutMainScreen() {
    val configuration = OdsAboutModuleConfiguration(
        appName = "App name",
        appVersion = "Version 1.0",
        appDescription = "Here is the description of the current application.",
        shareData = OdsAboutShareData("Title", "Text to display for sharing."),
        onFeedbackButtonClick = {}
    )

    Preview {
        OdsAboutHomeScreen(configuration = configuration, onAboutMenuItemClick = {})
    }
}