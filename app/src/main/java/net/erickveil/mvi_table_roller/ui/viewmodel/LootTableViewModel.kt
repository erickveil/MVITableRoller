package net.erickveil.mvi_table_roller.ui.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import net.erickveil.mvi_table_roller.ui.intent.LootTableIntent
import net.erickveil.mvi_table_roller.ui.viewstate.LootTableViewState

class LootTableViewModel: ViewModel() {

    // Here we set up the state machine
    private val _state = MutableStateFlow(LootTableViewState())
    val state: StateFlow<LootTableViewState> = _state.asStateFlow()

    fun processIntent(intent: LootTableIntent) {
        /* This `when` expression is checking the type of the intent object passed to the function.
         * The `is LootTableIntent.RollLootTable` tests whether the intent is an instance of the
         * RollLootTable object.
         * Because RollLootTable is declared as an object, this check is essentially seeing if the
         * intent represents the action to roll the loot table.
         */
        when (intent) {
            is LootTableIntent.RollLootTable -> rollLootTable()
        }
    }

    private fun rollLootTable() {
        // Simulate fetching data and updating the state
        _state.value = _state.value.copy(
            isLoading = true,
            // Simulate fetching a random result
            resultText = "Magic sword"
        )
        // Here, you would actually fetch the data from your model and update the state accordingly
    }
}