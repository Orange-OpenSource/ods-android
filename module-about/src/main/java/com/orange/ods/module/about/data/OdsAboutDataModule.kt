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

package com.orange.ods.module.about.data

import android.content.Context
import com.orange.ods.module.about.data.appnews.AppNewsRepositoryImpl
import com.orange.ods.module.about.domain.appnews.AppNewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object OdsAboutDataModule {

    @Singleton
    @Provides
    fun provideAppNewsRepository(@ApplicationContext context: Context): AppNewsRepository = AppNewsRepositoryImpl(context)
}
