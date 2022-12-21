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

import androidx.annotation.DrawableRes
import androidx.compose.runtime.staticCompositionLocalOf

val LocalRecipes = staticCompositionLocalOf<List<Recipe>> { error("CompositionLocal LocalRecipes not present") }

data class Recipe(
    val title: String,
    val subtitle: String,
    val ingredients: List<Ingredient>,
    val description: String,
    val imageUrl: String,
    @DrawableRes val iconResId: Int?
)

data class Ingredient(
    val quantity: String,
    val name: String,
    val imageUrl: String
)
