/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
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
