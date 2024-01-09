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

package com.orange.ods.app.domain.recipes

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RecipesServiceImpl @Inject constructor(private val recipesRepository: RecipesRepository) : RecipesService {

    override fun getRecipes(): Flow<List<Recipe>> {
        return recipesRepository.getRecipes()
    }
}
