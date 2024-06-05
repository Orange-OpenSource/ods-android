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

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orange.ods.compose.extension.orElse
import com.orange.ods.module.moreapps.R
import com.orange.ods.module.moreapps.domain.MoreAppsItem
import com.orange.ods.module.moreapps.domain.MoreAppsService
import com.orange.ods.module.moreapps.ui.configuration.OdsMoreAppsConfiguration
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class OdsMoreAppsViewModel @Inject constructor(private val moreAppsService: MoreAppsService) : ViewModel() {
    var configuration: OdsMoreAppsConfiguration? by mutableStateOf(null)

    private val _uiState = MutableStateFlow<OdsMoreAppsUiState>(OdsMoreAppsUiState.Success(emptyList()))
    val uiState: StateFlow<OdsMoreAppsUiState> = _uiState

    fun getAppsSections() {
        configuration?.let { configuration ->
            _uiState.value = OdsMoreAppsUiState.Loading
            viewModelScope.launch {
                moreAppsService.getMoreAppsItems(configuration.apiKey, configuration.locale, configuration.filter).collect { result ->
                    _uiState.value = when {
                        result.isSuccess -> OdsMoreAppsUiState.Success(result.getOrDefault(emptyList()))
                        else -> OdsMoreAppsUiState.Error(MoreAppsError.RequestFailure(result.exceptionOrNull()?.message.orElse { result.exceptionOrNull()?.stackTrace.toString() }))
                    }
                }
            }
        }.orElse {
            _uiState.value = OdsMoreAppsUiState.Error(MoreAppsError.MissingConfiguration)
        }
    }

}

internal sealed class OdsMoreAppsUiState {
    data object Loading : OdsMoreAppsUiState()
    data class Success(val moreAppsItems: List<MoreAppsItem?>) : OdsMoreAppsUiState()
    data class Error(val moreAppsError: MoreAppsError) : OdsMoreAppsUiState()
}

internal sealed class MoreAppsError {
    data object MissingConfiguration : MoreAppsError()
    data class RequestFailure(val message: String) : MoreAppsError()

    @Composable
    fun getMessage() = when (this) {
        MissingConfiguration -> stringResource(id = R.string.odsMoreApps_error_missingConfiguration)
        is RequestFailure -> this.message
    }
}