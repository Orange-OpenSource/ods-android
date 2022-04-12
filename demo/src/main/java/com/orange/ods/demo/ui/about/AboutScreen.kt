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
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.orange.ods.compose.component.lists.OdsListItem
import com.orange.ods.compose.theme.Blue200
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.AboutNavigationItem
import com.orange.ods.demo.ui.utilities.extension.orElse
import com.orange.ods.demo.ui.utilities.versionCode

private data class AboutEntry(@StringRes val titleRes: Int, val fileName: String)

private val aboutEntries = listOf(
    AboutEntry(R.string.about_menu_legal_notice, "about_legal_notice.html"),
    AboutEntry(R.string.about_menu_privacy_policy, "about_privacy_policy.html")
)

@Composable
@ExperimentalMaterialApi
fun AboutScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        val context = LocalContext.current
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .background(Blue200), // TODO remove this background color when we get the full width image from the design team
            painter = painterResource(id = R.drawable.il_about),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        Column(Modifier.padding(horizontal = 16.dp)) {
            Text(text = stringResource(id = R.string.about_app_name), style = MaterialTheme.typography.h4, modifier = Modifier.padding(top = 33.dp))
            Text(text = getVersion(context), style = MaterialTheme.typography.caption, modifier = Modifier.padding(top = 3.dp))
            Text(text = stringResource(id = R.string.about_copyright), style = MaterialTheme.typography.caption)
        }

        Spacer(modifier = Modifier.height(16.dp))

        for (aboutEntry in aboutEntries) {
            OdsListItem(text = stringResource(id = aboutEntry.titleRes), modifier = Modifier.clickable {
                navController.navigate(AboutNavigationItem.AboutEntryContent.route.plus("/${aboutEntry.titleRes}/${aboutEntry.fileName}"))
            })
        }
    }
}

private fun getVersion(context: Context): String {
    val packageInfo = context.packageName?.let { packageName ->
        context.packageManager?.getPackageInfo(packageName, 0)
    }
    return packageInfo?.let {
        String.format(context.resources.getString(R.string.about_app_version), packageInfo.versionName, packageInfo.versionCode())
    }.orElse {
        context.resources.getString(R.string.about_app_version)
    }
}