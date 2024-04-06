package net.erickveil.mvi_table_roller.ui.viewstate

data class LootTableViewState(
    val isLoading: Boolean = false,
    val resultText: String = "Result goes here.",
    val error: String? = null
)
