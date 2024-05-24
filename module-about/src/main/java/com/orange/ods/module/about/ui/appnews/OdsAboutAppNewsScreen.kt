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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.orange.ods.compose.component.divider.OdsDivider
import com.orange.ods.compose.text.OdsText
import com.orange.ods.compose.theme.OdsTheme

@Composable
internal fun OdsAboutAppNewsScreen(@RawRes fileRes: Int, viewModel: OdsAboutAppNewsViewModel = viewModel(LocalContext.current as ViewModelStoreOwner)) {

    LaunchedEffect(Unit) {
        viewModel.getAppNews(fileRes)
    }

    LazyColumn(contentPadding = PaddingValues(bottom = OdsTheme.spacings.medium)) {
        itemsIndexed(viewModel.appNews) { index, news ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = OdsTheme.spacings.medium)
                    .padding(top = OdsTheme.spacings.medium)
            ) {
                OdsText(modifier = Modifier.weight(1f), text = news.version, style = OdsTheme.typography.titleMedium)
                OdsText(text = news.date, style = OdsTheme.typography.bodySmall)
            }
            OdsText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = OdsTheme.spacings.medium)
                    .padding(top = OdsTheme.spacings.small),
                text = news.news,
                style = OdsTheme.typography.bodyLarge
            )
            if (index + 1 < viewModel.appNews.size) {
                OdsDivider(modifier = Modifier.padding(top = OdsTheme.spacings.medium))
            }
        }
    }

}