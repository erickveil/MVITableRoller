package net.erickveil.mvi_table_roller.ui.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import net.erickveil.mvi_table_roller.data.repository.LootRepository

class LootTableViewModelFactory {
    class LootTableViewModelFactory(
        private val application: Application,
        private val repository: LootRepository
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(LootTableViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return LootTableViewModel(application, repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}