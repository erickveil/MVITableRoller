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
import net.erickveil.mvi_table_roller.data.model.LootTable
import net.erickveil.mvi_table_roller.data.repository.LootRepository
import net.erickveil.mvi_table_roller.ui.intent.LootTableIntent
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class LootTableViewModelTest {

    @Mock
    private lateinit var mockApplication: Application

    @Mock
    private lateinit var mockRepository: LootRepository

    private lateinit var lootTableViewModel:LootTableViewModel

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {

        Dispatchers.setMain(testDispatcher)
        /*
        We want to set up our ViewModel to:
        1. Not access a file for its ViewTable
        2. Use a mockApplication (which gets used for the context for a table load
         */

        `when`(mockRepository.getLootTable()).thenReturn(
            LootTable("Test Table", "Description", listOf("Item1"))
        )
        lootTableViewModel = LootTableViewModel(mockApplication, mockRepository)

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
        val expected = "Item1"
        assertEquals(expected, lootTableViewModel.state.value.resultText)

    }
}