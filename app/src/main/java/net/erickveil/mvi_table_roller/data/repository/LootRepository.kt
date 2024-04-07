package net.erickveil.mvi_table_roller.data.repository

import android.content.Context
import android.util.Log
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import net.erickveil.mvi_table_roller.data.model.LootTable
import java.io.IOException

class LootRepository(private val context: Context) {
    fun getLootTable(): LootTable? {
        Log.d("@LOADING", "Starting json load")

        val jsonString = loadJsonFromAssets("lootTable.json")

        Log.d("@LOADING", "Starting json parse")

        return jsonString?.let { parseJsonToLootTable(it) }
    }

    private fun loadJsonFromAssets(fileName: String): String? {
        return try {
            context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            null
        }
    }

    private fun parseJsonToLootTable(jsonString: String): LootTable? {
        return try {
            Json.decodeFromString(serializer(), jsonString)
        } catch (exception: SerializationException) {
            exception.printStackTrace()
            null
        }
    }
}
