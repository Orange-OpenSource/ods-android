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

package com.orange.ods.module.about.domain

import com.orange.ods.module.about.domain.appnews.AppNewsRepository
import com.orange.ods.module.about.domain.appnews.AppNewsService
import com.orange.ods.module.about.domain.appnews.AppNewsServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object OdsAboutDomainModule {

    @Singleton
    @Provides
    fun provideAppNewsService(appNewsRepository: AppNewsRepository): AppNewsService = AppNewsServiceImpl(appNewsRepository)
}
