package net.erickveil.mvi_table_roller.ui.viewmodel

import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import net.erickveil.mvi_table_roller.data.model.LootTable
import net.erickveil.mvi_table_roller.data.repository.LootRepository
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class LootTableViewModelTest {

    private lateinit var viewModel: LootTableViewModel
    private lateinit var repository: LootRepository


    @Before
    fun setup() {
        MockKAnnotations.init(this)
        repository = mockk(relaxed = true)

        // Mock repository response if needed
        coEvery {
            repository.getLootTable()
        } returns LootTable("Test Table", "Description", listOf("Gold", "Sword"))
        
    }

}