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

package com.orange.ods.app.domain.recipes

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.compose.runtime.staticCompositionLocalOf
import kotlinx.parcelize.Parcelize

val LocalRecipes = staticCompositionLocalOf<List<Recipe>> { error("CompositionLocal LocalRecipes not present") }
val LocalCategories = staticCompositionLocalOf<List<Category>> { error("CompositionLocal LocalCategories not present") }

@Parcelize
data class Recipe(
    val title: String,
    val category: Category,
    val subtitle: String,
    val ingredients: List<Ingredient>,
    val description: String,
    val imageUrl: String,
    @DrawableRes val iconResId: Int?
) : Parcelable

@Parcelize
data class Ingredient(
    val food: Food,
    val quantity: String
) : Parcelable

@Parcelize
data class Food(
    val id: Int,
    val name: String,
    val imageUrl: String
) : Parcelable

@Parcelize
data class Category(
    val id: Int,
    val name: String,
    @DrawableRes val iconResId: Int?
) : Parcelable