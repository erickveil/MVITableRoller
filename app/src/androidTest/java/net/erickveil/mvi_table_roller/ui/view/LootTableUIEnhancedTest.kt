package net.erickveil.mvi_table_roller.ui.view

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import net.erickveil.mvi_table_roller.ui.viewmodel.LootTableViewModel
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class LootTableUIEnhancedTest {


    // Set up an environment to test in
    @get:Rule
    val composeTestRule = createComposeRule()

    @Mock
    private lateinit var mockViewModel:LootTableViewModel


    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun isButtonExists() = runTest {

        composeTestRule.setContent {
            LootTableUIEnhanced(mockViewModel)
        }
        composeTestRule.onNodeWithText("Roll on Loot Table").assertExists()
        //composeTestRule.onNodeWithText("Fail").assertExists()
    }

    // Test pressing button gives us a value in the output window

}