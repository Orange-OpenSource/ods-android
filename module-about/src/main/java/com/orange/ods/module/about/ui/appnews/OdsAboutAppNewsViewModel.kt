/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.module.about.ui.appnews

import androidx.annotation.RawRes
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orange.ods.module.about.domain.appnews.AppNews
import com.orange.ods.module.about.domain.appnews.AppNewsService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class OdsAboutAppNewsViewModel @Inject constructor(private val appNewsService: AppNewsService) : ViewModel() {

    private val _appNews = mutableStateListOf<AppNews>()
    val appNews: List<AppNews>
        get() = _appNews
    
    fun getAppNews(@RawRes fileRes: Int) {
        viewModelScope.launch {
            _appNews.clear()
            _appNews.addAll(appNewsService.getAppNews(fileRes).firstOrNull().orEmpty())
        }
    }
}