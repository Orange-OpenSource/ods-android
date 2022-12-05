/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui

import androidx.lifecycle.ViewModel
import com.orange.ods.demo.domain.DataStoreService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val dataStoreService: DataStoreService) : ViewModel() {

    companion object {
        private const val UserThemeNameKey = "userThemeName"
    }

    fun storeUserThemeName(themeName: String) = runBlocking {
        dataStoreService.putString(UserThemeNameKey, themeName)
    }

    fun getUserThemeName(): String? = runBlocking { dataStoreService.getString(UserThemeNameKey) }
}