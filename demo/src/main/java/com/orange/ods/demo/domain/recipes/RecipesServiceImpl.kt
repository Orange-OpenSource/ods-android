/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.domain.recipes

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RecipesServiceImpl @Inject constructor(private val recipesRepository: RecipesRepository) : RecipesService {

    override fun getRecipes(): Flow<List<Recipe>> {
        return recipesRepository.getRecipes()
    }
}
