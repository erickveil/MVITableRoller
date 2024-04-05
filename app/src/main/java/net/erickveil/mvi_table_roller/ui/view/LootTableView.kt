package net.erickveil.mvi_table_roller.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LootTableUIEnhanced(onRollTable: () -> Unit, resultText: String = "Result appears here") {
    // Screen background color
    Box(modifier = Modifier
        .fillMaxWidth()
        .background(Color(0xFFEDE7F6))) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // Button with rounded corners
            Button(
                onClick = onRollTable,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFF673AB7))
            ) {
                Text("Roll on Loot Table", color = Color.White)
            }

            // Result text within a rounded corner rectangle
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                shape = RoundedCornerShape(8.dp),
                color = Color(0xFFD1C4E9)
            ) {
                Text(
                    text = resultText,
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    color = Color.Black,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLootTableUIEnhanced() {
    LootTableUIEnhanced(onRollTable = {})
}