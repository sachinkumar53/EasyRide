package com.sachin.app.easyride.countrycodepicker.data

import android.content.Context
import com.sachin.app.easyride.countrycodepicker.model.Country
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import java.io.IOException

class CountryDataSource(private val context: Context) {

    private suspend fun parseCountyJson(): List<Country> = withContext(Dispatchers.IO) {
        val json = loadJsonStringFromAssets() ?: return@withContext emptyList()
        Json.decodeFromString<List<Country>>(string = json)
    }

    private suspend fun loadJsonStringFromAssets(): String? = withContext(Dispatchers.IO) {
        try {
            context.assets.open(COUNTRIES_JSON).bufferedReader().use { it.readText() }
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    companion object {
        private const val COUNTRIES_JSON = "countries.json"
    }
}