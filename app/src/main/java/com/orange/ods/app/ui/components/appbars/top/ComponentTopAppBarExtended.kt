/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.ui.components.appbars.top

import androidx.compose.runtime.Composable
import com.orange.ods.app.domain.recipes.LocalRecipes
import com.orange.ods.compose.component.list.OdsListItem


@Composable
fun ComponentTopAppBarExtended() {
    val recipes = LocalRecipes.current

    recipes.forEach { recipe ->
        OdsListItem(text = recipe.title)
    }
}