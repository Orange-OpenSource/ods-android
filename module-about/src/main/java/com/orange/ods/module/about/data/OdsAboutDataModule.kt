/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
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
object OdsAboutDataModule {

    @Singleton
    @Provides
    fun provideAppNewsRepository(@ApplicationContext context: Context): AppNewsRepository = AppNewsRepositoryImpl(context)
}
