/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.theme.innovationcup

import com.orange.ods.theme.OdsThemeSettings
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet


@Module
@InstallIn(SingletonComponent::class)
object InnovationCupThemeModule {

    @Provides
    @IntoSet
    fun provideInnovationCupThemeSettings(): OdsThemeSettings = InnovationCupThemeSettings()

}
