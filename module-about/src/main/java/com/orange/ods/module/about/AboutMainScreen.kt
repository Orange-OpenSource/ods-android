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

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.orange.ods.compose.text.OdsTextCaption
import com.orange.ods.compose.text.OdsTextH4

private const val ImageHeight = 249

@Composable
internal fun AboutMainScreen() {
    val configuration = LocalAboutModuleConfiguration.current
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
                modifier = Modifier.padding(top = dimensionResource(id = com.orange.ods.R.dimen.spacing_xl))
            )
            configuration.appVersion?.let { appVersion ->
                OdsTextCaption(
                    text = appVersion,
                    modifier = Modifier.padding(top = dimensionResource(id = com.orange.ods.R.dimen.spacing_xs))
                )
            }
            configuration.appDescription?.let { description ->
                OdsTextCaption(
                    text = description,
                    modifier = Modifier.padding(top = dimensionResource(id = com.orange.ods.R.dimen.spacing_xs))
                )
            }
        }
    }
}