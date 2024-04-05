package net.erickveil.mvi_table_roller.ui.intent

sealed class LootTableIntent {
    object RollLootTable : LootTableIntent()
}