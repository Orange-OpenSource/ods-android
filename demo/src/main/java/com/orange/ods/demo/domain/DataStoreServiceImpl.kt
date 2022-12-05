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
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "datastore")

class DataStoreServiceImpl @Inject constructor(private val context: Context) : DataStoreService {

    override suspend fun putString(key: String, value: String) {
        val preferenceKey = stringPreferencesKey(key)
        context.dataStore.edit { preferences ->
            preferences[preferenceKey] = value
        }
    }

    override suspend fun getString(key: String): String? {
        val preferenceKey = stringPreferencesKey(key)
        return context.dataStore.data.firstOrNull()?.let { preferences ->
            preferences[preferenceKey]
        }
    }

}