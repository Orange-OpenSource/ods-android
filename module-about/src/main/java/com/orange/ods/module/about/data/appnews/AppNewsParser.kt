/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
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