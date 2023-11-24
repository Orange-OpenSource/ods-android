/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.module.about.appnews

import androidx.annotation.RawRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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

    LazyColumn {
        items(viewModel.appNews) { news ->
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
            OdsDivider(modifier = Modifier.padding(top = dimensionResource(id = com.orange.ods.R.dimen.spacing_m)))
        }
    }

}