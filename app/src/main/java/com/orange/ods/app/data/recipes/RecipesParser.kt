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

import com.orange.ods.app.R
import com.orange.ods.app.domain.recipes.Category
import com.orange.ods.app.domain.recipes.Food
import com.orange.ods.app.domain.recipes.Ingredient
import com.orange.ods.app.domain.recipes.Recipe
import org.json.JSONException
import org.json.JSONObject

class RecipesParser {

    companion object {

        private const val Recipes = "recipes"
        private const val Title = "title"
        private const val Subtitle = "subtitle"
        private const val Description = "description"
        private const val Url = "url"
        private const val IconName = "iconName"
        private const val Ingredients = "ingredients"
        private const val FoodId = "foodId"
        private const val Quantity = "quantity"
        private const val Cafe = "Cafe"
        private const val CookingPot = "CookingPot"
        private const val IceCream = "IceCream"
        private const val Restaurant = "Restaurant"
        private const val Medicine = "medicine"
        private const val OrangeDetente = "OrangeDetente"
        private const val FoodAndEntertainment = "Food_and_Entertainment"
        private const val Foods = "foods"
        private const val Id = "id"
        private const val Name = "name"
        private const val Image = "image"
        private const val CategoryId = "catId"
        private const val Category = "category"
    }

    @Throws(JSONException::class, NoSuchElementException::class)
    fun parseRecipes(jsonString: String): List<Recipe> {
        val jsonObject = JSONObject(jsonString)
        val jsonCategories = jsonObject.getJSONArray(Category)
        val categories = List(jsonCategories.length()) { parseCategory(jsonCategories.getJSONObject(it)) }
        val jsonFoods = jsonObject.getJSONArray(Foods)
        val foods = List(jsonFoods.length()) { parseFood(jsonFoods.getJSONObject(it)) }
        val jsonRecipes = jsonObject.getJSONArray(Recipes)

        return List(jsonRecipes.length()) { parseRecipe(jsonRecipes.getJSONObject(it), foods, categories) }
    }

    @Throws(JSONException::class)
    fun parseFood(jsonFood: JSONObject): Food {
        val id = jsonFood.getInt(Id)
        val name = jsonFood.getString(Name)
        val image = jsonFood.getString(Image)

        return Food(id, name, image)
    }

    @Throws(JSONException::class)
    fun parseCategory(jsonCategory: JSONObject): Category {
        val id = jsonCategory.getInt(Id)
        val name = jsonCategory.getString(Name)
        val iconName = jsonCategory.getString(IconName)
        val iconResId = getIconResId(iconName)

        return Category(id, name, iconResId)
    }

    @Throws(JSONException::class, NoSuchElementException::class)
    private fun parseRecipe(jsonRecipe: JSONObject, foods: List<Food>, categories: List<Category>): Recipe {
        val title = jsonRecipe.getString(Title)
        val subtitle = jsonRecipe.getString(Subtitle)
        val categoryId = jsonRecipe.getInt(CategoryId)
        val category = categories.first { it.id == categoryId }
        val description = jsonRecipe.getString(Description)
        val url = jsonRecipe.getString(Url)
        val iconName = jsonRecipe.getString(IconName)
        val iconResId = getIconResId(iconName)
        val jsonIngredients = jsonRecipe.getJSONArray(Ingredients)
        val ingredients = List(jsonIngredients.length()) { parseIngredient(jsonIngredients.getJSONObject(it), foods) }

        return Recipe(title, category, subtitle, ingredients, description, url, iconResId)
    }

    @Throws(JSONException::class, NoSuchElementException::class)
    private fun parseIngredient(jsonIngredient: JSONObject, foods: List<Food>): Ingredient {
        val foodId = jsonIngredient.getInt(FoodId)
        val food = foods.first { it.id == foodId }
        val quantity = jsonIngredient.getString(Quantity)

        return Ingredient(food, quantity)
    }

    private fun getIconResId(iconName: String) = when (iconName) {
        Cafe -> R.drawable.ic_coffee
        CookingPot -> R.drawable.ic_cooking_pot
        IceCream -> R.drawable.ic_ice_cream
        Restaurant -> R.drawable.ic_restaurant
        OrangeDetente -> R.drawable.ic_orange_detente
        Medicine -> R.drawable.ic_medicine
        FoodAndEntertainment -> R.drawable.ic_food_and_entertainment
        else -> null
    }
}
