/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.theme.orange

import com.orange.ods.compose.theme.OdsColors
import com.orange.ods.compose.theme.OdsCustomTheme
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
object OrangeThemeModule {

    @Provides
    @IntoSet
    fun provideOrangeTheme(): OdsCustomTheme = OrangeTheme()

    @Provides
    fun provideOrangeColors(): OdsColors = orangeLightColors()

}