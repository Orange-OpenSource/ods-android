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
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.size.Size
import coil.size.Scale
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.orange.ods.compose.component.listitem.OdsListItem
import com.orange.ods.compose.component.progressindicator.OdsCircularProgressIndicator
import com.orange.ods.compose.extension.orElse
import com.orange.ods.compose.text.OdsText
import com.orange.ods.module.moreapps.R
import com.orange.ods.module.moreapps.domain.Density
import com.orange.ods.module.moreapps.ui.configuration.OdsMoreAppsConfiguration
import com.orange.ods.theme.typography.OdsTextStyle

@Composable
internal fun OdsMoreAppsScreen(configuration: OdsMoreAppsConfiguration, viewModel: OdsMoreAppsViewModel = viewModel(LocalContext.current as ViewModelStoreOwner)) {

    LaunchedEffect(key1 = Unit) {
        viewModel.configuration = configuration
        viewModel.getAppsSections()
    }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    when (uiState) {
        is OdsMoreAppsUiState.Success -> {
            val appsSections = (uiState as OdsMoreAppsUiState.Success).appsSections
            LazyColumn(contentPadding = PaddingValues(bottom = dimensionResource(id = com.orange.ods.R.dimen.screen_vertical_margin))) {
                items(appsSections) { appsSection ->
                    OdsText(
                        modifier = Modifier
                            .padding(horizontal = dimensionResource(id = com.orange.ods.R.dimen.spacing_m))
                            .padding(top = dimensionResource(id = com.orange.ods.R.dimen.spacing_s)),
                        text = appsSection.name.orElse { stringResource(id = R.string.odsMoreApps_section_uncategorizedApps) },
                        style = OdsTextStyle.TitleM
                    )

                    Column {
                        appsSection.apps.filter { it.type == "app" }.forEach { app -> //TODO improve filter
                            OdsListItem(
                                text = app.name.orEmpty(),
                                secondaryText = app.description,
                                secondaryTextLineCount = OdsListItem.SecondaryTextLineCount.Two,
                                leadingIcon = getAppLeadingIcon(app.iconUrlByDensity)
                            )
                        }
                    }
                }
            }
        }
        is OdsMoreAppsUiState.Error -> {
            OdsText(text = (uiState as OdsMoreAppsUiState.Error).odsMoreAppsError.getMessage(), style = OdsTextStyle.BodyM)
        }
        is OdsMoreAppsUiState.Loading -> {
            OdsCircularProgressIndicator()
        }
    }
}

@Composable
fun getAppLeadingIcon(iconUrlByDensity: Map<Density, String>?): OdsListItem.LeadingIcon {
    val context = LocalContext.current
    val imageDensity = Density.fromDisplayMetrics(context.resources.displayMetrics)
    val painter = iconUrlByDensity?.let {
        val imageUrl = it[imageDensity]
        val imageSize = with(LocalDensity.current) {  dimensionResource(id = com.orange.ods.R.dimen.list_square_image_size).toPx() }
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