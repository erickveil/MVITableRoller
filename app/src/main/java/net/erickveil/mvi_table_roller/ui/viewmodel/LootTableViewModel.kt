package net.erickveil.mvi_table_roller.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import net.erickveil.mvi_table_roller.data.model.LootTable
import net.erickveil.mvi_table_roller.data.repository.LootRepository
import net.erickveil.mvi_table_roller.ui.intent.LootTableIntent
import net.erickveil.mvi_table_roller.ui.viewstate.LootTableViewState

class LootTableViewModel ( application: Application,
    private val repository: LootRepository = LootRepository(application.applicationContext)
)
    : AndroidViewModel(application) {

    // Here we set up the state machine
    private val _state = MutableStateFlow(LootTableViewState())
    val state: StateFlow<LootTableViewState> = _state.asStateFlow()

    //private var repository = LootRepository(application.applicationContext)

    private var lootTable: LootTable? = null

    init {
        loadLootTable()
    }

    private fun loadLootTable() {

        // This block puts the file loading in a coroutine so that it is an asynchronous operation.
        viewModelScope.launch {
            lootTable = repository.getLootTable()

            _state.value = _state.value.copy(
                isLoaded = true
            )
        }
    }

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
        val randomItem = getRandomLootItem()
        _state.value = _state.value.copy(
            resultText = randomItem ?: "No loot found"
        )
    }

    private fun getRandomLootItem(): String? {
        return lootTable?.results?.takeIf { it.isNotEmpty() }?.random()
    }
}