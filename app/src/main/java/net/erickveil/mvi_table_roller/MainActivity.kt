package net.erickveil.mvi_table_roller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import net.erickveil.mvi_table_roller.data.repository.LootRepository
import net.erickveil.mvi_table_roller.ui.theme.MVITableRollerTheme
import net.erickveil.mvi_table_roller.ui.view.LootTableUIEnhanced
import net.erickveil.mvi_table_roller.ui.viewmodel.LootTableViewModel
import net.erickveil.mvi_table_roller.ui.viewmodel.LootTableViewModelFactory

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
                    val repository = LootRepository(this.applicationContext)
                    val viewModelFactory = LootTableViewModelFactory.LootTableViewModelFactory(
                        this.application,
                        repository
                    )
                    viewModel = ViewModelProvider(this, viewModelFactory)[LootTableViewModel::class.java]
                    LootTableUIEnhanced(viewModel)
                }
            }
        }
    }
}
