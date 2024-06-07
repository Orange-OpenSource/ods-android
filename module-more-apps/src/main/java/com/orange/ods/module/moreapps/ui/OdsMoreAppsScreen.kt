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

import android.content.res.Configuration
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
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
import com.orange.ods.compose.component.utilities.BasicPreviewParameterProvider
import com.orange.ods.compose.component.utilities.OdsPreview
import com.orange.ods.compose.extension.orElse
import com.orange.ods.compose.module.emptystate.OdsEmptyStateView
import com.orange.ods.compose.text.OdsText
import com.orange.ods.module.moreapps.R
import com.orange.ods.module.moreapps.domain.App
import com.orange.ods.module.moreapps.domain.AppsList
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
        viewModel.getMoreAppsItems()
    }


    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    OdsMoreAppsScreen(uiState = uiState)
}

@Composable
private fun OdsMoreAppsScreen(uiState: OdsMoreAppsUiState) {
    when (uiState) {
        is OdsMoreAppsUiState.Success -> {
            val moreAppsItems = uiState.moreAppsItems
            LazyColumn(contentPadding = PaddingValues(bottom = dimensionResource(id = com.orange.ods.R.dimen.screen_vertical_margin))) {
                items(moreAppsItems) { item ->
                    MoreAppsItem(item = item, firstItem = item == moreAppsItems.firstOrNull())
                }
            }
        }
        is OdsMoreAppsUiState.Error -> {
            OdsEmptyStateView(
                title = stringResource(id = R.string.odsMoreApps_error),
                text = uiState.moreAppsError.getMessage(),
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
private fun MoreAppsItem(item: MoreAppsItem, firstItem: Boolean = false) {
    when (item) {
        is AppsList -> item.name?.let { MoreAppsSection(it, item.items, firstItem) }.orElse { MoreAppsSimpleList(item.items) }
        is App -> MoreAppsApp(item)
    }
}


@Composable
private fun MoreAppsSection(name: String?, items: List<MoreAppsItem>, firstItem: Boolean) {
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
    MoreAppsSimpleList(items = items)
}

@Composable
private fun MoreAppsSimpleList(items: List<MoreAppsItem>) {
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
        text = app.name,
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
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
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


@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun PreviewOdsMoreAppsScreen(@PreviewParameter(OdsMoreAppsScreenPreviewParameterProvider::class) uiState: OdsMoreAppsUiState) {
    OdsPreview {
        OdsMoreAppsScreen(uiState = uiState)
    }
}

private class OdsMoreAppsScreenPreviewParameterProvider :
    BasicPreviewParameterProvider<OdsMoreAppsUiState>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<OdsMoreAppsUiState>
    get() {
        val moreAppsItems = listOf(
            AppsList(
                name = "Section 1 without divider",
                items = listOf(
                    App("Toto App", "This application is the first app of the section 1", null, null),
                )
            ),
            AppsList(
                name = null,
                items = listOf(
                    App("Titi App", "First app of the simple app list", null, null),
                    App("VeryVeryVeryVeryVeryVeryVeryVerylongName App", "Second app of the simple app list with a very long description.", null, null),
                )
            ),
            AppsList(
                name = "Section 2 with divider",
                items = listOf(
                    App("Tutu App", "This application is the first app of the section 2", null, null),
                    App("Tata App", "This application 4 is the second app of the section 2 with a very big description that should be truncated.", null, null),
                )
            )
        )

        return listOf(
            OdsMoreAppsUiState.Success(moreAppsItems = moreAppsItems),
            OdsMoreAppsUiState.Error(MoreAppsError.MissingConfiguration),
            OdsMoreAppsUiState.Error(MoreAppsError.RequestFailure("OMA Apps Plus backend not available")),
            OdsMoreAppsUiState.Loading,
        )
    }