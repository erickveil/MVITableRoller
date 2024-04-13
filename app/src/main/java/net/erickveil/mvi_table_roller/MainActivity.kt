package net.erickveil.mvi_table_roller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import net.erickveil.mvi_table_roller.ui.theme.MVITableRollerTheme
import net.erickveil.mvi_table_roller.ui.view.LootTableUIEnhanced
import net.erickveil.mvi_table_roller.ui.viewmodel.LootTableViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var viewModel: LootTableViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MVITableRollerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    viewModel = LootTableViewModel(this.application)
                    LootTableUIEnhanced(viewModel)
                }
            }
        }
    }
}
