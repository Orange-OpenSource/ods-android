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

package com.orange.ods.app.data.recipes

import android.content.Context
import com.orange.ods.app.R
import com.orange.ods.app.domain.recipes.Recipe
import com.orange.ods.app.domain.recipes.RecipesRepository
import com.orange.ods.extension.contentAsString
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

class RecipesRepositoryImpl @Inject constructor(private val context: Context) : RecipesRepository {

    private val recipesParser = RecipesParser()

    override fun getRecipes(): Flow<List<Recipe>> {
        return flow {
            try {
                val jsonString = context.resources.openRawResource(R.raw.recipes).contentAsString().orEmpty()
                val recipes = recipesParser.parseRecipes(jsonString)
                emit(recipes)
            } catch (_: Exception) {
                Timber.d("Fail to parse JSON file")
            }
        }
    }
}
