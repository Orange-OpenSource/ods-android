/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.domain

import android.content.Context
import com.orange.ods.demo.domain.datastore.DataStoreService
import com.orange.ods.demo.domain.datastore.DataStoreServiceImpl
import com.orange.ods.demo.domain.recipes.RecipesRepository
import com.orange.ods.demo.domain.recipes.RecipesService
import com.orange.ods.demo.domain.recipes.RecipesServiceImpl
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
