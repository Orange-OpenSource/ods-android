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

package com.orange.ods.module.about.ui.appnews

import androidx.annotation.RawRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.orange.ods.compose.component.divider.OdsDivider
import com.orange.ods.compose.text.OdsText
import com.orange.ods.theme.typography.OdsTextStyle

@Composable
internal fun OdsAboutAppNewsScreen(@RawRes fileRes: Int, viewModel: OdsAboutAppNewsViewModel = viewModel(LocalContext.current as ViewModelStoreOwner)) {

    LaunchedEffect(Unit) {
        viewModel.getAppNews(fileRes)
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = dimensionResource(id = com.orange.ods.R.dimen.spacing_m))
    ) {
        itemsIndexed(viewModel.appNews) { index, news ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = dimensionResource(id = com.orange.ods.R.dimen.spacing_m))
                    .padding(top = dimensionResource(id = com.orange.ods.R.dimen.spacing_m))
            ) {
                OdsText(modifier = Modifier.weight(1f), text = news.version, style = OdsTextStyle.TitleM)
                OdsText(text = news.date, style = OdsTextStyle.BodyS)
            }
            OdsText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = dimensionResource(id = com.orange.ods.R.dimen.spacing_m))
                    .padding(top = dimensionResource(id = com.orange.ods.R.dimen.spacing_s)),
                text = news.news,
                style = OdsTextStyle.BodyL
            )
            if (index + 1 < viewModel.appNews.size) {
                OdsDivider(modifier = Modifier.padding(top = dimensionResource(id = com.orange.ods.R.dimen.spacing_m)))
            }
        }
    }

}