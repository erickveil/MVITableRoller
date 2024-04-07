package net.erickveil.mvi_table_roller.ui.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import net.erickveil.mvi_table_roller.data.model.LootTable
import net.erickveil.mvi_table_roller.data.repository.LootRepository
import net.erickveil.mvi_table_roller.ui.intent.LootTableIntent
import net.erickveil.mvi_table_roller.ui.viewstate.LootTableViewState
import javax.inject.Inject

@HiltViewModel
class LootTableViewModel @Inject constructor(
    @ApplicationContext private val context: Context)
    : ViewModel() {

    // Here we set up the state machine
    private val _state = MutableStateFlow(LootTableViewState())
    val state: StateFlow<LootTableViewState> = _state.asStateFlow()

    private val repository = LootRepository(context)
    private var lootTable: LootTable? = null

    init {
        loadLootTable()
    }

    private fun loadLootTable() {
        // This block puts the file loading in a coroutine so that it is an asynchronous operation.
        viewModelScope.launch {
            lootTable = repository.getLootTable()
            // If loading the data is a potentially lengthy process, we would want to update the
            // state here to signal that data is ready to be accessed.
            // We can use the `LootTable.isLoading` member to track this.
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
            isLoading = false,
            resultText = randomItem ?: "No loot found"
        )
    }

    private fun getRandomLootItem(): String? {
        return lootTable?.results?.takeIf { it.isNotEmpty() }?.random()
    }
}