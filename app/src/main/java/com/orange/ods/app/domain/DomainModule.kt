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

package com.orange.ods.app.domain

import android.content.Context
import com.orange.ods.app.domain.datastore.DataStoreService
import com.orange.ods.app.domain.datastore.DataStoreServiceImpl
import com.orange.ods.app.domain.recipes.RecipesRepository
import com.orange.ods.app.domain.recipes.RecipesService
import com.orange.ods.app.domain.recipes.RecipesServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Singleton
    @Provides
    fun provideDataStoreService(@ApplicationContext context: Context): DataStoreService = DataStoreServiceImpl(context)

    @Singleton
    @Provides
    fun provideRecipesService(recipesRepository: RecipesRepository): RecipesService = RecipesServiceImpl(recipesRepository)
}
