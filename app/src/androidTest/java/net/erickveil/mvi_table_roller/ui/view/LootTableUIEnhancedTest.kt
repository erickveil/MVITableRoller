package net.erickveil.mvi_table_roller.ui.view

import android.app.Application
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import net.erickveil.mvi_table_roller.ui.viewmodel.LootTableViewModel
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
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
    private lateinit var mockApp:Application

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
            LootTableUIEnhanced(
                LootTableViewModel( mockApp )
            )
        }
        composeTestRule.onNodeWithText("Roll on Loot Table").assertExists()
    }

    @Test
    fun isOutputBoxDisplayed() = runTest {
        composeTestRule.setContent {
            LootTableUIEnhanced(viewModel = LootTableViewModel(mockApp))
        }
        composeTestRule.onNodeWithContentDescription(ComposableDescription.OUTPUT_BOX)
            .assertIsDisplayed()
    }

    @Test
    fun testButtonClick() = runTest {
        val mockViewModel = LootTableViewModel(mockApp)
        composeTestRule.setContent {
            LootTableUIEnhanced(mockViewModel)
        }

        val initialText = "Result goes here."

        // Check the state's starting text
        assertEquals(initialText, mockViewModel.state.value.resultText)

        // Check the view's starting text
        composeTestRule.onNodeWithContentDescription(ComposableDescription.OUTPUT_TEXT)
            .assertTextEquals(initialText)

        // Click the button
        composeTestRule.onNodeWithContentDescription(ComposableDescription.ROLL_BUTTON)
            .performClick()

        // Make sure that the state's text has changed
        assertNotEquals(initialText, mockViewModel.state.value.resultText)

        // Test that the displayed text matches the state text
        // This sounds like "DUH!" but I did find an error while writing this text:
        // My OUTPUT_BOX description was on the wrong Text object!
        composeTestRule.onNodeWithContentDescription(ComposableDescription.OUTPUT_TEXT)
            .assertTextEquals(mockViewModel.state.value.resultText)

    }

}