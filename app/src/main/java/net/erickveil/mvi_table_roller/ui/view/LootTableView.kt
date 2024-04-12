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
fun LootTableUIEnhanced(viewModel: LootTableViewModel?) {
    val state = viewModel?.state?.collectAsState()
    val response: String? = state?.value?.resultText
    val nonNullResponse: String = response ?: "No data available"

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
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(cornerRadius),
                colors = ButtonDefaults.buttonColors(buttonColor)
            ) {
                Text("Roll on Loot Table",
                    style = TextStyle(fontSize = textFontSize),
                    color = buttonTextColor
                )
            }

            // Result text within a rounded corner rectangle
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = outputBoxMinHeight)
                    .padding(vertical = internalPadding),
                shape = RoundedCornerShape(cornerRadius),
                color = descriptoinBGColor
            ) {
                Text(
                    text = nonNullResponse,
                    modifier = Modifier
                        .padding(controlPadding)
                        .fillMaxWidth(),
                    color = Color.Black,
                    style = TextStyle(fontSize = textFontSize)
                )
            }
        }
    }
}

/*
@Preview(showBackground = true)
@Composable
fun PreviewLootTableUIEnhanced() {
    LootTableUIEnhanced()
}
 */
