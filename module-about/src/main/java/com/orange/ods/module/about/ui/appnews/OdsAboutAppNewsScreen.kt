/*
 * Software Name: Orange Design System
 * SPDX-FileCopyrightText: Copyright (c) Orange SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT licence,
 * the text of which is available at https://opensource.org/license/MIT/
 * or see the "LICENSE" file for more details.
 *
 * Software description: Android library of reusable graphical components 
 */

package com.orange.ods.module.about.ui.appnews

import androidx.annotation.RawRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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
import com.orange.ods.compose.text.OdsTextBody1
import com.orange.ods.compose.text.OdsTextCaption
import com.orange.ods.compose.text.OdsTextSubtitle1

@Composable
internal fun OdsAboutAppNewsScreen(@RawRes fileRes: Int, viewModel: OdsAboutAppNewsViewModel = viewModel(LocalContext.current as ViewModelStoreOwner)) {

    LaunchedEffect(Unit) {
        viewModel.getAppNews(fileRes)
    }

    LazyColumn(contentPadding = PaddingValues(bottom = dimensionResource(id = com.orange.ods.R.dimen.spacing_m))) {
        itemsIndexed(viewModel.appNews) { index, news ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = dimensionResource(id = com.orange.ods.R.dimen.spacing_m))
                    .padding(top = dimensionResource(id = com.orange.ods.R.dimen.spacing_m))
            ) {
                OdsTextSubtitle1(modifier = Modifier.weight(1f), text = news.version)
                OdsTextCaption(text = news.date)
            }
            OdsTextBody1(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = dimensionResource(id = com.orange.ods.R.dimen.spacing_m))
                    .padding(top = dimensionResource(id = com.orange.ods.R.dimen.spacing_s)),
                text = news.news
            )
            if (index + 1 < viewModel.appNews.size) {
                OdsDivider(modifier = Modifier.padding(top = dimensionResource(id = com.orange.ods.R.dimen.spacing_m)))
            }
        }
    }

}