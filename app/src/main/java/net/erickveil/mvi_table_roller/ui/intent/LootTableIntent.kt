package net.erickveil.mvi_table_roller.ui.intent

sealed class LootTableIntent {
    /* RollLootTable is an object that extends LootTableIntent. It represents a specific action or
     * Intent in your application, namely, the action to "roll" the loot table. Because it's an
     * object, there's exactly one instance of RollLootTable in your application, making it a
     * singleton.
     */
    object RollLootTable : LootTableIntent()
}