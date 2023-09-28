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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.orange.ods.compose.component.button.OdsButtonIcon
import com.orange.ods.compose.component.button.OdsTextButton
import com.orange.ods.compose.component.button.OdsTextButtonStyle
import com.orange.ods.compose.text.OdsTextBody1
import com.orange.ods.compose.text.OdsTextBody2
import com.orange.ods.compose.text.OdsTextH4
import com.orange.ods.module.about.configuration.OdsAboutModuleConfiguration

private const val ImageHeight = 249

@Composable
internal fun OdsAboutMainScreen(configuration: OdsAboutModuleConfiguration) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(bottom = dimensionResource(id = com.orange.ods.R.dimen.screen_vertical_margin))
    ) {
        Image(
            painter = painterResource(id = configuration.appIllustration),
            modifier = Modifier
                .fillMaxWidth()
                .height(ImageHeight.dp),
            contentScale = ContentScale.Crop,
            contentDescription = null,
        )

        Column(Modifier.padding(horizontal = dimensionResource(id = com.orange.ods.R.dimen.screen_horizontal_margin))) {
            OdsTextH4(
                text = configuration.appName,
                modifier = Modifier.padding(top = dimensionResource(id = com.orange.ods.R.dimen.spacing_m))
            )
            Row(modifier = Modifier.padding(top = dimensionResource(id = com.orange.ods.R.dimen.spacing_s))) {
                configuration.shareData?.let { shareData ->
                    OdsTextButton(
                        modifier = Modifier.padding(end = dimensionResource(id = com.orange.ods.R.dimen.spacing_s)),
                        text = stringResource(id = R.string.app_share),
                        icon = OdsButtonIcon(painterResource(id = R.drawable.ic_share)),
                        onClick = {
                            val sendIntent = Intent(Intent.ACTION_SEND).apply {
                                type = "text/plain"
                                putExtra(Intent.EXTRA_TITLE, shareData.title)
                                putExtra(Intent.EXTRA_TEXT, shareData.text)
                            }
                            context.startActivity(Intent.createChooser(sendIntent, null))
                        },
                        style = OdsTextButtonStyle.Primary
                    )
                }
                configuration.onFeedbackButtonClick?.let { feedbackAction ->
                    OdsTextButton(
                        text = stringResource(id = R.string.app_feedback),
                        icon = OdsButtonIcon(painterResource(id = R.drawable.ic_comment)),
                        onClick = feedbackAction,
                        style = OdsTextButtonStyle.Primary
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
}