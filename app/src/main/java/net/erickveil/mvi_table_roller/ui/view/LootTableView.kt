package net.erickveil.mvi_table_roller.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import net.erickveil.mvi_table_roller.ui.intent.LootTableIntent
import net.erickveil.mvi_table_roller.ui.theme.buttonColor
import net.erickveil.mvi_table_roller.ui.theme.buttonTextColor
import net.erickveil.mvi_table_roller.ui.theme.columnSpacing
import net.erickveil.mvi_table_roller.ui.theme.controlPadding
import net.erickveil.mvi_table_roller.ui.theme.cornerRadius
import net.erickveil.mvi_table_roller.ui.theme.descriptoinBGColor
import net.erickveil.mvi_table_roller.ui.theme.internalPadding
import net.erickveil.mvi_table_roller.ui.theme.outputBoxMinHeight
import net.erickveil.mvi_table_roller.ui.theme.pageBGColor
import net.erickveil.mvi_table_roller.ui.theme.textFontSize
import net.erickveil.mvi_table_roller.ui.viewmodel.LootTableViewModel

@Composable
fun LootTableUIEnhanced(viewModel: LootTableViewModel) {
    val state = viewModel.state.collectAsState()

    // Screen background color
    Box(modifier = Modifier
        .fillMaxWidth()
        .background(pageBGColor)) {
        Column(
            modifier = Modifier.padding(controlPadding),
            verticalArrangement = Arrangement.spacedBy(columnSpacing)
        ) {
            // Button with rounded corners
            Button(
                onClick = {
                          viewModel?.processIntent(LootTableIntent.RollLootTable)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .semantics {
                               this.contentDescription = ComposableDescription.ROLL_BUTTON
                    },
                shape = RoundedCornerShape(cornerRadius),
                colors = ButtonDefaults.buttonColors(buttonColor)

            ) {
                Text("Roll on Loot Table",
                    style = TextStyle(fontSize = textFontSize),
                    color = buttonTextColor,

                )
            }

            // Result text within a rounded corner rectangle
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = outputBoxMinHeight)
                    .padding(vertical = internalPadding)
                    .semantics {
                               this.contentDescription = ComposableDescription.OUTPUT_BOX
                    },
                shape = RoundedCornerShape(cornerRadius),
                color = descriptoinBGColor
            ) {
                Text(
                    text = state.value.resultText,
                    modifier = Modifier
                        .padding(controlPadding)
                        .fillMaxWidth()
                        .semantics {
                            this.contentDescription = ComposableDescription.OUTPUT_TEXT
                        },
                    color = Color.Black,
                    style = TextStyle(fontSize = textFontSize)
                )
            }
        }
    }
}

/**
 * We *could* just use a string for the description, but this allows us to not rely on
 * strings when we are trying to identify the composable.
 * Instead of `onNodeWithContentDescription("ID value"),
 * we can use `onNodeWithContentDescription(ComposableDescription.ID_VAL)`
 */
object ComposableDescription {
    const val OUTPUT_BOX = "Output Display Box"
    const val ROLL_BUTTON = "Table Roller Button"
    const val OUTPUT_TEXT = "Output Box Text"
}

/*
@Preview(showBackground = true)
@Composable
fun PreviewLootTableUIEnhanced() {
    LootTableUIEnhanced()
}
 */
