/*
 * Software Name: Orange Design System
 * SPDX-FileCopyrightText: Copyright (c) Orange SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT licence,
 * the text of which is available at https://opensource.org/license/MIT/
 * or see the "LICENSE" file for more details.
 *
 * Software description: Android library of reusable graphical components
 */

package com.orange.ods.app.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orange.ods.app.domain.datastore.DataStoreService
import com.orange.ods.app.domain.recipes.Category
import com.orange.ods.app.domain.recipes.Recipe
import com.orange.ods.app.domain.recipes.RecipesService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val dataStoreService: DataStoreService, private val recipesService: RecipesService) : ViewModel() {

    companion object {
        private const val UserThemeNameKey = "userThemeName"
    }

    var recipes = emptyList<Recipe>()
        private set

    var categories = emptyList<Category>()
        private set

    init {
        viewModelScope.launch {
            recipes = recipesService.getRecipes().firstOrNull().orEmpty()
            categories = recipes.map { it.category }.distinct().sortedBy { it.id }
        }
    }

    fun storeUserThemeName(themeName: String) = runBlocking {
        dataStoreService.putString(UserThemeNameKey, themeName)
    }

    fun getUserThemeName(): String? = runBlocking { dataStoreService.getString(UserThemeNameKey) }
}