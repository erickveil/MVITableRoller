package net.erickveil.mvi_table_roller.data.model

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.Test


class LootTableTest {
    @Test
    fun serializeAndDeserialize() {
        val lootTable = LootTable (
            tableName = "Test Table",
            description = "A test loot table.",
            results = listOf("Gold", "Sword", "Potion")
        )

        // Serialize
        val jsonString = Json.encodeToString(lootTable)
        assertNotNull(jsonString)

        // Deserialize
        val deserialized = Json.decodeFromString<LootTable>(jsonString)
        assertEquals(lootTable, deserialized)
    }
}
