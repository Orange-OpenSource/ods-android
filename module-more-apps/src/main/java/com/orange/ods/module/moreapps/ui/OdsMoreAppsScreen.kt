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

package com.orange.ods.module.moreapps.ui

import android.content.Context
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import coil.size.Size
import com.orange.ods.compose.component.divider.OdsDivider
import com.orange.ods.compose.component.listitem.OdsListItem
import com.orange.ods.compose.component.progressindicator.OdsCircularProgressIndicator
import com.orange.ods.compose.extension.orElse
import com.orange.ods.compose.module.emptystate.OdsEmptyStateView
import com.orange.ods.compose.text.OdsText
import com.orange.ods.module.moreapps.R
import com.orange.ods.module.moreapps.domain.App
import com.orange.ods.module.moreapps.domain.AppsList
import com.orange.ods.module.moreapps.domain.AppsSection
import com.orange.ods.module.moreapps.domain.Density
import com.orange.ods.module.moreapps.domain.MoreAppsItem
import com.orange.ods.module.moreapps.ui.configuration.OdsMoreAppsConfiguration
import com.orange.ods.module.moreapps.ui.extension.launchUrl
import com.orange.ods.theme.typography.OdsTextStyle

@Composable
internal fun OdsMoreAppsScreen(
    configuration: OdsMoreAppsConfiguration,
    viewModel: OdsMoreAppsViewModel = viewModel(LocalContext.current as ViewModelStoreOwner)
) {

    LaunchedEffect(key1 = Unit) {
        viewModel.configuration = configuration
        viewModel.getAppsSections()
    }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    when (uiState) {
        is OdsMoreAppsUiState.Success -> {
            val moreAppsItems = (uiState as OdsMoreAppsUiState.Success).moreAppsItems
            LazyColumn(contentPadding = PaddingValues(bottom = dimensionResource(id = com.orange.ods.R.dimen.screen_vertical_margin))) {
                items(moreAppsItems) { item ->
                    MoreAppsItem(item = item, firstItem = true)
                }
            }
        }
        is OdsMoreAppsUiState.Error -> {
            OdsEmptyStateView(
                title = stringResource(id = R.string.odsMoreApps_error),
                text = (uiState as OdsMoreAppsUiState.Error).moreAppsError.getMessage(),
                image = OdsEmptyStateView.Image(painter = painterResource(id = R.drawable.il_empty_state_error))
            )
        }
        is OdsMoreAppsUiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                OdsCircularProgressIndicator(label = stringResource(id = R.string.odsMoreApps_loading))
            }
        }
    }
}

@Composable
private fun MoreAppsItem(item: MoreAppsItem?, firstItem: Boolean = false) {
    when (item) {
        is AppsSection -> MoreAppsSection(item.name, item.items, firstItem)
        is AppsList -> MoreAppsList(item.items)
        is App -> MoreAppsApp(item)
    }
}


@Composable
private fun MoreAppsSection(name: String?, items: List<MoreAppsItem?>, firstItem: Boolean) {
    if (!firstItem) {
        OdsDivider(modifier = Modifier.padding(top = dimensionResource(id = com.orange.ods.R.dimen.spacing_s)))
    }
    OdsText(
        modifier = Modifier
            .padding(
                horizontal = dimensionResource(id = com.orange.ods.R.dimen.spacing_m),
                vertical = dimensionResource(id = com.orange.ods.R.dimen.spacing_s)
            ),
        text = name.orElse { stringResource(id = R.string.odsMoreApps_section_uncategorizedApps) },
        style = OdsTextStyle.TitleS
    )
    MoreAppsList(items = items)
}

@Composable
private fun MoreAppsList(items: List<MoreAppsItem?>) {
    if (items.isNotEmpty()) {
        Column {
            items.forEach { item ->
                MoreAppsItem(item = item)
            }
        }
    }
}

@Composable
private fun MoreAppsApp(app: App) {
    val context = LocalContext.current
    OdsListItem(
        text = app.name.orEmpty(),
        secondaryText = app.description.orEmpty(),
        secondaryTextLineCount = OdsListItem.SecondaryTextLineCount.Two,
        leadingIcon = getAppLeadingIcon(app.iconUrlByDensity),
        onClick = app.url?.let { url -> { context.launchUrl(url) } }
    )
}

@Composable
private fun getAppLeadingIcon(iconUrlByDensity: Map<Density, String>?): OdsListItem.LeadingIcon {
    val context = LocalContext.current
    val imageDensity = Density.fromDisplayMetrics(context.resources.displayMetrics)
    val painter = iconUrlByDensity?.let {
        val imageUrl = it[imageDensity]
        val imageSize = with(LocalDensity.current) { dimensionResource(id = com.orange.ods.R.dimen.list_square_image_size).toPx() }
        rememberAsyncImagePainter(
            model = ImageRequest.Builder(buildImageRequest(LocalContext.current, imageUrl))
                .scale(Scale.FILL)
                .size(Size(imageSize.toInt(), imageSize.toInt()))
                .build(),
            placeholder = painterResource(id = getPlaceholderSmallResId()),
            error = painterResource(id = getPlaceholderSmallResId(true))
        )
    }.orElse {
        painterResource(id = getPlaceholderSmallResId())
    }

    return OdsListItem.LeadingIcon(OdsListItem.LeadingIcon.Type.SquareImage, painter, "")
}

private fun buildImageRequest(context: Context, data: Any?): ImageRequest {
    return ImageRequest.Builder(context)
        .data(data)
        .build()
}

@Composable
private fun getPlaceholderSmallResId(error: Boolean = false): Int { //TODO Duplicate code from app
    val darkTheme = isSystemInDarkTheme()
    return when {
        !darkTheme && !error -> com.orange.ods.R.drawable.placeholder_small
        !darkTheme && error -> com.orange.ods.R.drawable.placeholder_error_small
        darkTheme && !error -> com.orange.ods.R.drawable.placeholder_small_dark
        else -> com.orange.ods.R.drawable.placeholder_error_small_dark
    }
}