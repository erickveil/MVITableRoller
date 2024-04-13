package net.erickveil.mvi_table_roller.ui.viewmodel

import android.app.Application
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import net.erickveil.mvi_table_roller.ui.intent.LootTableIntent
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class LootTableViewModelTest {

    @Mock
    private lateinit var mockApplication: Application

    private lateinit var lootTableViewModel:LootTableViewModel

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {

        Dispatchers.setMain(testDispatcher)

        lootTableViewModel = LootTableViewModel(mockApplication)

    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    /*
    @Test
    fun loadLootTable_updatesLootTable() = runTest {

        lootTableViewModel.loadLootTable()


        advanceUntilIdle()

        val expected = LootTableViewState( isLoaded = true, resultText = "Result goes here." )
        assertEquals(expected, lootTableViewModel.state.value)
    }
     */

    @Test
    fun processIntent_rollLootTable() = runTest {

        // run out the init method which calls a coroutine
        advanceUntilIdle()

        lootTableViewModel.processIntent(LootTableIntent.RollLootTable)
        val expected = "No loot found"
        assertEquals(expected, lootTableViewModel.state.value.resultText)

    }
}