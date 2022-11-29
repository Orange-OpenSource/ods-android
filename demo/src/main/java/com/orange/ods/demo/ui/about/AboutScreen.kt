/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.about

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import com.orange.ods.compose.component.list.OdsListItem
import com.orange.ods.compose.text.OdsTextCaption
import com.orange.ods.compose.text.OdsTextH4
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.LocalMainTopAppBarManager
import com.orange.ods.demo.ui.utilities.compat.PackageManagerCompat
import com.orange.ods.demo.ui.utilities.extension.versionCode
import com.orange.ods.utilities.extension.ifNotNull
import com.orange.ods.utilities.extension.orElse

@Composable
fun AboutScreen(onAboutItemClick: (Long) -> Unit) {
    LocalMainTopAppBarManager.current.updateTopAppBarTitle(R.string.navigation_item_about)

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(bottom = dimensionResource(id = R.dimen.screen_vertical_margin))
    ) {
        val context = LocalContext.current
        Image(
            modifier = Modifier
                .fillMaxWidth(),
            painter = painterResource(id = R.drawable.il_about),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        Column(Modifier.padding(horizontal = dimensionResource(id = R.dimen.screen_horizontal_margin))) {
            OdsTextH4(
                text = stringResource(id = R.string.about_app_name),
                modifier = Modifier.padding(top = dimensionResource(id = R.dimen.spacing_xl))
            )
            OdsTextCaption(
                text = getVersion(context),
                modifier = Modifier.padding(top = dimensionResource(id = R.dimen.spacing_xs))
            )
            OdsTextCaption(text = stringResource(id = R.string.about_copyright))
        }

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing_m)))

        for (aboutItem in aboutItems) {
            OdsListItem(text = stringResource(id = aboutItem.titleRes), modifier = Modifier.clickable {
                onAboutItemClick(aboutItem.id)
            })
        }
    }
}

private fun getVersion(context: Context): String {
    val packageInfo = ifNotNull(context.packageManager, context.packageName) { packageManager, packageName ->
        PackageManagerCompat.getPackageInfo(packageManager, packageName, 0)
    }
    return packageInfo?.let {
        String.format(context.resources.getString(R.string.about_app_version), packageInfo.versionName, packageInfo.versionCode())
    }.orElse {
        context.resources.getString(R.string.about_app_version)
    }
}