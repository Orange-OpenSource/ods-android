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

data class Recipe(
    val title: String,
    val subtitle: String,
    val ingredients: List<Ingredient>,
    val description: String,
    val url: String,
    val iconName: String
)

data class Ingredient(
    val quantity: String,
    val name: String,
    val image: String
)
