/*
 * Software Name: Orange Design System
 * SPDX-FileCopyrightText: Copyright (c) Orange SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT licence,
 * the text of which is available at https://opensource.org/license/MIT/
 * or see the "LICENSE" file for more details.
 *
 * Software description: Android library of reusable graphical components 
 */

package com.orange.ods.module.about.data.appnews

import com.orange.ods.module.about.domain.appnews.AppNews
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

internal class AppNewsParser {

    companion object {
        private const val Version = "version"
        private const val Date = "date"
        private const val News = "news"
    }

    @Throws(JSONException::class, NoSuchElementException::class)
    fun parseAppNews(jsonString: String): List<AppNews> {
        val jsonAppNews = JSONArray(jsonString)

        return List(jsonAppNews.length()) { parseAppNews(jsonAppNews.getJSONObject(it)) }
    }

    @Throws(JSONException::class, NoSuchElementException::class)
    private fun parseAppNews(jsonAppNews: JSONObject): AppNews {
        val version = jsonAppNews.getString(Version)
        val date = jsonAppNews.getString(Date)
        val news = jsonAppNews.getString(News)

        return AppNews(version, date, news)
    }
}