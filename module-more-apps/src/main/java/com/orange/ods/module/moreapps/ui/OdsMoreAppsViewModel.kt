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
import com.orange.ods.module.moreapps.domain.AppsSection
import com.orange.ods.module.moreapps.domain.MoreAppsService
import com.orange.ods.module.moreapps.domain.Resource
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
                moreAppsService.getAppsSections(configuration.apiKey, configuration.locale).collect { resource ->
                    _uiState.value = when (resource) {
                        is Resource.Success -> OdsMoreAppsUiState.Success(resource.value)
                        is Resource.Failure -> OdsMoreAppsUiState.Error(OdsMoreAppsError.RequestFailure(resource.throwable.message.orElse { resource.throwable.stackTrace.toString() }))
                    }
                }
            }
        }.orElse {
            _uiState.value = OdsMoreAppsUiState.Error(OdsMoreAppsError.MissingConfiguration)
        }
    }

}

internal sealed class OdsMoreAppsUiState {
    data object Loading: OdsMoreAppsUiState()
    data class Success(val appsSections: List<AppsSection>): OdsMoreAppsUiState()
    data class Error(val odsMoreAppsError: OdsMoreAppsError): OdsMoreAppsUiState()
}

internal sealed class OdsMoreAppsError {
    data object MissingConfiguration: OdsMoreAppsError()
    data class RequestFailure(val message: String): OdsMoreAppsError()

    @Composable
    fun getMessage(): String {
        val errorMessage = when(this) {
            MissingConfiguration -> stringResource(id = R.string.odsMoreApps_error_missingConfiguration)
            is RequestFailure -> this.message
        }
        return stringResource(id = R.string.odsMoreApps_error, errorMessage)
    }
}