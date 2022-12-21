/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.data.recipes

import android.content.Context
import com.orange.ods.demo.R
import com.orange.ods.demo.domain.recipes.Recipe
import com.orange.ods.demo.domain.recipes.RecipesRepository
import com.orange.ods.demo.ui.utilities.extension.contentAsString
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RecipesRepositoryImpl @Inject constructor(private val context: Context) : RecipesRepository {

    private val foodParser = RecipesParser()

    override fun getRecipes(): Flow<List<Recipe>> {
        return flow {
            try {
                val jsonString = context.resources.openRawResource(R.raw.recipes).contentAsString().orEmpty()
                val recipes = foodParser.parseRecipes(jsonString)
                emit(recipes)
            } catch (_: Exception) {
            }
        }
    }
}
