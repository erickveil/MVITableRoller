package net.erickveil.mvi_table_roller.data.model
import kotlinx.serialization.Serializable

@Serializable
data class LootTable (
    val tableName: String,
    val description: String,
    val results: List<String>
)